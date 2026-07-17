package com.bhurli.event_management.service.impl;

import com.bhurli.event_management.dto.request.BookingRequest;
import com.bhurli.event_management.dto.response.BookingResponse;
import com.bhurli.event_management.repository.BookingRepository;
import com.bhurli.event_management.repository.EventRepository;
import com.bhurli.event_management.repository.UserRepository;
import com.bhurli.event_management.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.bhurli.event_management.constant.AppConstants;
import com.bhurli.event_management.entity.Booking;
import com.bhurli.event_management.entity.Event;
import com.bhurli.event_management.entity.User;
import com.bhurli.event_management.enums.BookingStatus;
import com.bhurli.event_management.enums.EventStatus;
import com.bhurli.event_management.exception.ResourceNotFoundException;
import com.bhurli.event_management.mapper.BookingMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.access.AccessDeniedException;

import java.math.BigDecimal;
import java.util.UUID;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.USER_NOT_FOUND));

    }

    private void validateBookingOwnership(
            Booking booking,
            User currentUser
    ) {

        if (!booking.getUser().getId().equals(currentUser.getId())) {

            throw new AccessDeniedException(
                    "You are not authorized to access this booking."
            );

        }

    }

    @Override
    public BookingResponse createBooking(BookingRequest request) {
        // Logged-in user
        User user = getCurrentUser();

        // Find event
        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.EVENT_NOT_FOUND));

        // Event should be upcoming
        if (event.getEventStatus() != EventStatus.UPCOMING) {
            throw new IllegalArgumentException("Booking is allowed only for upcoming events.");
        }

        // Seat validation
        if (request.getNumberOfTickets() > event.getAvailableSeats()) {
            throw new IllegalArgumentException(AppConstants.NO_SEATS_AVAILABLE);
        }

        // Max ticket validation
        if (request.getNumberOfTickets() > event.getMaxTicketsPerUser()) {
            throw new IllegalArgumentException(AppConstants.TICKET_LIMIT_EXCEEDED);
        }

        // Total amount
        BigDecimal totalAmount =
                event.getTicketPrice()
                        .multiply(BigDecimal.valueOf(request.getNumberOfTickets()));

        // Create booking
        Booking booking =
                BookingMapper.toEntity(request, user, event, totalAmount);

        booking.setBookingReference(
                AppConstants.BOOKING_PREFIX + UUID.randomUUID().toString().substring(0,8)
        );

        booking.setBookingStatus(BookingStatus.CONFIRMED);

        // Update seats
        event.setAvailableSeats(
                event.getAvailableSeats() - request.getNumberOfTickets()
        );

        eventRepository.save(event);

        Booking savedBooking = bookingRepository.save(booking);

        return BookingMapper.toResponse(savedBooking);
    }

    @Override
    public BookingResponse getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.BOOKING_NOT_FOUND));

        User currentUser = getCurrentUser();

        validateBookingOwnership(booking, currentUser);

        return BookingMapper.toResponse(booking);
    }

    @Override
    public List<BookingResponse> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(BookingMapper::toResponse)
                .toList();
    }

    @Override
    public List<BookingResponse> getMyBookings() {

        User currentUser = getCurrentUser();

        return bookingRepository.findByUser(currentUser)
                .stream()
                .map(BookingMapper::toResponse)
                .toList();

    }

    @Override
    public BookingResponse cancelBooking(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.BOOKING_NOT_FOUND));

        User currentUser = getCurrentUser();

        validateBookingOwnership(booking, currentUser);

        // Already cancelled
        if (booking.getBookingStatus() == BookingStatus.CANCELLED) {
            return BookingMapper.toResponse(booking);
        }

        // Restore seats
        Event event = booking.getEvent();

        event.setAvailableSeats(
                event.getAvailableSeats() + booking.getNumberOfTickets()
        );

        // Cancel booking
        booking.setBookingStatus(BookingStatus.CANCELLED);

        eventRepository.save(event);

        Booking updatedBooking = bookingRepository.save(booking);

        return BookingMapper.toResponse(updatedBooking);

    }
}
