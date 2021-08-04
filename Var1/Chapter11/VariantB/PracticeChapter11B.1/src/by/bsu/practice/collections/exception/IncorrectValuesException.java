package by.bsu.practice.collections.exception;

public class IncorrectValuesException extends Exception{
    public IncorrectValuesException() {
    }

    public IncorrectValuesException(String message) {
        super(message);
    }

    public IncorrectValuesException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectValuesException(Throwable cause) {
        super(cause);
    }
}
