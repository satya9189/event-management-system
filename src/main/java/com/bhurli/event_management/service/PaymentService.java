package com.bhurli.event_management.service;

import com.bhurli.event_management.dto.request.PaymentRequest;
import com.bhurli.event_management.dto.response.PaymentResponse;
import com.bhurli.event_management.entity.Payment;

import java.util.List;

/**
 * Service interface for managing payments.
 */

public interface PaymentService {

    PaymentResponse processPayment(PaymentRequest request);

    PaymentResponse getPaymentById(Long id);
}
