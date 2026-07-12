package com.bhurli.event_management.mapper;

import com.bhurli.event_management.dto.request.PaymentRequest;
import com.bhurli.event_management.dto.response.PaymentResponse;
import com.bhurli.event_management.entity.Booking;
import com.bhurli.event_management.entity.Payment;
import com.bhurli.event_management.enums.PaymentStatus;

/**
 * Mapper class for converting Payment Entity and DTO objects.
 */

public class PaymentMapper {

    public static Payment toEntity(PaymentRequest request, Booking booking) {

        return Payment.builder()
                .booking(booking)
                .amount(booking.getTotalAmount())
                .paymentMethod(request.getPaymentMethod())
                .paymentStatus(PaymentStatus.PENDING)
                .build();

    }

    public static PaymentResponse toResponse(Payment payment) {

        return PaymentResponse.builder()
                .id(payment.getId())
                .eventName(payment.getBooking().getEvent().getEventName())
                .userName(
                        payment.getBooking().getUser().getFirstName()
                                + " "
                                + payment.getBooking().getUser().getLastName()
                )
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .paymentStatus(payment.getPaymentStatus())
                .transactionId(payment.getTransactionId())
                .paymentDate(payment.getCreatedAt())
                .build();

    }
}
