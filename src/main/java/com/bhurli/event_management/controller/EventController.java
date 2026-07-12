package com.bhurli.event_management.controller;


import com.bhurli.event_management.dto.request.EventRequest;
//import com.bhurli.event_management.dto.response.ApiResponse;
import com.bhurli.event_management.dto.response.EventResponse;
import com.bhurli.event_management.dto.response.PageResponse;
import com.bhurli.event_management.enums.EventCategory;
import com.bhurli.event_management.enums.EventStatus;
import com.bhurli.event_management.enums.EventType;
import com.bhurli.event_management.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;


@Tag(
        name = "Event Management",
        description = "APIs for creating, updating, deleting and viewing events."
)
@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @Operation(
            summary = "Create Event",
            description = "Creates a new event. Only ADMIN and ORGANIZER can create events."
    )

    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "201",
                    description = "Event created successfully"),

            @ApiResponse(
                    responseCode = "400",
                    description = "Validation failed"),

            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"),

            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied")

    })

    @PreAuthorize("hasAnyRole('ADMIN','ORGANIZER')")
    @PostMapping
    public ResponseEntity<EventResponse> createEvent(
            @Valid @RequestBody EventRequest request) {

        EventResponse response = eventService.createEvent(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Event By ID",
            description = "Returns the details of a specific event."
    )

    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Event found"),

            @ApiResponse(
                    responseCode = "404",
                    description = "Event not found")

    })
    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEventById(
            @PathVariable Long id) {

        EventResponse response = eventService.getEventById(id);

        return ResponseEntity.ok(response);
    }

    //swagger
    @Operation(
            summary = "Get All Events",
            description = "Returns the list of all available events."
    )

    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Events fetched successfully")

    })
    @GetMapping
    public ResponseEntity<List<EventResponse>> getAllEvents() {

        List<EventResponse> response = eventService.getAllEvents();

        return ResponseEntity.ok(response);
    }
    //paging
    @Operation(
            summary = "Get Events With Pagination",
            description = "Returns paginated and sorted list of events."
    )
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Events fetched successfully")

    })
    @GetMapping("/paginated")
    public ResponseEntity<PageResponse<EventResponse>> getAllEventsWithPagination(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "5") int size,

            @RequestParam(defaultValue = "eventName") String sortBy,

            @RequestParam(defaultValue = "asc") String direction) {

        PageResponse<EventResponse> response =
                eventService.getAllEventsWithPagination(
                        page,
                        size,
                        sortBy,
                        direction);

        return ResponseEntity.ok(response);
    }


    //swagger
    @Operation(
            summary = "Update Event",
            description = "Updates an existing event. Only the owner organizer or admin can update."
    )

    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Event updated successfully"),

            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"),

            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied"),

            @ApiResponse(
                    responseCode = "404",
                    description = "Event not found")

    })
    @PutMapping("/{id}")
    public ResponseEntity<EventResponse> updateEvent(
            @PathVariable Long id,
            @Valid @RequestBody EventRequest request) {

        EventResponse response = eventService.updateEvent(id, request);

        return ResponseEntity.ok(response);
    }

    //swagger documentization
    @Operation(
            summary = "Delete Event",
            description = "Deletes an event. Only the owner organizer or admin can delete."
    )

    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "204",
                    description = "Event deleted successfully"),

            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"),

            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied"),

            @ApiResponse(
                    responseCode = "404",
                    description = "Event not found")

    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {

        eventService.deleteEvent(id);

        return ResponseEntity.noContent().build();
    }

    //adding search APIs endpoints
    @Operation(
            summary = "Search Events By Status",
            description = "Returns all events having the specified status."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events fetched successfully")
    })
    @GetMapping("/search/status")
    public ResponseEntity<List<EventResponse>> getEventsByStatus(
            @RequestParam EventStatus status) {

        return ResponseEntity.ok(
                eventService.getEventsByStatus(status)
        );
    }

    @Operation(
            summary = "Search Events By Type",
            description = "Returns all events of the specified type."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events fetched successfully")
    })
    @GetMapping("/search/type")
    public ResponseEntity<List<EventResponse>> getEventsByType(
            @RequestParam EventType type) {

        return ResponseEntity.ok(
                eventService.getEventsByType(type)
        );
    }

    @Operation(
            summary = "Search Events By Category",
            description = "Returns all events of the specified category."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events fetched successfully")
    })
    @GetMapping("/search/category")
    public ResponseEntity<List<EventResponse>> getEventsByCategory(
            @RequestParam EventCategory category) {

        return ResponseEntity.ok(
                eventService.getEventsByCategory(category)
        );
    }
    @Operation(
            summary = "Search Events By City",
            description = "Returns all events available in the specified city."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events fetched successfully")
    })
    @GetMapping("/search/city")
    public ResponseEntity<List<EventResponse>> getEventsByCity(
            @RequestParam String city) {

        return ResponseEntity.ok(
                eventService.getEventsByCity(city)
        );

    }
    //specification jpa
    @Operation(
            summary = "Filter Events",
            description = "Filter events using any combination of event name, city, category, type and status."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Events fetched successfully")
    })
    @GetMapping("/filter")
    public ResponseEntity<List<EventResponse>> filterEvents(

            @RequestParam(required = false) String eventName,

            @RequestParam(required = false) String city,

            @RequestParam(required = false) EventCategory category,

            @RequestParam(required = false) EventType type,

            @RequestParam(required = false) EventStatus status) {

        return ResponseEntity.ok(
                eventService.filterEvents(
                        eventName,
                        city,
                        category,
                        type,
                        status
                )
        );
    }
}
