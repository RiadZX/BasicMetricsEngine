public record CodeStyleResult(
        String methodName,
        boolean isCamelCase
) {
    public CodeStyleResult {
        if (methodName == null) throw new IllegalArgumentException("Method name cannot be null.");
    }
}
