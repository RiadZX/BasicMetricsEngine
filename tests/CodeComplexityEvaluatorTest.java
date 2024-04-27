import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CodeComplexityEvaluatorTest {
    @Test
    void testCheckCodeComplexity() throws FileNotFoundException {
        CodeComplexityEvaluator codeComplexityEvaluator = new CodeComplexityEvaluator(
                new BufferedReader(
                        new FileReader("./FakeCode/Fake2.java")));
        List<ComplexityResult> results = List.of(
                new ComplexityResult("fooBar", 1),
                new ComplexityResult("TooComplex", 6)
        );
        assertEquals(results, codeComplexityEvaluator.getComplexity());
    }

    @Test
    void testCheckCodeComplexity2() throws FileNotFoundException {
        CodeComplexityEvaluator codeComplexityEvaluator = new CodeComplexityEvaluator(
                new BufferedReader(
                        new FileReader("./FakeCode/Fake3.java")));
        List<ComplexityResult> results = List.of(
                new ComplexityResult("fooBar", 3),
                new ComplexityResult("JetBrainsBest", 3)
        );
        assertEquals(results, codeComplexityEvaluator.getComplexity());
    }

    @Test
    void getMethodName() throws FileNotFoundException {
        assertEquals("JetBrainsBest", new CodeComplexityEvaluator(null).getMethodName(List.of(
                "private List<T> JetBrainsBest() {",
                "if (1 == 1) {",
                "if (1 == 1) {",
                "while (1 != 1) {",
                "}",
                "}",
                "}"
        ), 0));
    }

    @Test
    void getMethodName2() throws FileNotFoundException {
        assertEquals("JetBrainsBest", new CodeComplexityEvaluator(null).getMethodName(List.of(
                "private List<T> JetBrainsBest()",
                "{if (1 == 1) {",
                "if (1 == 1) {",
                "while (1 != 1) {",
                "}",
                "}",
                "}"
        ), 1));
    }
    @Test
    void getMethodName3() throws FileNotFoundException {
        assertEquals("JetBrainsBest", new CodeComplexityEvaluator(null).getMethodName(List.of(
                "private List<T> JetBrainsBest(" +
                        ")",
                "",
                "",
                "{if (1 == 1) {",
                "if (1 == 1) {",
                "while (1 != 1) {",
                "}",
                "}",
                "}"
        ), 1));
    }
}