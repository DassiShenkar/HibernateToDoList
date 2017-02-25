package il.ac.shenkar.hibernate;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class ToiListException extends Exception {
    private Logger logger;
    public ToiListException() {
        logger = Logger.getLogger(ToiListException.class);
        BasicConfigurator.configure();
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
