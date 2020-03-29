package exception;

public class InvalidTicketNoException extends Exception {
    public InvalidTicketNoException(String message, Exception e) {
        super(message, e);
    }
}
