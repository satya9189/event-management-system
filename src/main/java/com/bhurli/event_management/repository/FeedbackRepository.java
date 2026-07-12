package com.bhurli.event_management.repository;

import com.bhurli.event_management.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bhurli.event_management.entity.Event;
import com.bhurli.event_management.entity.User;

import java.util.List;

public interface FeedbackRepository  extends JpaRepository<Feedback,Long> {
    List<Feedback> findByEvent(Event event);

    List<Feedback> findByUser(User user);

    boolean existsByUserAndEvent(User user, Event event);

}
