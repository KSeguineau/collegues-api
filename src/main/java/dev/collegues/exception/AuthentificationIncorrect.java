package dev.collegues.exception;

public class AuthentificationIncorrect extends RuntimeException {

    public AuthentificationIncorrect() {
    }

    public AuthentificationIncorrect(String message) {
        super(message);
    }

    public AuthentificationIncorrect(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthentificationIncorrect(Throwable cause) {
        super(cause);
    }

    public AuthentificationIncorrect(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
