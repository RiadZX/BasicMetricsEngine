public record ComplexityResult (
        String methodName,
        int complexity
) {
    public ComplexityResult {
        if (methodName == null) throw new IllegalArgumentException("Method name cannot be null.");
        if (complexity < 0) throw new IllegalArgumentException("Complexity cannot be negative.");
    }
}
