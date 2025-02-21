import basis.*;
import java.awt.event.KeyEvent; // https://docs.oracle.com/en/java/javase/11/docs/api/constant-values.html
import java.util.List;
import java.util.HashMap;
import java.util.Map;


public class Interface {

    private Fenster root;
    private Fenster plotter;
    private Stift plotter_pen;
    private TextFeld numberfield;
    private ListBox calculator_type;

    private int root_width = 420;
    private int root_height = 840;
    private int plotter_width = 740;
    private int plotter_height = 740;
    private int numberfield_width = 420 - 20;
    private int numberfield_height = 212;
    private int calculator_type_width = 150;
    private int calculator_type_heigth = 20;

    private int default_calculator;

    private Tastatur keyboard;
    private Maus mouse;

    private Knopf one;
    private Knopf two;
    private Knopf three;
    private Knopf four;
    private Knopf five;
    private Knopf six;
    private Knopf seven;
    private Knopf eigth;
    private Knopf nine;
    private Knopf zero;

    private Knopf clear;
    private Knopf remove;
    private Knopf decimal;

    private Knopf plus;
    private Knopf minus;
    private Knopf multiply;
    private Knopf divide;
    private Knopf bracket_open;
    private Knopf bracket_close;

    private Knopf equals;

    private Knopf x;
    private Knopf exponent;
    private Knopf carrot;
    private Knopf funct;

    public Calculator calculator; // Custom implementation

