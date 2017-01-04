package il.ac.shenkar.hibernate;

public class ToiListException extends Exception {

    public ToiListException() {
    }

    public ToiListException(String message) {
        super(message);
    }

    public ToiListException(Throwable cause) {
        super(cause);
    }

    public ToiListException(String message, Throwable cause) {
        super(message, cause);
    }
}
