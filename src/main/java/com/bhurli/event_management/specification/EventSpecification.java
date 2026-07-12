package com.bhurli.event_management.specification;

import com.bhurli.event_management.entity.Event;
import com.bhurli.event_management.enums.EventCategory;
import com.bhurli.event_management.enums.EventStatus;
import com.bhurli.event_management.enums.EventType;
import org.springframework.data.jpa.domain.Specification;

public class EventSpecification {

    public static Specification<Event> hasCity(String city) {

        return (root, query, criteriaBuilder) ->
                city == null
                        ? null
                        : criteriaBuilder.equal( criteriaBuilder.lower(root.get("city")),
                        city.toLowerCase());

    }

    public static Specification<Event> hasCategory(EventCategory category) {

        return (root, query, criteriaBuilder) ->
                category == null
                        ? null
                        : criteriaBuilder.equal(root.get("eventCategory"), category);

    }

    public static Specification<Event> hasType(EventType type) {

        return (root, query, criteriaBuilder) ->
                type == null
                        ? null
                        : criteriaBuilder.equal(root.get("eventType"), type);

    }

    public static Specification<Event> hasStatus(EventStatus status) {

        return (root, query, criteriaBuilder) ->
                status == null
                        ? null
                        : criteriaBuilder.equal(root.get("eventStatus"), status);

    }

    public static Specification<Event> hasEventName(String eventName) {

        return (root, query, criteriaBuilder) ->

                eventName == null || eventName.isBlank()

                        ? null

                        : criteriaBuilder.like(

                        criteriaBuilder.lower(root.get("eventName")),

                        "%" + eventName.toLowerCase() + "%"

                );

    }
}
