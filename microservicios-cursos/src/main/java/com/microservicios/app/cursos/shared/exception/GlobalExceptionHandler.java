package com.microservicios.app.cursos.shared.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Manejo de ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Resource Not Found");
        errorResponse.put("message", ex.getMessage());
        return errorResponse;
    }

    // Manejo de FeignException.NotFound (cuando Feign no encuentra el recurso en otro microservicio)
    @ExceptionHandler(FeignException.NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleFeignNotFoundException(FeignException.NotFound ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "External Resource Not Found");
        errorResponse.put("message", "The requested resource was not found in an external service.");
        return errorResponse;
    }

    // Manejo genérico de otras excepciones
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleGeneralException(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Internal Server Error");
        errorResponse.put("message", ex.getMessage());
        return errorResponse;
    }

}
