package com.microservicios.app.cursos.shared.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resourceName, Long resourceId) {
        super(String.format("%s with id %d not found.", resourceName, resourceId));
    }
}
