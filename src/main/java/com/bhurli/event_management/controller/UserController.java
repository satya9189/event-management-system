package com.bhurli.event_management.controller;

import com.bhurli.event_management.dto.request.LoginRequest;
import com.bhurli.event_management.dto.request.UserRequest;
import com.bhurli.event_management.dto.response.LoginResponse;
import com.bhurli.event_management.dto.response.UserResponse;
import com.bhurli.event_management.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

/**
 * REST Controller for User related APIs.
 */
@Tag(
        name = "User Management",
        description = "APIs for user registration, authentication and profile management."
)
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    //swagger documenti
    @Operation(
            summary = "Register a new user",
            description = "Creates a new user account."
    )
    //API 1
    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(
            @Valid @RequestBody UserRequest request) {

        UserResponse response = userService.registerUser(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //API 2
    @Operation(
            summary = "User Login",
            description = "Authenticates the user and returns a JWT access token."
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = userService.loginUser(request);

        return ResponseEntity.ok(response);
    }

    //api 3
    @Operation(
            summary = "Get User By ID",
            description = "Returns user details for the specified user ID."
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable Long id) {

        UserResponse response = userService.getUserById(id);

        return ResponseEntity.ok(response);
    }

    //Api 4
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(
            @PathVariable String email) {

        UserResponse response = userService.getUserByEmail(email);

        return ResponseEntity.ok(response);
    }

    //Api 5
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {

        List<UserResponse> users = userService.getAllUsers();

        return ResponseEntity.ok(users);
    }

    //API 6
    @Operation(
            summary = "Update User",
            description = "Updates the details of an existing user."
    )
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequest request) {

        UserResponse response = userService.updateUser(id, request);

        return ResponseEntity.ok(response);
    }

    //API 7
    @Operation(
            summary = "Delete User",
            description = "Deletes the specified user account."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Long id) {

        userService.deleteUser(id);

        return ResponseEntity.ok("User deleted successfully.");
    }

}
