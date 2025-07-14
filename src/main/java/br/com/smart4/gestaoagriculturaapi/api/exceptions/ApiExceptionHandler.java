package br.com.smart4.gestaoagriculturaapi.api.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<String> handleEntityNotFoundException(BusinessException e) {
        return error(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> handleEntityNotFoundException(ResourceNotFoundException e) {
        return error(HttpStatus.NOT_FOUND, e);
    }

    private ResponseEntity<String> error(HttpStatus status, Exception e) {
        logger.error("error: ", e);
        return ResponseEntity.status(status).body(e.getMessage());
    }
}
