package com.bhurli.event_management.dto.response;

import com.bhurli.event_management.enums.BookingStatus;
import com.bhurli.event_management.enums.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO returned after booking-related operations.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {

    private Long id;

    @Schema(
            description = "System generated booking reference",
            example = "BKG59109634",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private String bookingReference;

    @Schema(
            description = "Name of the booked event",
            example = "Spring Boot Workshop"
    )
    private String eventName;

    @Schema(
            description = "Name of the user who booked the event",
            example = "Satyajeet Pandey"
    )
    private String userName;

    @Schema(
            description = "Total number of booked tickets",
            example = "2"
    )
    private Integer numberOfTickets;

    @Schema(
            description = "Total payable amount",
            example = "1598.00"
    )
    private BigDecimal totalAmount;

    @Schema(
            description = "Current booking status",
            example = "CONFIRMED"
    )
    private BookingStatus bookingStatus;

    @Schema(
            description = "Current payment status",
            example = "PENDING"
    )
    private PaymentStatus paymentStatus;

    @Schema(
            description = "Booking creation date and time",
            example = "2026-07-10T10:57:02",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime createdAt;
}
