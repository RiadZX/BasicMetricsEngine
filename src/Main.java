import java.io.BufferedReader;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Application app = new Application();
        var complexityResults = app.getComplexity("./FakeCode");
        for (ComplexityResult result : complexityResults) {
            System.out.println(result.getMethodName() + " has a complexity of " + result.getComplexity());
        }
        double codeStyle = app.getCodeStyle("./FakeCode");
        System.out.println("Percentage of noncamel methods: " +  new DecimalFormat("#.##").format(codeStyle) + "%");
    }
}