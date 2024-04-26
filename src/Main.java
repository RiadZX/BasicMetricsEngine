import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        String directory = "./FakeCode";
        if(args.length == 1){
            directory = args[0];
        }
        Application app = new Application();
        var complexityResults = app.getComplexity(directory);
        //These  % and s are for formatting the output in a table format.
        System.out.printf("%-20s | %s%n", "methodname", "complexity");
        System.out.println("----------------------------------------");
        for (ComplexityResult result : complexityResults) {
            System.out.printf("%-20s | %d%n", result.methodName(), result.complexity());
        }
        System.out.println("----------------------------------------");
        double codeStyle = app.getCodeStyle(directory);
        System.out.println("Percentage of nonCamel methods: " +  new DecimalFormat("#.##").format(codeStyle) + "%");
    }
}