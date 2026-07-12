package com.bhurli.event_management.repository;

import com.bhurli.event_management.entity.Booking;
import com.bhurli.event_management.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

    Optional<Payment> findByTransactionId(String transactionId);

    Optional<Payment> findByBooking(Booking booking);

}
