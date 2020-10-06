package exceptions;

public class IncorrectSingerException extends RuntimeException {
    public IncorrectSingerException(String errorMessage) {
        super(errorMessage);
    }
}
