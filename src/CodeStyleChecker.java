import java.io.BufferedReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CodeStyleChecker {
    private BufferedReader reader;
    /**
     * List of keywords that are banned in the code when checking for camelCase in a *method* name.
     */
    private List<String> bannedKeywords = List.of(
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
        List<String> lines = reader.lines().toList();
        int totalMethods = 0;
        int methodsNotInCamelCase = 0;

        for (int i =0; i<lines.size();i++){
            //Iterate over each line.
            String line = lines.get(i); // current line

            //I will handle 2 cases here:
            //1. Opening Curly bracket on same line. Eg: public void fooBar(){...}
            //2. Opening Curly bracket on the next line. Eg: public void fooBar()
            //                                                  {...}

            //Case 1:
            if(line.contains("{")){
                List<String> words = Arrays.stream(line.split(" ")).toList();
                //We check if it is a forloop, if statement, switch, class declaration or method declaration.
                //HANDLE OTHER KEYWORDS HERE.
                if(bannedKeywords.stream().anyMatch(words::contains))continue;
                //We will check if the method name is in camelCase.
                String methodName = words.getLast().replace("{","").replace("(","").replace(")","").replace("}","");
                totalMethods++;
                if(!isCamelCase(methodName)){
                    //Method name is not in camelCase.
                    System.out.println("Method name is not in camelCase at line "+ i);
                    methodsNotInCamelCase++;
                }
            }
        }

        System.out.println("Total methods: "+totalMethods);
        System.out.println("Methods not in camelCase: "+methodsNotInCamelCase);
        return (methodsNotInCamelCase*100.0)/totalMethods;
    }


    private boolean isCamelCase(String word){
        return true;
    }
}
