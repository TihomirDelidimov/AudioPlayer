package exceptions;

/**
 * This exception is thrown when audio player reference is missing
 */
public class MissingAudioPlayerReferenceException extends RuntimeException {
    public MissingAudioPlayerReferenceException(String errorMessage) {
        super(errorMessage);
    }
}
