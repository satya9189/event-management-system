package com.bhurli.event_management.repository;

import com.bhurli.event_management.entity.Event;
import com.bhurli.event_management.entity.User;
import com.bhurli.event_management.enums.EventCategory;
import com.bhurli.event_management.enums.EventStatus;
import com.bhurli.event_management.enums.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EventRepository
        extends JpaRepository<Event, Long>,
        JpaSpecificationExecutor<Event> {

    List<Event> findByEventStatus(EventStatus eventStatus);

    List<Event> findByEventType(EventType eventType);

    List<Event> findByEventCategory(EventCategory eventCategory);

    List<Event> findByOrganizer(User organizer);

    List<Event> findByCityIgnoreCase(String city);

}
