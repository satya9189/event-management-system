package com.bhurli.event_management.controller;

import com.bhurli.event_management.dto.request.FeedbackRequest;
import com.bhurli.event_management.dto.response.FeedbackResponse;
import com.bhurli.event_management.service.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Feedback Management",
        description = "APIs for submitting and viewing event feedback."
)
@RestController
@RequestMapping("/api/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    //swagger
    @Operation(
            summary = "Submit Feedback",
            description = "Allows a user to submit feedback for an event."
    )

    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "201",
                    description = "Feedback submitted successfully"),

            @ApiResponse(
                    responseCode = "400",
                    description = "Validation failed"),

            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"),

            @ApiResponse(
                    responseCode = "404",
                    description = "Event not found")

    })

    @PostMapping
    public ResponseEntity<FeedbackResponse> addFeedback(
            @Valid @RequestBody FeedbackRequest request) {

        FeedbackResponse response =
                feedbackService.addFeedback(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //swagger
    @Operation(
            summary = "Get Feedback By Event",
            description = "Returns all feedback submitted for a specific event."
    )

    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Feedback fetched successfully"),

            @ApiResponse(
                    responseCode = "404",
                    description = "Event not found")

    })

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<FeedbackResponse>> getFeedbackByEvent(
            @PathVariable Long eventId) {

        return ResponseEntity.ok(
                feedbackService.getFeedbackByEvent(eventId)
        );
    }

}
