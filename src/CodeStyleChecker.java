import java.io.BufferedReader;
import java.util.List;
import java.util.Scanner;

public class CodeStyleChecker {
    private BufferedReader reader;

    /**
     * Constructor for CodeStyleChecker
     * @param reader Reader object that has the file to be checked.
     */
    public CodeStyleChecker(BufferedReader reader) {
        this.reader = reader;
    }



    /**
     * Checks if the code adheres to camelCase naming convention.
     * @return Percentage of code that does not adhere to camelCase naming convention.
     */
    public double checkCamelCase() {
        List<String> lines = reader.lines().toList();
    }


    private boolean isCamelCase(String word){
        return true;
    }
}
