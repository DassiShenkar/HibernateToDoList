package il.ac.shenkar.hibernate.model.dao;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * This class handling all the exceptions in the project
 * @see Exception
 */
public class ToiListException extends Exception {
    private Logger logger;

    /**
     * Default Constructor
     */
    public ToiListException() {
        logger = Logger.getLogger(ToiListException.class);
        BasicConfigurator.configure();
    }

    /**
     * Constructor
     * @param message The message throw by the specific exception
     */
    public ToiListException(String message) {
        super(message);
        logger.error("Exception - " + message);
    }

    /**
     * Constructor
     * @param cause The reason for the exception
     */
    public ToiListException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor
     * @param message The message throw by the specific exception
     * @param cause The reason for the exception
     */
    public ToiListException(String message, Throwable cause) {
        super(message, cause);
    }
}
