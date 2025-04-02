import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.List;

public class Calculator {

    /**
     * Takes a mathematical expression and returns its result.
     * @param expression The value to evaluate.
     * @return The result of the given expression.
     */
    public Object eval(String expression) {
        try {
            if (expression.contains("f(x)")) {
                // Define the range of x values to plot
                double start = -64; // Starting x value
                double end = 64;    // Ending x value
                double step = 0.0005;  // Step size to increment x by

                // Get all the points
                List<double[]> points = Plotter.plot(start, end, step, expression);
                // Loop through all points and plot them
                for (double[] point : points) {
                    Interface.zeichnePunkt(point[0], point[1]);
                }
                return expression;
            } else if (expression.contains("x")) {
                throw new IllegalArgumentException("Expression contained x but was not a function");
            }

            // Remove all whitespaces
            expression = expression.replaceAll("\\s", "");

            // Build and evaluate the expression using exp4j
            Expression exp = new ExpressionBuilder(expression).build();
            double result = exp.evaluate();

            // Round to 4 decimal places
            return round(result, 4);  // Round to 4 decimal places
        } catch (Exception e) {
            System.err.println(e);
            return Double.NaN; // Return NaN for invalid expressions
        }
    }

    // Method to round a double to the specified number of decimal places
    private double round(double value, int places) {
        return Math.round(value * Math.pow(10, places)) / Math.pow(10, places);
    }
}