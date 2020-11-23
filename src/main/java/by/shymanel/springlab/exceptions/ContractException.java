package by.shymanel.springlab.exceptions;


public class ContractException extends Exception {
    public ContractException() {
        super();
    }

    public ContractException(String message) {
        super(message);
    }

    public ContractException(Exception e) {
        super(e);
    }

    public ContractException(String message, Exception e) {
        super(message, e);
    }
}