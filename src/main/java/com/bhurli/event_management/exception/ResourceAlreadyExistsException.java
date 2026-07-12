package com.bhurli.event_management.exception;

/**
 * Thrown when attempting to create a resource that already exists.
 */
public class ResourceAlreadyExistsException extends  RuntimeException{
    public ResourceAlreadyExistsException(String message){
        super(message);
    }
}