    public Interface() {

        plotter = new Fenster("Plotter", plotter_width, plotter_height, false);
        plotter.setzeHintergrundFarbe(Farbe.rgb(12, 12, 12));

        plotter_pen = new Stift();
        plotter_pen.setzeFarbe(Farbe.WEISS);
        plotter_pen.hoch();

        root = new Fenster("Calculator", root_width, root_height);
        root.setzeHintergrundFarbe(Farbe.rgb(12, 12, 12));

        default_calculator = 0;
        create_numberfield(default_calculator);

        one = new Knopf("1", 10, 412 + 20, 100, 100, root);
        one.setzeHintergrundFarbe(Farbe.rgb(66, 66, 66));
        one.setzeSchriftFarbe(Farbe.WEISS);
        two = new Knopf("2", 110, 412 + 20, 100, 100, root);
        two.setzeHintergrundFarbe(Farbe.rgb(66, 66, 66));
        two.setzeSchriftFarbe(Farbe.WEISS);
        three = new Knopf("3", 210, 412 + 20, 100, 100, root);
        three.setzeHintergrundFarbe(Farbe.rgb(66, 66, 66));
        three.setzeSchriftFarbe(Farbe.WEISS);
        four = new Knopf("4", 10, 512 + 20, 100, 100, root);
        four.setzeHintergrundFarbe(Farbe.rgb(66, 66, 66));
        four.setzeSchriftFarbe(Farbe.WEISS);
        five = new Knopf("5", 110, 512 + 20, 100, 100, root);
        five.setzeHintergrundFarbe(Farbe.rgb(66, 66, 66));
        five.setzeSchriftFarbe(Farbe.WEISS);
        six = new Knopf("6", 210, 512 + 20, 100, 100, root);
        six.setzeHintergrundFarbe(Farbe.rgb(66, 66, 66));
        six.setzeSchriftFarbe(Farbe.WEISS);
        seven = new Knopf("7", 10, 612 + 20, 100, 100, root);
        seven.setzeHintergrundFarbe(Farbe.rgb(66, 66, 66));
        seven.setzeSchriftFarbe(Farbe.WEISS);
        eigth = new Knopf("8", 110, 612 + 20, 100, 100, root);
        eigth.setzeHintergrundFarbe(Farbe.rgb(66, 66, 66));
        eigth.setzeSchriftFarbe(Farbe.WEISS);
        nine = new Knopf("9", 210, 612 + 20, 100, 100, root);
        nine.setzeHintergrundFarbe(Farbe.rgb(66, 66, 66));
        nine.setzeSchriftFarbe(Farbe.WEISS);
        zero = new Knopf("0", 10, 712 + 20, 100, 100, root);
        zero.setzeHintergrundFarbe(Farbe.rgb(66, 66, 66));
        zero.setzeSchriftFarbe(Farbe.WEISS);

        clear = new Knopf("AC", 10, 212 + 20, 100, 100, root);
        clear.setzeHintergrundFarbe(Farbe.rgb(0, 255, 255));

        remove = new Knopf("⌫", 210, 712 + 20, 100, 100, root);
        remove.setzeHintergrundFarbe(Farbe.rgb(44, 44, 44));
        remove.setzeSchriftFarbe(Farbe.WEISS);
        decimal = new Knopf(".", 110, 712 + 20 ,100,100, root);
        decimal.setzeHintergrundFarbe(Farbe.rgb(44, 44, 44));
        decimal.setzeSchriftFarbe(Farbe.WEISS);

        plus = new Knopf("+", 310, 612 + 20, 100, 100, root);
        plus.setzeHintergrundFarbe(Farbe.rgb(44, 44, 44));
        plus.setzeSchriftFarbe(Farbe.WEISS);
        minus = new Knopf("-", 310, 512 + 20, 100, 100, root);
        minus.setzeHintergrundFarbe(Farbe.rgb(44, 44, 44));
        minus.setzeSchriftFarbe(Farbe.WEISS);
        multiply = new Knopf("×", 310, 412 + 20, 100, 100, root);
        multiply.setzeHintergrundFarbe(Farbe.rgb(44, 44, 44));
        multiply.setzeSchriftFarbe(Farbe.WEISS);
        divide = new Knopf("÷", 310, 312 + 20, 100, 100, root);
        divide.setzeHintergrundFarbe(Farbe.rgb(44, 44, 44));
        divide.setzeSchriftFarbe(Farbe.WEISS);
        bracket_open = new Knopf("(", 110, 212 + 20, 100, 100, root);
        bracket_open.setzeHintergrundFarbe(Farbe.rgb(44, 44, 44));
        bracket_open.setzeSchriftFarbe(Farbe.WEISS);
        bracket_close = new Knopf(")", 210, 212 + 20, 100, 100, root);
        bracket_close.setzeHintergrundFarbe(Farbe.rgb(44, 44, 44));
        bracket_close.setzeSchriftFarbe(Farbe.WEISS);

        equals = new Knopf("=", 310, 712 + 20, 100, 100, root);
        equals.setzeHintergrundFarbe(Farbe.rgb(0, 255, 255));

        x = new Knopf("x", 10, 312 + 20, 100, 100, root);
        x.setzeHintergrundFarbe(Farbe.rgb(44, 44, 44));
        x.setzeSchriftFarbe(Farbe.WEISS);

        exponent = new Knopf("^", 110, 312 + 20, 100, 100, root);
        exponent.setzeHintergrundFarbe(Farbe.rgb(44, 44, 44));
        exponent.setzeSchriftFarbe(Farbe.WEISS);

        carrot = new Knopf("⎷", 210, 312 + 20, 100, 100, root); // explicitly used radical root symbol, because it will take any [ x^(1/n) ]
        carrot.setzeHintergrundFarbe(Farbe.rgb(44, 44, 44));
        carrot.setzeSchriftFarbe(Farbe.WEISS);

        funct = new Knopf("f(x)", 310, 212 + 20, 100, 100, root); // TODO: When this button is pressed it should automatically set over to Graphical Calculator
        funct.setzeHintergrundFarbe(Farbe.rgb(44, 44, 44));
        funct.setzeSchriftFarbe(Farbe.WEISS);

        keyboard = new Tastatur();
        mouse = new Maus(equals);

        calculator = new Calculator(); // Custom implementation
    }

    /**
     * Creates a non-editable number field with specific styling and a list box for selecting
     * a calculator type. The method selects a default type if none is given.
     *
     * @param mode The index of the calculator type to select.
     */
    public void create_numberfield(int mode) {
        numberfield = new TextFeld(10, 10, numberfield_width, numberfield_height, root);
        numberfield.setzeHintergrundFarbe(Farbe.rgb(44, 44, 44));
        numberfield.setzeRand(Farbe.SCHWARZ, 1);
        numberfield.setzeSchriftGroesse(50);
        numberfield.setzeSchriftFarbe(Farbe.WEISS);
        numberfield.setzeEditierbar(false); // Does not allow the user to edit directly, but only tricks user into allowing input for keys set in the match case etc.

        calculator_type = new ListBox(0, 0, calculator_type_width, calculator_type_heigth, numberfield);
        calculator_type.setzeHintergrundFarbe(Farbe.rgb(0, 255, 255));
        calculator_type.setzeRand(Farbe.SCHWARZ, 1);
        calculator_type.fuegeAn("Calculator");
        calculator_type.fuegeAn("Graphical Calculator");

        if (calculator_type.wurdeGewaehlt()) {
            calculator_type.waehle(mode);
        } else {
            calculator_type.waehle(default_calculator);
        }
    }

