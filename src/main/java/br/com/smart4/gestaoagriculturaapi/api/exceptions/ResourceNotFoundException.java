package br.com.smart4.gestaoagriculturaapi.api.exceptions;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private final int code;

    public ResourceNotFoundException(String message) {
        super(message);
        this.code = 404;
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
        this.code = 4004;
    }
}
