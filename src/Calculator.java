import java.math.BigDecimal;
import java.math.RoundingMode;

// Note: all the regex filters in this script were created using web tools

public class Calculator {

    /**
     * Takes a mathematical expression and returns its result.
     * @param expression The value to evaluate.
     * @return The result of the given expression.
     */
    public double eval(String expression) {
        try {
            // Remove all whitespaces
            expression = expression.replaceAll("\\s", "");

            double result; // Variable to store the final result

            result = evaluatePower(expression);

            // Add multiplication symbol where necessary
            expression = addMultiplicationSigns(expression);
            
            // Evaluate the expression normally
            result = evaluateExpression(expression);

            // Round the final result and return it
            return round(result, 4);
        } catch (Exception e) {
            return Double.NaN; // Return NaN for invalid expressions
        }
    }

    // Method to add multiplication signs where necessary
    private String addMultiplicationSigns(String expression) {
        // Add multiplication between numbers/parentheses and other numbers/parentheses
        expression = expression.replaceAll("([0-9)])\\(", "$1*("); // Number followed by '('
        expression = expression.replaceAll("\\)([0-9(])", ")*$1");  // ')' followed by number/parenthesis

        return expression;
    }

    // Method to evaluate expressions (handles +, -)
    private double evaluateExpression(String expression) {
        // Handle addition and subtraction first
        int lastOperatorIndex = -1;
        int parenthesesBalance = 0;

        // Searches for the last + or - (outside parentheses).
        // If found, it splits the expression into two parts.
        for (int i = expression.length() - 1; i >= 0; i--) {
            char c = expression.charAt(i);

            if (c == ')') parenthesesBalance++;
            else if (c == '(') parenthesesBalance--;

            if (parenthesesBalance == 0 && (c == '+' || c == '-') && lastOperatorIndex == -1) {
                lastOperatorIndex = i;
            }
        }

        if (lastOperatorIndex != -1) {
            String left = expression.substring(0, lastOperatorIndex);
            String right = expression.substring(lastOperatorIndex + 1);
            char operator = expression.charAt(lastOperatorIndex);

            if (operator == '+') {
                return evaluateExpression(left) + evaluateTerm(right);
            } else {
                return evaluateExpression(left) - evaluateTerm(right);
            }
        }

        return evaluateTerm(expression); // If no + or -, evaluate the term
    }

    // Method to evaluate terms (handles *, /)
    private double evaluateTerm(String expression) {
        int lastOperatorIndex = -1;
        int parenthesesBalance = 0;

        // Identical to evaluateExpression but for * and /
        for (int i = expression.length() - 1; i >= 0; i--) {
            char c = expression.charAt(i);

            if (c == ')') parenthesesBalance++;
            else if (c == '(') parenthesesBalance--;

            if (parenthesesBalance == 0 && (c == '*' || c == '/') && lastOperatorIndex == -1) {
                lastOperatorIndex = i;
            }
        }

        if (lastOperatorIndex != -1) {
            String left = expression.substring(0, lastOperatorIndex);
            String right = expression.substring(lastOperatorIndex + 1);
            char operator = expression.charAt(lastOperatorIndex);

            if (operator == '*') {
                return evaluateTerm(left) * evaluateFactor(right);
            } else {
                return evaluateTerm(left) / evaluateFactor(right);
            }
        }

        return evaluateFactor(expression); // If no * or /, evaluate the factor
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
            exponent = eval(innerExpression); // Call eval on the inner expression (1/n or something similar)
        } else {
            exponent = Double.parseDouble(parts[1]); // Regular exponent
        }

        return Math.pow(base, exponent); // Use Math.pow to calculate x^n or x^(1/n)
    }

    // Method to evaluate individual factors
    private double evaluateFactor(String expression) {
        // If the expression is a number, return it directly
        if (expression.matches("[0-9]+(\\.[0-9]+)?")) { // Regex for integers or decimal numbers
            return Double.parseDouble(expression);
        }
        // If the expression is inside parentheses, evaluate the content
        if (expression.startsWith("(") && expression.endsWith(")")) {
            return evaluateExpression(expression.substring(1, expression.length() - 1)); // Recursively evaluate the expression inside parentheses
        }
        return 0;
    }

    // Method to round a double to the specified number of decimal places
    private double round(double value, int places) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);  // Round to the specified number of places
        return bd.doubleValue();
    }
}