    public void cleanup() {
        // Clears the console for BlueJ
        System.out.println("System exit!");
        System.out.print('\u000C');
        System.exit(0);
    }

    public void resizer() {
        root.setzeGroesse(root_width, root_height);
    }

    public void executeAction(char key) {

        int keyCode = KeyEvent.VK_F4;
        int modifiers = KeyEvent.ALT_DOWN_MASK;

        switch (key) {
            case KeyEvent.VK_F4: // This workaround is only for for BlueJ! (Alt + F4)
                if ((modifiers & KeyEvent.ALT_DOWN_MASK) != 0) {
                    System.out.println("Exiting application.");
                    cleanup();
                }
                break;
            case KeyEvent.VK_ESCAPE: // Exit (Escape Key)
                System.out.println("Exiting application.");
                cleanup();
                break;
            case 'C':
            case KeyEvent.VK_DELETE:
                clear.setzeFokus();

                // Keeps track of the last state the calculator was in, sets the calculator mode for the new numberfield
                int mode = calculator_type.index();
                numberfield.gibFrei();
                create_numberfield(mode);
                break;
            case '1':
                one.setzeFokus();
                numberfield.fuegeAn('1');
                break;
            case '2':
                two.setzeFokus();
                numberfield.fuegeAn('2');
                break;
            case '3':
                three.setzeFokus();
                numberfield.fuegeAn('3');
                break;
            case '4':
                four.setzeFokus();
                numberfield.fuegeAn('4');
                break;
            case '5':
                five.setzeFokus();
                numberfield.fuegeAn('5');
                break;
            case '6':
                six.setzeFokus();
                numberfield.fuegeAn('6');
                break;
            case '7':
                seven.setzeFokus();
                numberfield.fuegeAn('7');
                break;
            case '8':
                eigth.setzeFokus();
                numberfield.fuegeAn('8');
                break;
            case '9':
                nine.setzeFokus();
                numberfield.fuegeAn('9');
                break;
            case '0':
                zero.setzeFokus();
                numberfield.fuegeAn('0');
                break;
            case '/':
                divide.setzeFokus();
                numberfield.fuegeAn("/");
                break;
            case '*':
                multiply.setzeFokus();
                numberfield.fuegeAn("*");
                break;
            case '-':
                minus.setzeFokus();
                numberfield.fuegeAn("-");
                break;
            case '+':
                plus.setzeFokus();
                numberfield.fuegeAn("+");
                break;
            case '.':
                decimal.setzeFokus();
                numberfield.fuegeAn(".");
                break;
            case '(':
                bracket_open.setzeFokus();
                numberfield.fuegeAn("(");
                break;
            case ')':
                bracket_close.setzeFokus();
                numberfield.fuegeAn(")");
                break;
            case '⌫':
            case KeyEvent.VK_BACK_SPACE:
                remove.setzeFokus();
                // Remove the last character
                String str = "" + numberfield.text();
                if (str != null && str.length() > 0) {
                    str = str.substring(0, str.length() - 1);
                    numberfield.setzeText(str);
                } else {
                    System.err.println("String is empty or null");
                }
                break;
            case '=':
                equals.setzeFokus();
                // Evaluate the expression entered in the number field
                String expression = numberfield.text();
                double result = calculator.eval(expression);
                // TODO: double lastresult = result;  // Store the result as the last result

                // Check if the result is NaN or not and log it correspondingly
                if (Double.isNaN(result)) {
                    System.err.println(result);
                } else {
                    System.out.println(result);
                }
                // Convert the result to a string and update the number field
                String output = String.valueOf(result);
                numberfield.setzeText(output);  // Set the result as the number field text

                // TEST

                // Define the range of x values to plot
                double start = -10; // Starting x value
                double end = 10;    // Ending x value
                double step = 0.0005;  // Step size to increment x by
                String function = "f(x)=0.25x^2+2x-5"; // The function that is supposed to be plotted

                // Get all the points
                List<double[]> points = Plotter.plot(start, end, step, function);
                // Loop through all points and plot them
                for (double[] point : points) {
                    zeichnePunkt(point[0], point[1]);
                }

                // TEST

                break;
            case 'X':
                bracket_close.setzeFokus();
                numberfield.fuegeAn("x");
                break;
            case '^':
                bracket_close.setzeFokus();
                numberfield.fuegeAn("^");
                break;
            case '⎷':
                bracket_close.setzeFokus();
                numberfield.fuegeAn("^(1/2)");
                break;
            case 'F':
                bracket_close.setzeFokus();
                numberfield.fuegeAn("f(x)=");
                break;
        }
    }

