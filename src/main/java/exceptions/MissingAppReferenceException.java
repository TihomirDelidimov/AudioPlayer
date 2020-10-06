package exceptions;

/**
 * This exception is thrown if the reference passed to the audio player is missing
 */
public class MissingAppReferenceException extends  RuntimeException{
    public MissingAppReferenceException(String errorMessage) {
        super(errorMessage);
    }
}
