package com.bhurli.event_management.dto.request;

import com.bhurli.event_management.enums.EventCategory;
import com.bhurli.event_management.enums.EventType;
import jakarta.validation.constraints.*;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO used for creating or updating an event.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRequest {

    @Schema(
            description = "Name of the event",
            example = "Spring Boot Workshop"
    )
    @NotBlank(message = "Event name is required")
    private String eventName;

    @Schema(
            description = "Type of event",
            example = "WORKSHOP"
    )
    @NotNull(message = "Event type is required")
    private EventType eventType;

    @Schema(
            description = "Category of event",
            example = "TECH"
    )
    @NotNull(message = "Event category is required")
    private EventCategory eventCategory;

    @Schema(
            description = "Detailed event description",
            example = "Hands-on Spring Boot Workshop"
    )
    @NotBlank(message = "Description is required")
    private String description;

    @Schema(
            description = "Venue of the event",
            example = "TIT Auditorium"
    )
    @NotBlank(message = "Venue is required")
    private String venue;

    @NotBlank(message = "Address is required")
    private String address;

    @Schema(
            description = "City where event will be held",
            example = "Bhopal"
    )
    @NotBlank(message = "City is required")
    private String city;

    @Schema(
            description = "State whwre  event if held",
            example = "Delhi"
    )
    @NotBlank(message = "State is required")
    private String state;

    @NotNull(message = "Start date and time is required")
    private LocalDateTime startDateTime;

    @NotNull(message = "End date and time is required")
    private LocalDateTime endDateTime;

    @Positive(message = "Total seats must be greater than zero")
    private Integer totalSeats;

    @Positive(message = "Maximum tickets per user must be greater than zero")
    private Integer maxTicketsPerUser;

    @DecimalMin(value = "0.0", inclusive = true,
            message = "Ticket price cannot be negative")
    private BigDecimal ticketPrice;

    private String bannerUrl;
}
