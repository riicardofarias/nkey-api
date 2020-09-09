package br.com.nkey.api.exception;

/**
 * Representa as exceções relacionadas a authenticação de usuário
 */
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
