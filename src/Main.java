import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        String directory = "./src";
        if(args.length == 1){
            directory = args[0];
        }
        Application app = new Application();
        var complexityResults = app.getComplexity(directory);

        System.out.printf("%-20s | %s%n", "methodname", "complexity");
        System.out.println("----------------------------------------");
        for (ComplexityResult result : complexityResults) {
            System.out.printf("%-20s | %d%n", result.getMethodName(), result.getComplexity());
        }
        System.out.println("----------------------------------------");
        double codeStyle = app.getCodeStyle(directory);
        System.out.println("Percentage of noncamel methods: " +  new DecimalFormat("#.##").format(codeStyle) + "%");
    }
}