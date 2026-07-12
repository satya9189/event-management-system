package com.bhurli.event_management.exception;

/**
 * Thrown when booking validation fails.
 */
public class InvalidBookingException extends  RuntimeException{
    public InvalidBookingException(String message){
        super(message);
    }
}
