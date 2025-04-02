import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;
import java.util.List;

public class Plotter {

    /**
     * A method to parse the input function and return the result based on x.
     * @param x The value of x for which we want to evaluate the function.
     * @param expression The function as a string (e.g., "f(x) = 5*(2 + 3*x)^2").
     * @return The evaluated result for the given x.
     */
    public static double evaluateFunction(double x, String expression) {
        // Remove 'f(x)=' and any spaces from the expression
        expression = expression.split("=")[1].replaceAll("\\s", "");

        try {
            // Create an expression builder and define 'x' as a variable
            Expression exp = new ExpressionBuilder(expression)
                    .variable("x") // Define x as a variable
                    .build();

            // Set the value of x in the expression
            exp.setVariable("x", x);

            // Evaluate the expression for the given x
            return exp.evaluate();
        } catch (Exception e) {
            System.err.println("Error evaluating expression: " + e.getMessage());
            return Double.NaN; // Return NaN for invalid expressions
        }
    }

    /**
     * Generates points for the graph by evaluating the function over the range [start, end].
     * @param start The starting value of x.
     * @param end The ending value of x.
     * @param step The step size for incrementing x.
     * @param function The function as a string (e.g., "f(x) = 5*(2 + 3*x)^2").
     * @return A list of points represented by [x, y].
     */
    public static List<double[]> plot(double start, double end, double step, String function) {
        List<double[]> points = new ArrayList<>();

        // Loop through the range of x values
        for (double x = start; x <= end; x += step) {
            double y = evaluateFunction(x, function); // Calculate y for current x

            // Skip points where the function evaluation results in NaN
            if (Double.isNaN(y)) {
                continue; // Skip invalid points
            }

            // Store the point in the list
            points.add(new double[] {x, y});
        }

        return points;
    }
}