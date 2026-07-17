package com.bhurli.event_management.service.impl;

import com.bhurli.event_management.dto.response.PageResponse;
import com.bhurli.event_management.entity.Booking;
import com.bhurli.event_management.entity.Event;
import com.bhurli.event_management.entity.User;
import com.bhurli.event_management.enums.EventCategory;
import com.bhurli.event_management.enums.EventStatus;
import com.bhurli.event_management.enums.EventType;
import com.bhurli.event_management.exception.ResourceNotFoundException;
import com.bhurli.event_management.mapper.EventMapper;
import com.bhurli.event_management.constant.AppConstants;
import com.bhurli.event_management.repository.BookingRepository;
import com.bhurli.event_management.specification.EventSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.bhurli.event_management.dto.request.EventRequest;
import com.bhurli.event_management.dto.response.EventResponse;
import com.bhurli.event_management.repository.EventRepository;
import com.bhurli.event_management.repository.UserRepository;
import com.bhurli.event_management.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.bhurli.event_management.enums.Role;
import com.bhurli.event_management.exception.UnauthorizedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    @Override
    public EventResponse createEvent(EventRequest request) {

        User organizer = getCurrentUser();

        Event event = EventMapper.toEntity(request);

        event.setOrganizer(organizer);

        event.setAvailableSeats(request.getTotalSeats());

        event.setEventStatus(EventStatus.UPCOMING);

        Event savedEvent = eventRepository.save(event);

        return EventMapper.toResponse(savedEvent);
    }

    @Override
    public EventResponse getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.EVENT_NOT_FOUND));

        return EventMapper.toResponse(event);
    }

    @Override
    public List<EventResponse> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(EventMapper::toResponse)
                .toList();
    }
    @Override
    public PageResponse<EventResponse> getAllEventsWithPagination(
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Event> eventPage = eventRepository.findAll(pageable);

        return PageResponse.<EventResponse>builder()
                .content(
                        eventPage.getContent()
                                .stream()
                                .map(EventMapper::toResponse)
                                .toList()
                )
                .page(eventPage.getNumber())
                .size(eventPage.getSize())
                .totalElements(eventPage.getTotalElements())
                .totalPages(eventPage.getTotalPages())
                .first(eventPage.isFirst())
                .last(eventPage.isLast())
                .build();
    }

    @Override
    public EventResponse updateEvent(Long id, EventRequest request) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.EVENT_NOT_FOUND));

            User currentUser = getCurrentUser();

        validateEventOwnership(event, currentUser);

        event.setEventName(request.getEventName());
        event.setEventType(request.getEventType());
        event.setEventCategory(request.getEventCategory());
        event.setDescription(request.getDescription());
        event.setVenue(request.getVenue());
        event.setAddress(request.getAddress());
        event.setCity(request.getCity());
        event.setState(request.getState());
        event.setStartDateTime(request.getStartDateTime());
        event.setEndDateTime(request.getEndDateTime());
        event.setTotalSeats(request.getTotalSeats());
        event.setAvailableSeats(request.getTotalSeats());
        event.setMaxTicketsPerUser(request.getMaxTicketsPerUser());
        event.setTicketPrice(request.getTicketPrice());
        event.setBannerUrl(request.getBannerUrl());

        Event updatedEvent = eventRepository.save(event);

        return EventMapper.toResponse(updatedEvent);
    }

    @Override
    public void deleteEvent(Long id) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.EVENT_NOT_FOUND));

        User currentUser = getCurrentUser();

        validateEventOwnership(event, currentUser);

        List<Booking> bookings = bookingRepository.findByEvent(event);

        if (!bookings.isEmpty()) {
            throw new IllegalStateException(
                    "Cannot delete event because bookings already exist.");
        }

        eventRepository.delete(event);

    }

    @Override
    public EventResponse cancelEvent(Long id) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                AppConstants.EVENT_NOT_FOUND));

        User currentUser = getCurrentUser();

        validateEventOwnership(event, currentUser);

        event.setEventStatus(EventStatus.CANCELLED);

        Event updatedEvent = eventRepository.save(event);

        return EventMapper.toResponse(updatedEvent);

    }

    // search Api's
    @Override
    public List<EventResponse> getEventsByStatus(EventStatus status) {

        return eventRepository.findByEventStatus(status)
                .stream()
                .map(EventMapper::toResponse)
                .toList();
    }

    @Override
    public List<EventResponse> getEventsByType(EventType type) {

        return eventRepository.findByEventType(type)
                .stream()
                .map(EventMapper::toResponse)
                .toList();
    }
    @Override
    public List<EventResponse> getEventsByCategory(EventCategory category) {

        return eventRepository.findByEventCategory(category)
                .stream()
                .map(EventMapper::toResponse)
                .toList();
    }
    @Override
    public List<EventResponse> getEventsByCity(String city) {

        return eventRepository.findByCityIgnoreCase(city)
                .stream()
                .map(EventMapper::toResponse)
                .toList();
    }

    @Override
    public List<EventResponse> filterEvents(
            String eventName,
            String city,
            EventCategory category,
            EventType type,
            EventStatus status) {

        Specification<Event> specification =
                Specification.<Event>unrestricted()
                        .and(EventSpecification.hasEventName(eventName))
                        .and(EventSpecification.hasCity(city))
                        .and(EventSpecification.hasCategory(category))
                        .and(EventSpecification.hasType(type))
                        .and(EventSpecification.hasStatus(status));

        return eventRepository.findAll(specification)

                .stream()

                .map(EventMapper::toResponse)

                .toList();
    }

    /**
     * Returns the currently authenticated user.
     */
    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return userRepository.findById(currentUser.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                AppConstants.USER_NOT_FOUND));
    }

    /**
     * Ensures that only the event owner or an admin
     * can update or delete an event.
     */
    private void validateEventOwnership(
            Event event,
            User currentUser) {

        if (!event.getOrganizer().getId().equals(currentUser.getId())
                && currentUser.getRole() != Role.ADMIN) {

            throw new UnauthorizedException(
                    "You are not authorized to modify this event.");
        }
    }

}
