package com.bhurli.event_management.constant;

/**
 * It will Contains application-wide constant values.
 */

public class AppConstants {

    public static final String EMAIL_ALREADY_EXISTS = "Email already exists.";
    public static final String PHONE_ALREADY_EXISTS = "Phone no already exists.";
    public static final String ACCOUNT_INACTIVE = "Your Account is inactive";
    public static final String INVALID_EMAIL_OR_PASSWORD ="Invalid email or password" ;


    private AppConstants() {
    }

    // User Messages
    public static final String USER_NOT_FOUND = "User not found.";
    public static final String USER_ALREADY_EXISTS = "User already exists.";

    // Event Messages
    public static final String EVENT_NOT_FOUND = "Event not found.";

    // Booking Messages
    public static final String BOOKING_NOT_FOUND = "Booking not found.";
    public static final String NO_SEATS_AVAILABLE = "No seats available.";
    public static final String TICKET_LIMIT_EXCEEDED = "Maximum ticket limit exceeded.";

    // Payment Messages
    public static final String PAYMENT_NOT_FOUND = "Payment not found.";
    public static final String PAYMENT_FAILED = "Payment failed.";

    // Feedback Messages
    public static final String FEEDBACK_NOT_FOUND = "Feedback not found.";

    // Booking Reference Prefix
    public static final String BOOKING_PREFIX = "BKG";

    // JWT
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER = "Authorization";

}
