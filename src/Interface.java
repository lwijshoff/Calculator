import basis.*;
import java.awt.event.KeyEvent; // https://docs.oracle.com/en/java/javase/11/docs/api/constant-values.html
import java.util.List;

public class Interface {

    private Fenster root;
    private TextFeld numberfield;
    private ListBox calculator_type;

    private int root_width = 420;
    private int root_height = 740;
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

    public Calculator calculator; // Custom implementation

    public Interface() {
        root = new Fenster("Calculator", root_width, root_height);
        root.setzeHintergrundFarbe(Farbe.rgb(12, 12, 12));

        default_calculator = 0;
        create_numberfield(default_calculator);

        one = new Knopf("1", 10, 312 + 20, 100, 100);
        one.setzeHintergrundFarbe(Farbe.rgb(66, 66, 66));
        one.setzeSchriftFarbe(Farbe.WEISS);
        two = new Knopf("2", 110, 312 + 20, 100, 100);
        two.setzeHintergrundFarbe(Farbe.rgb(66, 66, 66));
        two.setzeSchriftFarbe(Farbe.WEISS);
        three = new Knopf("3", 210, 312 + 20, 100, 100);
        three.setzeHintergrundFarbe(Farbe.rgb(66, 66, 66));
        three.setzeSchriftFarbe(Farbe.WEISS);
        four = new Knopf("4", 10, 412 + 20, 100, 100);
        four.setzeHintergrundFarbe(Farbe.rgb(66, 66, 66));
        four.setzeSchriftFarbe(Farbe.WEISS);
        five = new Knopf("5", 110, 412 + 20, 100, 100);
        five.setzeHintergrundFarbe(Farbe.rgb(66, 66, 66));
        five.setzeSchriftFarbe(Farbe.WEISS);
        six = new Knopf("6", 210, 412 + 20, 100, 100);
        six.setzeHintergrundFarbe(Farbe.rgb(66, 66, 66));
        six.setzeSchriftFarbe(Farbe.WEISS);
        seven = new Knopf("7", 10, 512 + 20, 100, 100);
        seven.setzeHintergrundFarbe(Farbe.rgb(66, 66, 66));
        seven.setzeSchriftFarbe(Farbe.WEISS);
        eigth = new Knopf("8", 110, 512 + 20, 100, 100);
        eigth.setzeHintergrundFarbe(Farbe.rgb(66, 66, 66));
        eigth.setzeSchriftFarbe(Farbe.WEISS);
        nine = new Knopf("9", 210, 512 + 20, 100, 100);
        nine.setzeHintergrundFarbe(Farbe.rgb(66, 66, 66));
        nine.setzeSchriftFarbe(Farbe.WEISS);
        zero = new Knopf("0", 10, 612 + 20, 100, 100);
        zero.setzeHintergrundFarbe(Farbe.rgb(66, 66, 66));
        zero.setzeSchriftFarbe(Farbe.WEISS);

        clear = new Knopf("AC", 10, 212 + 20, 100, 100);
        clear.setzeHintergrundFarbe(Farbe.rgb(0, 255, 255));

        remove = new Knopf("⌫", 210, 612 + 20, 100, 100);
        remove.setzeHintergrundFarbe(Farbe.rgb(44, 44, 44));
        remove.setzeSchriftFarbe(Farbe.WEISS);
        decimal = new Knopf(".", 110, 612 + 20 ,100,100);
        decimal.setzeHintergrundFarbe(Farbe.rgb(44, 44, 44));
        decimal.setzeSchriftFarbe(Farbe.WEISS);

        plus = new Knopf("+", 310, 512 + 20, 100, 100);
        plus.setzeHintergrundFarbe(Farbe.rgb(44, 44, 44));
        plus.setzeSchriftFarbe(Farbe.WEISS);
        minus = new Knopf("-", 310, 412 + 20, 100, 100);
        minus.setzeHintergrundFarbe(Farbe.rgb(44, 44, 44));
        minus.setzeSchriftFarbe(Farbe.WEISS);
        multiply = new Knopf("×", 310, 312 + 20, 100, 100);
        multiply.setzeHintergrundFarbe(Farbe.rgb(44, 44, 44));
        multiply.setzeSchriftFarbe(Farbe.WEISS);
        divide = new Knopf("÷", 310, 212 + 20, 100, 100);
        divide.setzeHintergrundFarbe(Farbe.rgb(44, 44, 44));
        divide.setzeSchriftFarbe(Farbe.WEISS);
        bracket_open = new Knopf("(", 110, 212 + 20, 100, 100);
        bracket_open.setzeHintergrundFarbe(Farbe.rgb(44, 44, 44));
        bracket_open.setzeSchriftFarbe(Farbe.WEISS);
        bracket_close = new Knopf(")", 210, 212 + 20, 100, 100);
        bracket_close.setzeHintergrundFarbe(Farbe.rgb(44, 44, 44));
        bracket_close.setzeSchriftFarbe(Farbe.WEISS);

        equals = new Knopf("=", 310, 612 + 20, 100, 100);
        equals.setzeHintergrundFarbe(Farbe.rgb(0, 255, 255));

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
        numberfield = new TextFeld(10, 10, numberfield_width, numberfield_height);
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
                break;
        }
    }

    public void fuehreAus() {
        while (true) {
            Hilfe.kurzePause();

            char key = ' ';
            // Handle button presses for the normal calculator
            if (clear.wurdeGedrueckt()) {
                key = 'C';
            } else if (one.wurdeGedrueckt()) {
                key = '1';
            } else if (two.wurdeGedrueckt()) {
                key = '2';
            } else if (three.wurdeGedrueckt()) {
                key = '3';
            } else if (four.wurdeGedrueckt()) {
                key = '4';
            } else if (five.wurdeGedrueckt()) {
                key = '5';
            } else if (six.wurdeGedrueckt()) {
                key = '6';
            } else if (seven.wurdeGedrueckt()) {
                key = '7';
            } else if (eigth.wurdeGedrueckt()) {
                key = '8';
            } else if (nine.wurdeGedrueckt()) {
                key = '9';
            } else if (zero.wurdeGedrueckt()) {
                key = '0';
            } else if (divide.wurdeGedrueckt()) {
                key = '/';
            } else if (multiply.wurdeGedrueckt()) {
                key = '*';
            } else if (minus.wurdeGedrueckt()) {
                key = '-';
            } else if (plus.wurdeGedrueckt()) {
                key = '+';
            } else if (decimal.wurdeGedrueckt()) {
                key = '.';
            } else if (bracket_open.wurdeGedrueckt()) {
                key = '(';
            } else if (bracket_close.wurdeGedrueckt()) {
                key = ')';
            } else if (remove.wurdeGedrueckt()) {
                key = '⌫';
            } else if (equals.wurdeGedrueckt()) {
                key = '=';
            }

            // Check for keyboard input if no button is pressed
            if (key == ' ' && keyboard.wurdeGedrueckt()) {
                key = Character.toUpperCase(keyboard.zeichen());
            }

            // Execute action if a valid key is detected
            if (key != ' ') {
                executeAction(key);
            }

            // Prevent resizing of the root window
            if (root.breite() != root_width || root.hoehe() != root_height) {
                resizer();
            }

            // Check if a calculator type is selected
            if (calculator_type.wurdeGewaehlt()) {
                int mode = calculator_type.index(); // Get the selected calculator mode

                if (mode == 0) {  // Normal Calculator Mode
                    calculator = new Calculator();

                } else if (mode == 1) {  // Graphical Calculator Mode
                    // calculator = new GraphicalCalculator();
                    // Update the interface for graphical calculator mode
                    // Here you can change buttons, labels, or add new functionality specific to graphical calculator

                    // For example, if you need to add new graphical operations:
                    // advancedOperationButtons();  // This is a method that adds advanced operations

                    // Continue with the advanced calculator logic
                    // For instance, handle advanced operations, trig functions, etc.
                    // handleAdvancedOperations();
                }
            }
        }
    }
}