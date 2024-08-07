package com.educandoweb.course.resources.exceptions;

import com.educandoweb.course.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

// irá interceptar as exceções
@ControllerAdvice
public class ResourceExceptionHandler {


    // metodo será acionado quando a exceção personalizada for
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFounds(ResourceNotFoundException e, HttpServletRequest request){
        String error = "resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(error, e.getMessage(), request.getRequestURI(), status.value(), Instant.now());
        return ResponseEntity.status(status).body(err);
    }
}
