package com.bhurli.event_management.controller;

import com.bhurli.event_management.dto.request.PaymentRequest;
import com.bhurli.event_management.dto.response.PaymentResponse;
import com.bhurli.event_management.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(
        name = "Payment Management",
        description = "APIs for processing and retrieving payments."
)
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor

public class PaymentController {

    private final PaymentService paymentService;

    //swagger doc
    @Operation(
            summary = "Process Payment",
            description = "Processes payment for a booking."
    )

    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "201",
                    description = "Payment processed successfully"),

            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid payment request"),

            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"),

            @ApiResponse(
                    responseCode = "404",
                    description = "Booking not found")

    })

    @PostMapping
    public ResponseEntity<PaymentResponse> processPayment(
            @Valid @RequestBody PaymentRequest request) {

        PaymentResponse response =
                paymentService.processPayment(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //swagger doc
    @Operation(
            summary = "Get Payment By ID",
            description = "Returns payment details using payment ID."
    )

    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Payment fetched successfully"),

            @ApiResponse(
                    responseCode = "404",
                    description = "Payment not found")

    })

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPaymentById(
            @PathVariable Long id) {

        PaymentResponse response =
                paymentService.getPaymentById(id);

        return ResponseEntity.ok(response);
    }

}
