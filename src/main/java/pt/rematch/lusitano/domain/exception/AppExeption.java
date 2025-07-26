package pt.rematch.lusitano.domain.exception;

/*
 * Application Exception class for Lusitano Discord Bot
 * This class extends Exception and serves as a base class for all application-specific exceptions.
 */
public abstract class AppExeption extends Exception {
    private static final long serialVersionUID = 1L;

    public AppExeption(String message) {
        super(message);
    }

    public AppExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public AppExeption(Throwable cause) {
        super(cause);
    }

}
