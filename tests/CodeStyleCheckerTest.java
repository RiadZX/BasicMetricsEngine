import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CodeStyleCheckerTest {
    List<String> files = List.of(
            "./FakeCode/Main.java",
            "./src/CodeStyleChecker.java",
            "./FakeCode/Fake2.java"
    );
    @Test
    void testCheckCodeStyle() throws FileNotFoundException {
        CodeStyleChecker codeStyleChecker = new CodeStyleChecker(new BufferedReader(new FileReader(files.getFirst())));
    }

    @Test
    void testCheckCamelCase() throws FileNotFoundException {
        CodeStyleChecker codeStyleChecker = new CodeStyleChecker(new BufferedReader(new FileReader(files.getFirst())));
        double percentageThatDoesNotAdhere = codeStyleChecker.checkCamelCase();
        assertEquals(
                42.86 //3/7
                , percentageThatDoesNotAdhere);
    }

    @Test
    void testCheckCamelCase2() throws FileNotFoundException {
        CodeStyleChecker codeStyleChecker = new CodeStyleChecker(new BufferedReader(new FileReader(files.get(1))));
        double percentageThatDoesNotAdhere = codeStyleChecker.checkCamelCase();
        assertEquals(
                80.0
                , percentageThatDoesNotAdhere);
    }

    @Test
    void testCheckCamelCase3() throws FileNotFoundException {
        CodeStyleChecker codeStyleChecker = new CodeStyleChecker(new BufferedReader(new FileReader(files.getLast())));
        double percentageThatDoesNotAdhere = codeStyleChecker.checkCamelCase();
        assertEquals(
                50
                , percentageThatDoesNotAdhere);
    }

    @Test
    void testEmptyFile() {
        CodeStyleChecker codeStyleChecker = new CodeStyleChecker(new BufferedReader(new StringReader("")));
        double percentageThatDoesNotAdhere = codeStyleChecker.checkCamelCase();
        assertEquals(
                0
                , percentageThatDoesNotAdhere);
    }

    @Test
    void testEmptyClass() {
        CodeStyleChecker codeStyleChecker = new CodeStyleChecker(new BufferedReader(new StringReader("""
                public class Main {
               }""")));
        double percentageThatDoesNotAdhere = codeStyleChecker.checkCamelCase();
        assertEquals(
                0
                , percentageThatDoesNotAdhere);
    }

    @Test
    void testNull() {
        assertThrows(IllegalArgumentException.class,() -> {
          new CodeStyleChecker(null);
        });
    }
}