package com.bhurli.event_management.service;

import com.bhurli.event_management.dto.request.FeedbackRequest;
import com.bhurli.event_management.dto.response.FeedbackResponse;
import com.bhurli.event_management.entity.Feedback;

import java.util.List;

/**
 * Service interface for managing event feedback.
 */
public interface FeedbackService {

//    Feedback addFeedback(Feedback feedback);
//
//    Feedback getFeedbackById(Long id);
//
//    List<Feedback> getAllFeedbacks();
//
//    Feedback updateFeedback(Long id, Feedback feedback);
//
//    void deleteFeedback(Long id);

    FeedbackResponse addFeedback(FeedbackRequest request);

    List<FeedbackResponse> getFeedbackByEvent(Long eventId);
}
