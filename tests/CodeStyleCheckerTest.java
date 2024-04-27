import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CodeStyleCheckerTest {
    @ParameterizedTest
    @MethodSource("testCases")
    void isCamelCase(String methodName, boolean expected) {
        assertEquals(expected, new CodeStyleChecker(new BufferedReader(new StringReader("")))
                .isCamelCase(methodName));
    }
    @Test
    void isCamelCaseNull() {
        assertThrows(IllegalArgumentException.class, () -> new CodeStyleChecker(null));
    }

    @Test
    void testCodeStyle(){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("./FakeCode/Fake2.java");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        CodeStyleChecker codeStyleChecker = new CodeStyleChecker(bufferedReader);
        List<CodeStyleResult> results = List.of(
                new CodeStyleResult("fooBar", true),
                new CodeStyleResult("TooComplex", false)
        );
        assertEquals(results, codeStyleChecker.getCheckStyle());
    }

    @Test
    void testCodeStyle2(){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("./FakeCode/Fake3.java");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        CodeStyleChecker codeStyleChecker = new CodeStyleChecker(bufferedReader);
        List<CodeStyleResult> results = List.of(
                new CodeStyleResult("fooBar", true),
                new CodeStyleResult("JetBrainsBest", false)
        );
        assertEquals(results, codeStyleChecker.getCheckStyle());
    }

    static List<Object[]> testCases() {
        return List.of(
                new Object[]{"JetBrainsBest", false},
                new Object[]{"JetBrainsBest", false},
                new Object[]{"jetBrainsBesT", false},
                new Object[]{"jetB23rainsBest", false},
                new Object[]{"jetB__rainsBest", false},
                new Object[]{"jetBrainsBest", true},
                new Object[]{"jetbrainsbest", true},
                new Object[]{"jetbrainsbest1", false}
        );
    }
}