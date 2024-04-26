import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class Application {
    public Application() {
    }

    /**
     * Get the top 3 methods with the highest complexity.
     * Uses the CodeComplexityEvaluator to get the complexity of each method, of each file in the directory.
     *
     * @param directory The directory to search for .java files.
     * @return List of ComplexityResult objects. Top 3 based on complexity.
     */
    public List<ComplexityResult> getComplexity(String directory) {
        if (directory == null) throw new IllegalArgumentException("Directory cannot be null.");
        List<File> files = null;
        try {
            files = List.of(new File(directory).listFiles())
                    .stream()
                    .filter(file -> file.getName().endsWith(".java"))
                    .toList();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Directory does not exist.");
        }

        if (files == null || files.isEmpty()) throw new IllegalArgumentException("Could not find .java files");

        var allComplexities= files.stream()
                .map(file -> {
                    try {
                        return new CodeComplexityEvaluator(new BufferedReader(new FileReader(file))).getComplexity();
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Could not read file.");
                    }
                }).flatMap(List::stream).sorted(
                        (c1, c2) -> c2.getComplexity() - c1.getComplexity() //the comparator to sort in desceinding order
                ).limit(3).toList();

        return allComplexities;
    }

    public double getCodeStyle(String directory){
        if (directory == null) throw new IllegalArgumentException("Directory cannot be null.");
        List<File> files = null;
        try {
            files = List.of(new File(directory).listFiles())
                    .stream()
                    .filter(file -> file.getName().endsWith(".java"))
                    .toList();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Directory does not exist.");
        }

        if (files == null || files.isEmpty()) throw new IllegalArgumentException("Could not find .java files");

        var allStyles= files.stream()
                .map(file -> {
                    try {
                        return new CodeStyleChecker(new BufferedReader(new FileReader(file))).getComplexity();
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
