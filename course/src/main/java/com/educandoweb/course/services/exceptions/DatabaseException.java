package com.educandoweb.course.services.exceptions;

public class DatabaseException extends RuntimeException{

    private static final long serialVersionUUID = 1L;

    public DatabaseException(String message){
        super(message);
    }
}
