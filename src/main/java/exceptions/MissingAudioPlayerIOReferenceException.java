package exceptions;

/**
 * This exception is thrown when AudioPlayerConsoleIO reference is missing
 */
public class MissingAudioPlayerIOReferenceException extends RuntimeException {
    public MissingAudioPlayerIOReferenceException(String errorMessage) {
        super(errorMessage);
    }
}
