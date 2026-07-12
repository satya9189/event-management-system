package com.bhurli.event_management.exception;

/**
 * Thrown when a user is not authorized to perform an action.
 */

public class UnauthorizedException extends RuntimeException {
    public  UnauthorizedException (String message){
        super(message);
    }
}
