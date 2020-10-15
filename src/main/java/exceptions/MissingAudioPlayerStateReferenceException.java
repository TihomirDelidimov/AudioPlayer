package exceptions;

/**
 * This exception is thrown when AudioPlayerState reference is missing
 */
public class MissingAudioPlayerStateReferenceException extends RuntimeException {
    public MissingAudioPlayerStateReferenceException(String errorMessage) {
        super(errorMessage);
    }
}