    // Method to get the key based on button presses or keyboard input
    private char getPressedKey() {
        char key = ' ';

        // Map buttons to corresponding key values
        // Absolute legends --> https://www.w3schools.com/java/java_hashmap.asp
        Map<Knopf, Character> buttonToKeyMap = new HashMap<>();
        buttonToKeyMap.put(clear, 'C');
        buttonToKeyMap.put(one, '1');
        buttonToKeyMap.put(two, '2');
        buttonToKeyMap.put(three, '3');
        buttonToKeyMap.put(four, '4');
        buttonToKeyMap.put(five, '5');
        buttonToKeyMap.put(six, '6');
        buttonToKeyMap.put(seven, '7');
        buttonToKeyMap.put(eigth, '8');
        buttonToKeyMap.put(nine, '9');
        buttonToKeyMap.put(zero, '0');
        buttonToKeyMap.put(divide, '/');
        buttonToKeyMap.put(multiply, '*');
        buttonToKeyMap.put(minus, '-');
        buttonToKeyMap.put(plus, '+');
        buttonToKeyMap.put(decimal, '.');
        buttonToKeyMap.put(bracket_open, '(');
        buttonToKeyMap.put(bracket_close, ')');
        buttonToKeyMap.put(remove, '⌫');
        buttonToKeyMap.put(equals, '=');
        buttonToKeyMap.put(x, 'X');
        buttonToKeyMap.put(exponent, '^');
        buttonToKeyMap.put(carrot, '⎷');
        buttonToKeyMap.put(funct, 'F');

        // Check for button presses
        for (Map.Entry<Knopf, Character> entry : buttonToKeyMap.entrySet()) {
            if (entry.getKey().wurdeGedrueckt()) {
                key = entry.getValue();
                break;
            }
        }

        // If no button is pressed, check for keyboard input
        if (key == ' ' && keyboard.wurdeGedrueckt()) {
            key = Character.toUpperCase(keyboard.zeichen());
        }

        return key;
    }

    // Method to handle window resizing
    private void handleWindowResize() {
        if (root.breite() != root_width || root.hoehe() != root_height) {
            resizer();
        }
    }

    // Variables to save window sizes
    private int letzteBreite = 0;
    private int letzteHoehe = 0;

