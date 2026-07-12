package com.bhurli.event_management.dto.request;

import com.bhurli.event_management.enums.PaymentMethod;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * DTO used for processing payment.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {

    @Schema(
            description = "Booking ID for which payment is being made",
            example = "1"
    )
    @NotNull(message = "Booking id is required")
    private Long bookingId;

    @Schema(
            description = "Preferred payment method",
            example = "UPI"
    )
    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;
}
