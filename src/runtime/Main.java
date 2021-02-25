package runtime;

import children.ComplexNumber;
import children.Double;
import children.Fraction;
import parents.RealNumber;

public class Main {
    static final Client client = new Client();

    public static void main(String[] args) {
        System.out.println(
                """
                        e.x '12/9 + 3' '0.592382938 / 4/3' the spaces around the main function is necessary in the middle. Do not include parenthesis.
                        You can also add, subtract, multiply, and divide two complex numbers in this calculator. Please pay attention to spaces.
                        Our complex number looks like (a + bi) where a and b can be any real number, a decimal, fraction, or whole number.
                        A fraction for a and b can only have integers for the numerator and denominator. There must be coefficients and no parts can be omitted.
                        For example you may have to type (-2 -1i) instead of (-2 - i)
                        e.x: '(5 - 3i) * (7 + 2i)' or '(14/6 + 0.33333333i) / (0 - 2/485i)'"""
        );

        while (client.isRunAgain()) {
            System.out.print("Enter the expression (spaces do not matter for complex but do matter for normal): ");
            String input = client.scanner.nextLine();
            boolean complexSolving = input.contains("(");
            if (complexSolving) {
                input = input.replaceAll(" ", "");
                String[] inputs = input.split("(?<=\\))[+|\\-*/](?=\\()");
                ComplexNumber a = parseComplex(inputs[0]);
                ComplexNumber b = parseComplex(inputs[1]);
                ComplexNumber answer;
                char sign = input.charAt(input.indexOf(')') + 1);
                switch (sign) {
                    case '+' -> answer = (ComplexNumber) a.add(b);
                    case '-' -> answer = (ComplexNumber) a.subtract(b);
                    case '*' -> answer = (ComplexNumber) a.multiply(b);
                    default -> answer = (ComplexNumber) a.divide(b);
                }
                System.out.println("The answer is: " + answer);
            }
            else {
                String[] inputs = input.trim().split("(?<=[0-9]) [+\\-*/] (?=[0-9])");
                RealNumber a = parseReal(inputs[0]);
                RealNumber b = parseReal(inputs[1]);
                RealNumber answer;
                char sign = input.trim().charAt(inputs[0].length() + 1);
                switch (sign){
                    case '+' -> answer = a.add(b);
                    case '-' -> answer = a.subtract(b);
                    case '*' -> answer = a.multiply(b);
                    default -> answer = a.divide(b);
                }
                System.out.println("The answer is: " + answer);
            }
            client.runAgain();
        }
    }

    private static RealNumber parseReal(String input) {
        RealNumber realNumber;
        try {
            realNumber = new Double(java.lang.Double.parseDouble(input));
        } catch (Exception e) {
            String[] fraction = input.split("/");
            realNumber = new Fraction(Integer.parseInt(fraction[0]), Integer.parseInt(fraction[1]));
        }
        return  realNumber;
    }

    private static ComplexNumber parseComplex(String input) {
        RealNumber realNumber, complexNumber;
        input = input.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("i", "");
        String[] parts = input.split("(?<=[0-9])[+-](?=[0-9])");
        if (input.matches(".*(?<=[0-9])-(?=[0-9]).*")) parts[1] = "-" + parts[1];
        try {
            realNumber = new Double(java.lang.Double.parseDouble(parts[0]));
        } catch (Exception e) {
            String[] fraction = parts[0].split("/");
            realNumber = new Fraction(Integer.parseInt(fraction[0]), Integer.parseInt(fraction[1]));
        }
        try {
            complexNumber = new Double(java.lang.Double.parseDouble(parts[1]));
        } catch (Exception e) {
            String[] fraction = parts[1].split("/");
            complexNumber = new Fraction(Integer.parseInt(fraction[0]), Integer.parseInt(fraction[1]));
        }

        return new ComplexNumber(realNumber, complexNumber);
    }
}
