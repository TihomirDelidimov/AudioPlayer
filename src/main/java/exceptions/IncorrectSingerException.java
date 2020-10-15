package exceptions;

/**
 * This exception is thrown when Singer with incorrect parameters is created
 */
public class IncorrectSingerException extends RuntimeException {
    public IncorrectSingerException(String errorMessage) {
        super(errorMessage);
    }
}
