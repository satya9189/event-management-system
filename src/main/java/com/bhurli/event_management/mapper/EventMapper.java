package com.bhurli.event_management.mapper;

import com.bhurli.event_management.dto.request.EventRequest;
import com.bhurli.event_management.dto.response.EventResponse;
import com.bhurli.event_management.entity.Event;
import com.bhurli.event_management.enums.EventStatus;

/**
 * Mapper class for converting Event Entity and DTO objects.
 */

public class EventMapper {

    public static Event toEntity(EventRequest request) {

        return Event.builder()
                .eventName(request.getEventName())
                .eventType(request.getEventType())
                .eventCategory(request.getEventCategory())
                .description(request.getDescription())
                .venue(request.getVenue())
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .startDateTime(request.getStartDateTime())
                .endDateTime(request.getEndDateTime())
                .totalSeats(request.getTotalSeats())
                .availableSeats(request.getTotalSeats())
                .maxTicketsPerUser(request.getMaxTicketsPerUser())
                .ticketPrice(request.getTicketPrice())
                .bannerUrl(request.getBannerUrl())
                .eventStatus(EventStatus.UPCOMING)
                .build();

    }


    public static EventResponse toResponse(Event event) {

        return EventResponse.builder()
                .id(event.getId())
                .eventName(event.getEventName())
                .eventType(event.getEventType())
                .eventCategory(event.getEventCategory())
                .description(event.getDescription())
                .venue(event.getVenue())
                .address(event.getAddress())
                .city(event.getCity())
                .state(event.getState())
                .startDateTime(event.getStartDateTime())
                .endDateTime(event.getEndDateTime())
                .totalSeats(event.getTotalSeats())
                .availableSeats(event.getAvailableSeats())
                .maxTicketsPerUser(event.getMaxTicketsPerUser())
                .ticketPrice(event.getTicketPrice())
                .bannerUrl(event.getBannerUrl())
                .eventStatus(event.getEventStatus())
                .organizerName(
                event.getOrganizer() != null
                        ? event.getOrganizer().getFirstName() + " " + event.getOrganizer().getLastName()
                        : null
        )
                .createdAt(event.getCreatedAt())
                .build();

    }
}
