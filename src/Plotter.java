import basis.*;
import java.lang.Math;
import java.util.regex.*;
import java.util.*;

public class Plotter {

    // A method to parse the input function and return a dynamically created function
    public static double evaluateFunction(double x, String expression) {
        // Remove the 'f(x)=' part
        expression = expression.split("=")[1];

        // Pattern to match terms like 9x^5, -6x^2, or constant terms
        String regex = "([+-]?\\d*\\.?\\d*)x\\^(\\d+)|([+-]?\\d*\\.?\\d*)x|([+-]?\\d*\\.?\\d+)"; // fucking regex filters :skull:
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);

        double result = 0; // TODO: change this to NaN?

        // Process all the terms in the expression
        while (matcher.find()) {
            if (matcher.group(1) != null && matcher.group(2) != null) {
                // Matching x^power terms
                double coefficient = matcher.group(1).isEmpty() ? 1 : Double.parseDouble(matcher.group(1));
                int power = Integer.parseInt(matcher.group(2));
                result += coefficient * Math.pow(x, power);
            } else if (matcher.group(3) != null) {
                // Matching x terms (like 9x or -0.5x)
                double coefficient = matcher.group(3).isEmpty() ? 1 : Double.parseDouble(matcher.group(3));
                result += coefficient * x;
            } else if (matcher.group(4) != null) {
                // Matching constant terms (like 2, -4.3)
                result += Double.parseDouble(matcher.group(4));
            }
        }

        return result;
    }

    // Method to generate and display points
    public static List<double[]> plot(double start, double end, double step, String function) {
        List<double[]> points = new ArrayList<>();

        // Loop through the range of x values
        for (double x = start; x <= end; x += step) {
            double y = evaluateFunction(x, function); // Calculate y for current x
            System.out.printf("x = %.2f, y = %.2f\n", x, y); // Print result

            // Store the point in the list
            points.add(new double[] {x, y});

            // Delay (if needed)
            /*
            try {
                Thread.sleep(1);  // Delay in milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            */
        }

        // Return the list of points
        return points;
    }

    /*
    public static void main(String[]args) {
        // Define the range of x values to plot
        double start = -10; // Starting x value
        double end = 10;    // Ending x value
        double step = 0.1;  // Step size to increment x by
        String function = "f(x)=5x^3+6x^6-0.4"; // The function that is supposed to be plotted

        // Call plot method to generate and display the points
        plot(start, end, step, function);
    }
     */
}
