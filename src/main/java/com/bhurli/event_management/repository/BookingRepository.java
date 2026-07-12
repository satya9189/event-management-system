package com.bhurli.event_management.repository;

import com.bhurli.event_management.entity.Booking;
import com.bhurli.event_management.entity.Event;
import com.bhurli.event_management.entity.User;
import com.bhurli.event_management.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface BookingRepository extends JpaRepository<Booking,Long> {

    List<Booking> findByUser(User user);

    List<Booking> findByEvent(Event event);

    List<Booking> findByBookingStatus(BookingStatus bookingStatus);

    Booking findByBookingReference(String bookingReference);

}
