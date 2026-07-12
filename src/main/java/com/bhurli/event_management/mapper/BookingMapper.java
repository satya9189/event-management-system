package com.bhurli.event_management.mapper;

import com.bhurli.event_management.dto.request.BookingRequest;
import com.bhurli.event_management.dto.response.BookingResponse;
import com.bhurli.event_management.entity.Booking;
import com.bhurli.event_management.entity.Event;
import com.bhurli.event_management.entity.User;
import com.bhurli.event_management.enums.BookingStatus;
import com.bhurli.event_management.enums.PaymentStatus;

import java.math.BigDecimal;

/**
 * Mapper class for converting Booking Entity and DTO objects.
 */

public class BookingMapper {

    public static Booking toEntity(BookingRequest request,
                                   User user,
                                   Event event,
                                   BigDecimal totalAmount) {

        return Booking.builder()
                .user(user)
                .event(event)
                .numberOfTickets(request.getNumberOfTickets())
                .totalAmount(totalAmount)
                .bookingStatus(BookingStatus.PENDING)
                .paymentStatus(PaymentStatus.PENDING)
                .build();

    }

    public static BookingResponse toResponse(Booking booking) {

        return BookingResponse.builder()
                .id(booking.getId())
                .bookingReference(booking.getBookingReference())
                .eventName(booking.getEvent().getEventName())
                .userName(
                        booking.getUser().getFirstName()
                                + " "
                                + booking.getUser().getLastName()
                )
                .numberOfTickets(booking.getNumberOfTickets())
                .totalAmount(booking.getTotalAmount())
                .bookingStatus(booking.getBookingStatus())
                .paymentStatus(booking.getPaymentStatus())
                .createdAt(booking.getCreatedAt())
                .build();

    }
}
