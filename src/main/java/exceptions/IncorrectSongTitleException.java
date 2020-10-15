package exceptions;

/**
 * This exception is thrown if the song's title is not valid
 */
public class IncorrectSongTitleException extends RuntimeException {
    public IncorrectSongTitleException(String errorMessage) {
        super(errorMessage);
    }
}
