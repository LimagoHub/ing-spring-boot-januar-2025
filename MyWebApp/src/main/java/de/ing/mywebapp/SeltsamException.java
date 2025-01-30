package de.ing.mywebapp;

public class SeltsamException extends RuntimeException{

    public SeltsamException() {
    }

    public SeltsamException(final String message) {
        super(message);
    }

    public SeltsamException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public SeltsamException(final Throwable cause) {
        super(cause);
    }

    public SeltsamException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
