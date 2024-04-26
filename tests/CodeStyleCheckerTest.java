import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CodeStyleCheckerTest {

    List<String> files = List.of(
            "./FakeCode/Main.java",
            "./src/CodeStyleChecker.java"
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





}