import java.io.BufferedReader;
import java.util.Arrays;
import java.util.List;

import static java.lang.Character.*;

public class CodeStyleChecker {
    private final BufferedReader reader;
    /**
     * List of keywords that are banned in the code when checking for camelCase in a *method* name.
     */
    private final List<String> bannedKeywords = List.of(
            "if", "class", "while", "for", "switch", "case", "default"
    );

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
        //ASSUMPTIONS:
        //1. The code is in Java.
        //2. between each keyword and the method name, there is a space.
        List<String> lines = reader.lines().toList();
        int totalMethods = 0;
        int methodsNotInCamelCase = 0;

        for (int i =0; i<lines.size();i++){
            //Iterate over each line.
            String line = lines.get(i); // current line

            if(line.contains("{")){
                //I will handle 2 cases here:
                //1. Opening Curly bracket on same line. Eg: public void fooBar(){...}
                //2. Opening Curly bracket on the next line. Eg: public void fooBar()
                //                                   {...}
                String trimmedLine = line.trim();
                if (trimmedLine.charAt(0)=='{') {
                    //The method name is on the previous line.
                    String lineBefore = lines.get(i-1);
                    List<String> words = Arrays.stream(lineBefore.split(" ")).toList();
                    if(lineBefore.contains("{")) continue;
                    if (!isLineValid(lineBefore)) continue;
                    String methodName = getMethodName(words);
                    totalMethods++;
                    if(!isCamelCase(methodName)){
                        //Method name is not in camelCase.
                        methodsNotInCamelCase++;
                    }

                } else{
                    List<String> words = Arrays.stream(line.split(" ")).toList();
                    if (!isLineValid(line)) continue;
                    String methodName = getMethodName(words);
                    totalMethods++;
                    if(!isCamelCase(methodName)){
                        methodsNotInCamelCase++;
                    }
                }

            }
        }
        System.out.println("Total methods: "+totalMethods);
        System.out.println("Methods not in camelCase: "+methodsNotInCamelCase);
        // round output to 2 decimals
        return Math.round(((double)methodsNotInCamelCase/totalMethods)*100.0*100.0)/100.0;
    }

    /**
     * This method checks if the line is valid or not.
     * It checks if the line contains any banned keywords.
     * If it does, it returns false.
     * @param line line to be checked.
     * @return true if line is valid, false otherwise.
     */
    private boolean isLineValid(String line){
        if(line==null || line.isEmpty()) return false;
        List<String> words = Arrays.stream(line.split(" ")).toList();
        //We check if it is a for loop, if statement, switch, class declaration or method declaration.
        //HANDLE OTHER KEYWORDS HERE.
        return bannedKeywords.stream().noneMatch(words::contains);
    }

    /**
     * This method cleans the method name by removing any special characters.
     * @param words list of words in the line.
     * @return cleaned word.
     */
    private String getMethodName(List<String> words){
        if(words==null || words.isEmpty()) throw new IllegalArgumentException("Words cannot be null or empty.");
        //Last word might be a { when having a whitespace between method name and {
        String lastWord = words.getLast();
        if(lastWord.trim().equals("{") || lastWord.trim().equals("{}")) lastWord = words.get(words.size()-2);
        StringBuilder cleanedWord = new StringBuilder();

        for(int i=0;i<lastWord.length();i++){
            if(lastWord.charAt(i)=='{' || lastWord.charAt(i)=='(' || lastWord.charAt(i)=='[' || lastWord.charAt(i)=='}' || lastWord.charAt(i)==')' || lastWord.charAt(i)==']') continue;
            cleanedWord.append(lastWord.charAt(i));
        }
        return cleanedWord.toString();
    }

    /**
     * This method checks if the word is in CamelCase or not.
     * Valid CamelCase: FooBar, OneTwo, ThreeFourFive
     * Invalid CamelCase: fooBar, oneTwo, threeFourFive
     * @param word word to be checked.
     * @return true if word is in CamelCase, false otherwise.
     */
    private boolean isCamelCase(String word){
        if(word==null || word.isEmpty()) return false;
        if(!Character.isUpperCase(word.charAt(0))) return false;
        for(int i=1;i<word.length();i++){
            if(i==word.length()-1 && isUpperCase(word.charAt(i))) return false; //Last character cannot be uppercase.
            if(!isLetter(word.charAt(i))) return false; //Only letters are allowed.
        }
        return true;
    }
}
