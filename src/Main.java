import java.io.BufferedReader;
import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new java.io.FileReader(new File("./FakeCode/Main.java")));
        } catch (Exception e) {
            System.out.println("File not found.");
            return;
        }

        // CodeStyleChecker currently validates only camelCase.
        CodeStyleChecker codeStyleChecker = new CodeStyleChecker(reader);
        double percentageThatDoesNotAdhere = codeStyleChecker.checkCamelCase();

        System.out.println(percentageThatDoesNotAdhere);
    }
}