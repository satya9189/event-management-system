package com.bhurli.event_management.dto.response;

import com.bhurli.event_management.enums.PaymentMethod;
import com.bhurli.event_management.enums.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO returned after payment-related operations.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {

    @Schema(
            description = "Unique payment ID",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Schema(
            description = "Event name",
            example = "Spring Boot Workshop"
    )
    private String eventName;

    @Schema(
            description = "User who made the payment",
            example = "Satyajeet Pandey"
    )
    private String userName;

    @Schema(
            description = "Paid amount",
            example = "1598.00"
    )
    private BigDecimal amount;

    @Schema(
            description = "Payment method used",
            example = "UPI"
    )
    private PaymentMethod paymentMethod;

    @Schema(
            description = "Payment status",
            example = "SUCCESS"
    )
    private PaymentStatus paymentStatus;

    @Schema(
            description = "Generated transaction ID",
            example = "TXNa37ec02dee",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private String transactionId;

    @Schema(
            description = "Payment completion date and time",
            example = "2026-07-10T14:04:49",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime paymentDate;

}
