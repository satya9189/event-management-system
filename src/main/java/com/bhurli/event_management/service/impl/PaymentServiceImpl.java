package com.bhurli.event_management.service.impl;

import com.bhurli.event_management.dto.request.PaymentRequest;
import com.bhurli.event_management.dto.response.PaymentResponse;
import com.bhurli.event_management.repository.BookingRepository;
import com.bhurli.event_management.repository.PaymentRepository;
import com.bhurli.event_management.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.bhurli.event_management.constant.AppConstants;
import com.bhurli.event_management.entity.Booking;
import com.bhurli.event_management.entity.Payment;
import com.bhurli.event_management.enums.BookingStatus;
import com.bhurli.event_management.enums.PaymentStatus;
import com.bhurli.event_management.exception.ResourceNotFoundException;
import com.bhurli.event_management.mapper.PaymentMapper;

import java.util.UUID;

@Service
@RequiredArgsConstructor

public class PaymentServiceImpl implements PaymentService{


    private final PaymentRepository paymentRepository;

    private final BookingRepository bookingRepository;

    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        // Find booking
        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.BOOKING_NOT_FOUND));

        // Booking cancelled
        if (booking.getBookingStatus() == BookingStatus.CANCELLED) {
            throw new IllegalArgumentException("Booking has been cancelled.");
        }

        // Already paid
        if (booking.getPaymentStatus() == PaymentStatus.SUCCESS) {
            throw new IllegalArgumentException("Payment already completed.");
        }

        // Create payment
        Payment payment = PaymentMapper.toEntity(request, booking);

        payment.setTransactionId(
                "TXN" + UUID.randomUUID().toString().replace("-", "")
                        .substring(0, 10)
        );

        payment.setPaymentGateway("Mock Gateway");

        payment.setPaymentStatus(PaymentStatus.SUCCESS);

        // Update booking
        booking.setPaymentStatus(PaymentStatus.SUCCESS);

        bookingRepository.save(booking);

        Payment savedPayment = paymentRepository.save(payment);

        return PaymentMapper.toResponse(savedPayment);
    }

    @Override
    public PaymentResponse getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.PAYMENT_NOT_FOUND));

        return PaymentMapper.toResponse(payment);
    }
}
