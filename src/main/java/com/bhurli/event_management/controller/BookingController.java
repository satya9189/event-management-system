package com.bhurli.event_management.controller;

import com.bhurli.event_management.dto.request.BookingRequest;
import com.bhurli.event_management.dto.response.BookingResponse;
import com.bhurli.event_management.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(
        name = "Booking Management",
        description = "APIs for booking event tickets."
)

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    //swagger
    @Operation(
            summary = "Book Event Tickets",
            description = "Creates a booking for an event by the logged-in user."
    )

    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "201",
                    description = "Booking created successfully"),

            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid booking request"),

            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"),

            @ApiResponse(
                    responseCode = "404",
                    description = "User or Event not found")

    })
    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(
            @Valid @RequestBody BookingRequest request) {

        BookingResponse response =
                bookingService.createBooking(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get My Bookings",
            description = "Returns all bookings of the currently logged-in user."
    )

    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Bookings fetched successfully"
            )

    })

    @GetMapping("/my-bookings")
    public ResponseEntity<List<BookingResponse>> getMyBookings() {

        return ResponseEntity.ok(
                bookingService.getMyBookings()
        );

    }

    //swagger
    @Operation(
            summary = "Get Booking By ID",
            description = "Returns booking details using booking ID."
    )
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Booking fetched successfully"),

            @ApiResponse(
                    responseCode = "404",
                    description = "Booking not found")

    })

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getBookingById(
            @PathVariable Long id) {

        BookingResponse response =
                bookingService.getBookingById(id);

        return ResponseEntity.ok(response);
    }

    //swagger
    @Operation(
            summary = "Get All Bookings",
            description = "Returns all event bookings."
    )

    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Bookings fetched successfully")

    })

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<BookingResponse>> getAllBookings() {

        return ResponseEntity.ok(
                bookingService.getAllBookings()
        );
    }


    // Swagger
    @Operation(
            summary = "Cancel Booking",
            description = "Cancels an existing booking."
    )

    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Booking cancelled successfully"),

            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"),

            @ApiResponse(
                    responseCode = "404",
                    description = "Booking not found")

    })

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<BookingResponse> cancelBooking(
            @PathVariable Long id) {

        BookingResponse response = bookingService.cancelBooking(id);

        return ResponseEntity.ok(response);
    }


}
