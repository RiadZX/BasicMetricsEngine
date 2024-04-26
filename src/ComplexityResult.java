public class ComplexityResult {
    private int complexity;
    private String methodName;

    public ComplexityResult(String methodName,int complexity) {
        this.complexity = complexity;
        this.methodName = methodName;
    }

    public int getComplexity() {
        return complexity;
    }

    public String getMethodName() {
        return methodName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ComplexityResult)) {
            return false;
        }
        ComplexityResult other = (ComplexityResult) obj;
        return other.complexity == this.complexity && other.methodName.equals(this.methodName);
    }
}
