import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

/**
 * The Application class is the main class that is used to get the complexity and code style of a directory.
 */
public class Application {
    public Application() {}
    /**
     * Get the top 3 methods with the highest complexity.
     * Uses the CodeComplexityEvaluator to get the complexity of each method, of each file in the directory.
     *
     * @param directory The directory to search for .java files.
     * @return List of ComplexityResult objects. Top 3 based on complexity.
     */
    public List<ComplexityResult> getComplexity(String directory) {
        List<File> files = getJavaFiles(directory);

        var allComplexities= files.stream()
                .map(file -> {
                    try {
                        return new CodeComplexityEvaluator(new BufferedReader(new FileReader(file))).getComplexity();
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Could not read file.");
                    }
                }).flatMap(List::stream).sorted(
                        (c1, c2) -> c2.complexity() - c1.complexity() //the comparator to sort in desceinding order
                ).limit(3).toList();

        return allComplexities;
    }

    /**
     * Get all the .java files in the directory.
     * @param directory The directory to search for .java files.
     * @return List of File objects.
     */
    private List<File> getJavaFiles(String directory) throws IllegalArgumentException {
        if(directory == null) throw new IllegalArgumentException("Directory cannot be null.");
        try {
            List<File> files = List.of(new File(directory).listFiles())
                    .stream()
                    .filter(file -> file.getName().endsWith(".java"))
                    .toList();
            return files;
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Directory does not exist.");
        }
    }

    /**
     * Get the percentage of methods that are not in lowerCamelCase.
     * @param directory The directory with the Java files.
     * @return The percentage of methods that are not in lowerCamelCase.
     */
    public double getCodeStyle(String directory){
        List<File> files = getJavaFiles(directory);
        List<CodeStyleResult> allStyles= files.stream()
                .map(file -> {
                    try {
                        return new CodeStyleChecker(new BufferedReader(new FileReader(file))).getCheckStyle();
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Could not read file.");
                    }
                }).flatMap(List::stream).toList();

        //Now we have all the styles, we can calculate the percentage of camelCase methods.
        int totalMethods = allStyles.size();
        int notCamelCase = (int) allStyles.stream().filter(result -> !result.isCamelCase()).count();
        //percentage of not camelCase methods.
        return (double) notCamelCase / totalMethods * 100;
    }

}
