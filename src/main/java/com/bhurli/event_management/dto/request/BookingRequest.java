package com.bhurli.event_management.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * DTO used for booking event tickets.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BookingRequest {

    @Schema(
            description = "Unique ID of the event to be booked",
            example = "1"
    )
    @NotNull(message = "Event id is required")
    private Long eventId;

    @Schema(
            description = "Number of tickets to book",
            example = "2"
    )
    @Min(value = 1, message = "Minimum one ticket is required")
    private Integer numberOfTickets;
}
