package br.com.nkey.api.controller;

import br.com.nkey.api.exception.BadCredentialException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

/**
 * Controla as exeções lançadas propositalmente pelos Controlers e Services
 */
@ControllerAdvice
public class ExceptionController {
    /**
     * Controla as RuntimeException
     * @param ex RuntimeException
     * @return Map
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleBadCredentialException(RuntimeException ex) {
        ex.printStackTrace();

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Controla as BadCredentialException
     * @param ex BadCredentialException
     * @return Map
     */
    @ExceptionHandler(BadCredentialException.class)
    public ResponseEntity<?> handleBadCredentialException(BadCredentialException ex) {
        ex.printStackTrace();

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Controla as exceções geradas pelos métodos de validação
     * @param ex MethodArgumentNotValidException
     * @return Map
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        ex.printStackTrace();

        List<Map<String, String>> errors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((e) -> {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getDefaultMessage());

            errors.add(error);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
