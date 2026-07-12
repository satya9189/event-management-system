package com.bhurli.event_management.service.impl;

import com.bhurli.event_management.dto.request.FeedbackRequest;
import com.bhurli.event_management.dto.response.FeedbackResponse;
import com.bhurli.event_management.repository.BookingRepository;
import com.bhurli.event_management.repository.EventRepository;
import com.bhurli.event_management.repository.FeedbackRepository;
import com.bhurli.event_management.repository.UserRepository;
import com.bhurli.event_management.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.bhurli.event_management.constant.AppConstants;
import com.bhurli.event_management.entity.Event;
import com.bhurli.event_management.entity.Feedback;
import com.bhurli.event_management.entity.User;
import com.bhurli.event_management.exception.ResourceAlreadyExistsException;
import com.bhurli.event_management.exception.ResourceNotFoundException;
import com.bhurli.event_management.mapper.FeedbackMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Service
@RequiredArgsConstructor

public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    private final BookingRepository bookingRepository;

    @Override
    public FeedbackResponse addFeedback(FeedbackRequest request) {
        // Logged-in user
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.USER_NOT_FOUND));

        // Find event
        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.EVENT_NOT_FOUND));

        // One feedback per user per event
        if (feedbackRepository.existsByUserAndEvent(user, event)) {
            throw new ResourceAlreadyExistsException(
                    "Feedback already submitted for this event.");
        }

        // Save feedback
        Feedback feedback =
                FeedbackMapper.toEntity(request, user, event);

        Feedback savedFeedback =
                feedbackRepository.save(feedback);

        return FeedbackMapper.toResponse(savedFeedback);
    }

    @Override
    public List<FeedbackResponse> getFeedbackByEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.EVENT_NOT_FOUND));

        return feedbackRepository.findByEvent(event)
                .stream()
                .map(FeedbackMapper::toResponse)
                .toList();
    }

}
