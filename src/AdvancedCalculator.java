import java.math.BigDecimal;
import java.math.RoundingMode;

public class AdvancedCalculator extends Calculator {

    @Override
    public double eval(String expression) {
        try {
            // Remove all whitespaces
            expression = expression.replaceAll("\\s", "");

            // Handle the power operator '**' in the expression
            if (expression.contains("**")) {
                return evaluatePower(expression);
            }

            // If no '**' operator, fall back to the base class eval for normal operations
            return super.eval(expression);
        } catch (Exception e) {
            return Double.NaN; // Return NaN for invalid expressions
        }
    }

    // Method to handle power operator '**' (x**n or x**(1/n))
    private double evaluatePower(String expression) {
        // Split by '**' to separate the base and exponent
        String[] parts = expression.split("\\*\\*");
        double base = Double.parseDouble(parts[0]);

        // Check if the exponent is wrapped in parentheses (for roots like 1/n)
        double exponent;
        if (parts[1].startsWith("(") && parts[1].endsWith(")")) {
            // Remove the parentheses and parse the value inside
            String innerExpression = parts[1].substring(1, parts[1].length() - 1);
            exponent = super.eval(innerExpression); // Call eval on the inner expression (1/n or something similar)
        } else {
            exponent = Double.parseDouble(parts[1]); // Regular exponent
        }

        return Math.pow(base, exponent); // Use Math.pow to calculate x^n or x^(1/n)
    }
}