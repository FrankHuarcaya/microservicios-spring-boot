package com.example.microservices.app.exams.shared.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resourceName, Long resourceId) {
        super(String.format("%s with id %d not found.", resourceName, resourceId));
    }

    public ResourceNotFoundException(String resourceName, String resourceId) {
        super(String.format("%s with id %s not found.", resourceName, resourceId));
    }
}
