package error;

import java.util.InputMismatchException;

public class DivideByZero extends InputMismatchException {
    public DivideByZero(){
        super("You tried to divide by zero.");
    }
}
