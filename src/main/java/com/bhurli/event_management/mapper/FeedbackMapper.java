package com.bhurli.event_management.mapper;

import com.bhurli.event_management.dto.request.FeedbackRequest;
import com.bhurli.event_management.dto.response.FeedbackResponse;
import com.bhurli.event_management.entity.Event;
import com.bhurli.event_management.entity.Feedback;
import com.bhurli.event_management.entity.User;

/**
 * Mapper class for converting Feedback Entity and DTO objects.
 */

public class FeedbackMapper {

    public static Feedback toEntity(FeedbackRequest request,
                                    User user,
                                    Event event) {

        return Feedback.builder()
                .user(user)
                .event(event)
                .rating(request.getRating())
                .comment(request.getComment())
                .build();

    }

    public static FeedbackResponse toResponse(Feedback feedback) {

        return FeedbackResponse.builder()
                .id(feedback.getId())
                .userName(
                        feedback.getUser().getFirstName()
                                + " "
                                + feedback.getUser().getLastName()
                )
                .eventName(feedback.getEvent().getEventName())
                .rating(feedback.getRating())
                .comment(feedback.getComment())
                .feedbackDate(feedback.getCreatedAt())
                .build();

    }
}
