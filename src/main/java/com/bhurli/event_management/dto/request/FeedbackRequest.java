package com.bhurli.event_management.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * DTO used for submitting feedback.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackRequest {

    @Schema(
            description = "Event ID for which feedback is submitted",
            example = "1"
    )
    @NotNull(message = "Event id is required")
    private Long eventId;

    @Schema(
            description = "Rating given by the user (1 to 5)",
            example = "5"
    )
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot be greater than 5")
    private Integer rating;

    @Schema(
            description = "User feedback comment",
            example = "Excellent event with well-organized sessions."
    )
    @NotBlank(message = "Comment is required")
    private String comment;
}
