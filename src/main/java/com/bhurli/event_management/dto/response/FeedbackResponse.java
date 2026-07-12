package com.bhurli.event_management.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

/**
 * DTO returned after feedback-related operations.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class FeedbackResponse {

    @Schema(
            description = "Unique feedback ID",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Schema(
            description = "Name of the user who submitted feedback",
            example = "Satyajeet Pandey"
    )
    private String userName;

    @Schema(
            description = "Event name",
            example = "Spring Boot Workshop"
    )
    private String eventName;

    @Schema(
            description = "User rating",
            example = "5"
    )
    private Integer rating;

    @Schema(
            description = "Feedback comment",
            example = "Excellent event with well-organized sessions."
    )
    private String comment;

    @Schema(
            description = "Date and time when feedback was submitted",
            example = "2026-07-10T16:30:00",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime feedbackDate;
}
