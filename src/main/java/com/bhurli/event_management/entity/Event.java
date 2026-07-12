package com.bhurli.event_management.entity;

import com.bhurli.event_management.enums.EventCategory;
import com.bhurli.event_management.enums.EventStatus;
import com.bhurli.event_management.enums.EventType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents an event in the Event Management System.
 * Users can view and book tickets for available events.
 * Events are created and managed by organizers.
 */

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 150)
    private  String eventName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventType eventType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventCategory eventCategory;

    @Lob
    @Column(nullable = false)
    private  String description;

    @Column(nullable = false)
    private String venue;

    @Column(length = 300)
    private String address;

    @Column(nullable = false)
    private  String city;

    @Column(length = 100)
    private String state;

    @Column(nullable = false)
    private LocalDateTime startDateTime;

    @Column(nullable = false)
    private LocalDateTime endDateTime;

    @Column(nullable = false)
    private Integer totalSeats;

    @Column(nullable = false)
    private Integer availableSeats;

    @Column(nullable = false)
    private  Integer maxTicketsPerUser;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal ticketPrice;

    @Column(length = 500)
    private String bannerUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventStatus eventStatus;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id", nullable = false)
    private User organizer;  //user to event relatipn

    //booking add
    @OneToMany(mappedBy = "event")
    private List<Booking> bookings;

    //fedback relation
    @OneToMany(mappedBy = "event")
    private List<Feedback> feedbacks;

    // TODO: Add organizer relationship using @ManyToOne
}
