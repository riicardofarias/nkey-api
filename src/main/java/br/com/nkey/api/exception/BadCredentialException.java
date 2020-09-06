package br.com.nkey.api.exception;

public class BadCredentialException extends RuntimeException {
    public BadCredentialException() {
        super();
    }

    public BadCredentialException(String message) {
        super(message);
    }

    public BadCredentialException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadCredentialException(Throwable cause) {
        super(cause);
    }
}
