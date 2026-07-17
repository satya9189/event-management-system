package com.bhurli.event_management.service;

import com.bhurli.event_management.dto.request.BookingRequest;
import com.bhurli.event_management.dto.response.BookingResponse;
import com.bhurli.event_management.entity.Booking;

import java.util.List;

/**
 * Service interface for managing event bookings.
 */

public interface BookingService {

//    Booking createBooking(Booking booking);
//
//    Booking getBookingById(Long id);
//
//    List<Booking> getAllBookings();
//
//    Booking cancelBooking(Long id);

    BookingResponse createBooking(BookingRequest request);

    BookingResponse getBookingById(Long id);

    List<BookingResponse> getAllBookings();

    List<BookingResponse> getMyBookings();

    BookingResponse cancelBooking(Long id);
}
