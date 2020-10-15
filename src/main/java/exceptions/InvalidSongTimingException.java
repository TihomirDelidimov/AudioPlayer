package exceptions;

/**
 * This exception is thrown if invalid timing is passed to the song class constructor
 */
public class InvalidSongTimingException extends RuntimeException {
    public InvalidSongTimingException(String errorMessage) {
        super(errorMessage);
    }
}