    /**
     * God help me, this method took forever to create.
     *
     * Draws a 2D Coordinate system with axis, that adapt relative to the window size
     */
    public void zeichneKoordinatensystem() {
        // Get the current width and height of the window
        int breite = plotter.breite();
        int hoehe = plotter.hoehe();

        // Check if the window size has changed
        if (breite != letzteBreite || hoehe != letzteHoehe) {
            // Update the stored window sizes
            letzteBreite = breite;
            letzteHoehe = hoehe;

            // Determine the center point of the window (origin of the coordinate system)
            double mitteX = breite / 2.0;
            double mitteY = hoehe / 2.0;

            // Clear the drawing area
            plotter.loescheAlles();

            // Set the pen to normal mode
            plotter_pen.normal();

            // Set the pen color to white
            plotter_pen.setzeFarbe(Farbe.WEISS);

            // Positive X-Axis
            for (double x = mitteX; x <= breite; x += 20) {
                plotter_pen.linie(x, mitteY - 5, x, mitteY + 5);  // Vertical line

                // Calculate the value of the X-Axis
                int AxisnWert = (int) ((x - mitteX) / 20);

                // Label the X-Axis
                if (x != mitteX) {  // Prevents the value of the origin (0) from appearing twice
                    plotter_pen.bewegeAuf(x, mitteY + 15);  // Position for label
                    plotter_pen.schreibe(Integer.toString(AxisnWert));
                }
            }

            // Negative X-Axis
            for (double x = mitteX; x >= 0; x -= 20) {
                plotter_pen.linie(x, mitteY - 5, x, mitteY + 5);  // Vertical line

                // Calculate the value of the X-Axis
                int AxisnWert = (int) ((x - mitteX) / 20);

                // Label the X-Axis
                if (x != mitteX) {  // Prevents the value of the origin (0) from appearing twice
                    plotter_pen.bewegeAuf(x, mitteY + 15);  // Position for label
                    plotter_pen.schreibe(Integer.toString(AxisnWert));
                }
            }

            // Positive Y-Axis
            for (double y = mitteY; y >= 0; y -= 20) {
                plotter_pen.linie(mitteX - 5, y, mitteX + 5, y);  // Horizontal line

                // Calculate the value of the Y-Axis
                int AxisnWert = (int) ((mitteY - y) / 20);

                // Label the Y-Axis
                if (y != mitteY) {  // Prevents the value of the origin (0) from appearing twice
                    plotter_pen.bewegeAuf(mitteX + 10, y);  // Position for label
                    plotter_pen.schreibe(Integer.toString(AxisnWert));
                }
            }

            // Negative Y-Axis
            for (double y = mitteY; y <= hoehe; y += 20) {
                plotter_pen.linie(mitteX - 5, y, mitteX + 5, y);  // Horizontal line

                // Calculate the value of the Y-Axis
                int AxisnWert = (int) ((mitteY - y) / 20);

                // Label the Y-Axis
                if (y != mitteY) {  // Prevents the value of the origin (0) from appearing twice
                    plotter_pen.bewegeAuf(mitteX + 10, y);  // Position for label
                    plotter_pen.schreibe(Integer.toString(AxisnWert));
                }
            }

            // Draw the main axes (X-Axis and Y-Axis)
            plotter_pen.linie(0, mitteY, breite, mitteY);  // X-Axis
            plotter_pen.linie(mitteX, 0, mitteX, hoehe);  // Y-Axis

            // Label the axes
            plotter_pen.bewegeAuf(breite - 15, mitteY - 15);  // Move the pen down for the X label
            plotter_pen.schreibe("X");

            plotter_pen.bewegeAuf(mitteX - 20, 15);  // Move the pen right for the Y label
            plotter_pen.schreibe("Y");
        }
    }

    public void zeichnePunkt(double x, double y) {
        // Get the current width and height of the plotter (window)
        int breite = plotter.breite();
        int hoehe = plotter.hoehe();

        // Point / Graph width
        int width = 1;

        // Determine the center of the window (origin of the coordinate system)
        double mitteX = breite / 2.0;
        double mitteY = hoehe / 2.0;

        // Convert the coordinate (x, y) to screen coordinates
        // Each unit corresponds to 20 pixels
        double pixelX = mitteX + x * 20; // Scale and shift to the center
        double pixelY = mitteY - y * 20; // Invert Y to make it fit the window

        // Set the pen to normal mode
        plotter_pen.normal();

        // Set the pen color to red
        plotter_pen.setzeFarbe(Farbe.ROT);

        // Draw a small box (dot) at the specified position
        plotter_pen.rechteck(pixelX, pixelY, width, width);
    }

    // Method to handle the selected calculator mode
    private void handleCalculatorMode() {
        if (calculator_type.wurdeGewaehlt()) {
            int mode = calculator_type.index(); // Get the selected calculator mode

            if (mode == 0) {  // Normal Calculator Mode
                plotter.setzeSichtbar(false);
            } else if (mode == 1) {  // Graphical Calculator Mode
                plotter.setzeSichtbar(true);
                // Additional logic for graphical calculator, if needed
            }
        }
        if (plotter.istSichtbar() == false && calculator_type.index() != 0) {
            calculator_type.waehle(0);
        }
    }

    // Method to check if the program should stop running and clean up
    private void checkAndCleanup() {
        if (plotter.istSichtbar() && !root.istSichtbar() || !plotter.istSichtbar() && !root.istSichtbar()) {
            cleanup();
        }
    }

    public void fuehreAus() {
        while (true) {
            Hilfe.kurzePause();  // Short pause to prevent UI freezing

            // Handle button press or keyboard input
            char key = getPressedKey();

            // Execute action if a valid key is detected
            if (key != ' ') {
                executeAction(key);
            }

            // Draw Coordinate System on Plotter window
            zeichneKoordinatensystem();

            // Prevent resizing of the root window
            handleWindowResize();

            // Handle calculator type change
            handleCalculatorMode();

            // Check if the program should stop running in the background
            checkAndCleanup();
        }
    }
}