import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.List;

public class Calculator {

    /**
     * Takes a mathematical expression and returns its result.
     * @param expression The value to evaluate.
     * @param xValue The value of x to be used in the expression.
     * @return The result of the given expression.
     */
    public Object eval(String expression, double xValue) {
        try {
            if (expression.contains("f(x)") && Interface.calculator_type.index() == 1) {
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
            } else if (expression.contains("x") && Interface.calculator_type.index() == 1) {
                // Remove all whitespaces
                expression = expression.replaceAll("\\s", "");

                // Create an expression builder and define the variable x
                Expression exp = new ExpressionBuilder(expression)
                        .variable("x") // Define x as a variable
                        .build();

                // Set the value of x in the expression
                exp.setVariable("x", xValue);

                // Evaluate the expression
                double result = exp.evaluate();

                // Round to 4 decimal places
                return round(result, 4);  // Round to 4 decimal places
            }

            // Handle cases where the expression doesn't contain 'x'
            expression = expression.replaceAll("\\s", "");
            Expression exp = new ExpressionBuilder(expression).build();
            double result = exp.evaluate();
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