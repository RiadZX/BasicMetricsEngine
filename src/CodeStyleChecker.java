import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static java.lang.Character.*;

public class CodeStyleChecker {
    private final BufferedReader reader;
    /**
     * Constructor for CodeStyleChecker
     * @param reader Reader object that has the file to be checked.
     */
    public CodeStyleChecker(BufferedReader reader){
        if (reader == null) {
            throw new IllegalArgumentException("Reader cannot be null.");
        }
        this.reader = reader;
    }
    public List<CodeStyleResult> getComplexity() {
        Stack<Integer> stack = new Stack<>(); //stores the index of the opening bracket
        List<CodeStyleResult> results = new ArrayList<>();
        List<String> lines = reader.lines().toList();

        int i;
        //iterate until the first bracket, ignore the first bracket, since that is for the class.
        for (i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.contains("{")) {
                i++;
                break;
            }
        }
        String currentMethod = null;
        while (i < lines.size()) {
            String line = lines.get(i);
            if(stack.isEmpty() && currentMethod!=null){
                results.add(new CodeStyleResult(currentMethod, isCamelCase(currentMethod)));
                currentMethod = null;

            }
            //we encountered new method.
            if (line.contains("{") && stack.isEmpty()) {
                stack.push(i);
                //get method name.
                currentMethod = getMethodName(lines, i);
            }
            if(containsKeyword(line)
                    && !stack.isEmpty()){
                stack.push(i);
            }
            if (line.contains("}") && !stack.isEmpty()) {
                stack.pop();
            }
            i++;
        }
        return results;
    }

    public boolean isCamelCase(String currentMethod) {
        if (currentMethod == null) throw new IllegalArgumentException("Method name cannot be null.");
        if (currentMethod.isEmpty()) return false;
        if (!isLowerCase(currentMethod.charAt(0))) return false;
        for (int i = 1; i < currentMethod.length(); i++) {
            if(i==currentMethod.length()-1 && isUpperCase(currentMethod.charAt(i))) return false; //last character cannot be uppercase.
            if (!isLetter(currentMethod.charAt(i))) return false;
        }
        return true;
    }

    private static boolean containsKeyword(String line) {
        List<String> keywords = List.of("if", "else", "for", "while","switch");
        return keywords.stream().anyMatch(line::contains);
    }

    public String getMethodName(List<String> lines, int i) {
        //Start from i, go back until you find a (, then go back until you find a space.
        String line = lines.get(i);
        int j = line.indexOf("{"); //indexOF , because you might have multiple brackets in the same line.
        if (j == -1) {
            j = line.length() - 1;
        }
        while (j >= 0) {
            if (line.charAt(j) == '(') {
                break;
            }
            j--;
        }
        //not found, then look in the previous line.
        if (j == -1) {
            return getMethodName(lines, i - 1);
        }
        int k = j;
        while (k >= 0) {
            if (line.charAt(k) == ' ') {
                break;
            }
            k--;
        }
        //if not found, then look in the previous line.
        if (k == -1) {
            return getMethodName(lines, i - 1);
        }
        return line.substring(k, j).trim();
    }


}
