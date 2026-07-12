package com.bhurli.event_management.service;

import com.bhurli.event_management.dto.request.EventRequest;
import com.bhurli.event_management.dto.response.EventResponse;
import com.bhurli.event_management.dto.response.PageResponse;
import com.bhurli.event_management.entity.Event;
import com.bhurli.event_management.enums.EventCategory;
import com.bhurli.event_management.enums.EventStatus;
import com.bhurli.event_management.enums.EventType;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Service interface for managing events.
 *
 * It defines business operations related to event creation,
 * retrieval, update and deletion.
 */
public interface EventService {

//    Event createEvent(Event event);
//
//    Event getEventById(Long id);
//
//    List<Event> getAllEvents();
//
//    Event updateEvent(Long id, Event event);
//
//    void deleteEvent(Long id);
EventResponse createEvent(EventRequest request);

    EventResponse getEventById(Long id);

    List<EventResponse> getAllEvents();


    EventResponse updateEvent(Long id, EventRequest request);

    void deleteEvent(Long id);

    //custom pagination
    PageResponse<EventResponse> getAllEventsWithPagination(
            int page,
            int size,
            String sortBy,
            String direction
    );

    //search api
    List<EventResponse> getEventsByStatus(EventStatus status);

    List<EventResponse> getEventsByType(EventType type);

    List<EventResponse> getEventsByCategory(EventCategory category);

    List<EventResponse> getEventsByCity(String city);

    //specification
    List<EventResponse> filterEvents(
            String eventName,
            String city,
            EventCategory category,
            EventType type,
            EventStatus status
    );
}
