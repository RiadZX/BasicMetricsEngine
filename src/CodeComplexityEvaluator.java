import java.io.BufferedReader;
import java.util.*;

public class CodeComplexityEvaluator {
    private BufferedReader reader;

    /**
     * Single file evaluator for code complexity.
     * @param reader BufferedReader object that has the file to be checked.
     */
    public CodeComplexityEvaluator(BufferedReader reader) {
        this.reader = reader;
    }

    public List<ComplexityResult> getComplexity() {
        Stack<Integer> stack = new Stack<>(); //stores the index of the opening bracket
        List<ComplexityResult> results = new ArrayList<>();
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
        int complexity = 0; //starting complexity = 0. NOTE.
        while (i < lines.size()) {
            String line = lines.get(i);
            if(stack.isEmpty() && currentMethod!=null){
                results.add(new ComplexityResult(currentMethod, complexity));
                currentMethod = null;
                complexity = 0;
            }
            //we encountered new method.
            if (line.contains("{") && stack.isEmpty()) {
                stack.push(i);
                //get method name.
                currentMethod = getMethodName(lines, i);
            }
            if(containsKeyword(line)
                            && !stack.isEmpty()){
                complexity++;
                stack.push(i);
            }
            if (line.contains("}") && !stack.isEmpty()) {
                stack.pop();
            }
            i++;
        }
        return results;
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