package com.educandoweb.course.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUUID = 1L;

    public ResourceNotFoundException(Object id){
        super("Resource not found. Id " + id);
    }
}
