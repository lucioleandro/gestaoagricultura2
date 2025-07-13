package br.com.smart4.gestaoagriculturaapi.autenticacao.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class AuthExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(AuthExceptionHandler.class);

    private ResponseEntity<String> error(HttpStatus status, Exception e) {
        logger.error("error: ", e);
        return ResponseEntity.status(status).body(e.getMessage());
    }
}
