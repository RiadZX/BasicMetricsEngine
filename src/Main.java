import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("./FakeCode/Main.java"));
        } catch (Exception e) {
            System.out.println("File not found.");
        }
        if (scanner == null) return;

        // CodeStyleChecker currently validates only camelCase.
        CodeStyleChecker codeStyleChecker = new CodeStyleChecker(scanner);
        double percentageThatDoesNotAdhere = codeStyleChecker.checkCamelCase();

        System.out.println(percentageThatDoesNotAdhere);

        scanner.close();
    }
}