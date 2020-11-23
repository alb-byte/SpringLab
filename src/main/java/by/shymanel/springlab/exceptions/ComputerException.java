package by.shymanel.springlab.exceptions;


public class ComputerException extends Exception{
    public ComputerException() {
        super();
    }

    public ComputerException(String message) {
        super(message);
    }

    public ComputerException(Exception e) {
        super(e);
    }

    public ComputerException(String message, Exception e) {
        super(message, e);
    }
}
