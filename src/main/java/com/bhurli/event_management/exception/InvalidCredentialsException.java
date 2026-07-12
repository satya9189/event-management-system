package com.bhurli.event_management.exception;

/**
 * Thrown when user provides invalid login credentials.
 */

public class InvalidCredentialsException extends RuntimeException{

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
