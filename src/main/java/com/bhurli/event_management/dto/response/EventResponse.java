package com.bhurli.event_management.dto.response;

import com.bhurli.event_management.enums.EventCategory;
import com.bhurli.event_management.enums.EventStatus;
import com.bhurli.event_management.enums.EventType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO returned after event-related operations.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventResponse {

    @Schema(
            description = "Unique Event ID",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Name of the event",
            example = "Spring Boot Workshop"
    )
    private String eventName;

    @Schema(
            description = "Type of event",
            example = "WORKSHOP"
    )
    private EventType eventType;

    @Schema(
            description = "Category of the event",
            example = "TECH"
    )
    private EventCategory eventCategory;

    @Schema(
            description = "Detailed description of the event",
            example = "Hands-on workshop covering Spring Boot and Microservices."
    )
    private String description;

    @Schema(
            description = "Venue",
            example = "TIT Auditorium"
    )
    private String venue;

    @Schema(
            description = "Complete address of the venue",
            example = "Kolar Road"
    )
    private String address;

    @Schema(
            description = "City",
            example = "Bhopal"
    )
    private String city;

    @Schema(
            description = "State where the event will be organized",
            example = "Madhya Pradesh"
    )
    private String state;

    @Schema(
            description = "Event start date and time",
            example = "2026-10-15T10:00:00"
    )
    private LocalDateTime startDateTime;

    @Schema(
            description = "Event end date and time",
            example = "2026-10-15T17:00:00"
    )
    private LocalDateTime endDateTime;

    @Schema(
            description = "Total number of seats available",
            example = "200"
    )
    private Integer totalSeats;

    @Schema(
            description = "Number of seats currently available",
            example = "150"
    )
    private Integer availableSeats;

    @Schema(
            description = "Maximum number of tickets a user can book",
            example = "3"
    )
    private Integer maxTicketsPerUser;

    @Schema(
            description = "Ticket Price",
            example = "999"
    )
    private BigDecimal ticketPrice;

    @Schema(
            description = "Event Banner URL",
            example = "https://example.com/banner.jpg"
    )
    private String bannerUrl;

    @Schema(
            description = "Current Event Status",
            example = "UPCOMING"
    )
    private EventStatus eventStatus;

    @Schema(
            description = "Organizer Name",
            example = "Satyajeet Pandey"
    )
    private String organizerName;

    @Schema(
            description = "Creation Timestamp",
            example = "2026-07-10T18:30:00"
    )
    private LocalDateTime createdAt;
}
