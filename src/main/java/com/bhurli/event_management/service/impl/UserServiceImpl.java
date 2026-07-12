package com.bhurli.event_management.service.impl;
import com.bhurli.event_management.constant.AppConstants;
import com.bhurli.event_management.dto.request.LoginRequest;
import com.bhurli.event_management.dto.request.UserRequest;
import com.bhurli.event_management.dto.response.LoginResponse;
import com.bhurli.event_management.dto.response.UserResponse;
import com.bhurli.event_management.entity.User;
import com.bhurli.event_management.enums.AccountStatus;
import com.bhurli.event_management.enums.Role;
import com.bhurli.event_management.exception.InvalidCredentialsException;
import com.bhurli.event_management.exception.ResourceAlreadyExistsException;
import com.bhurli.event_management.exception.ResourceNotFoundException;
import com.bhurli.event_management.mapper.UserMapper;
import com.bhurli.event_management.repository.UserRepository;
import com.bhurli.event_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.bhurli.event_management.security.jwt.JwtService;

import java.util.List;

/**
 * Provides business logic for user registration,
 * authentication, and profile management.
 */
@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Override
    public UserResponse registerUser(UserRequest request) {
        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException(AppConstants.EMAIL_ALREADY_EXISTS);
        }

        // Check if phone already exists
        if (userRepository.existsByPhone(request.getPhone())) {
            throw new ResourceAlreadyExistsException(AppConstants.PHONE_ALREADY_EXISTS);
        }

        // Convert DTO to Entity
        User user = UserMapper.toEntity(request);

        // Encode password
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Set default values
        user.setRole(Role.USER);
        user.setAccountStatus(AccountStatus.ACTIVE);

        // Save user
        User savedUser = userRepository.save(user);

        // Return Response DTO
        return UserMapper.toResponse(savedUser);
    }

    @Override
    public LoginResponse loginUser(LoginRequest request) {

        // Find user by email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException(AppConstants.INVALID_EMAIL_OR_PASSWORD));

        // Verify password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException(AppConstants.INVALID_EMAIL_OR_PASSWORD);
        }

        // Check account status
        if (user.getAccountStatus() != AccountStatus.ACTIVE) {
            throw new ResourceNotFoundException(AppConstants.ACCOUNT_INACTIVE);
        }

        // Generate JWT Token
        String token = jwtService.generateToken(user.getEmail());

// Return Login Response
        return LoginResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .userId(user.getId())
                .fullName(user.getFirstName() + " " + user.getLastName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.USER_NOT_FOUND));

        return UserMapper.toResponse(user);
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.USER_NOT_FOUND));

        return UserMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        // Find user
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.USER_NOT_FOUND));

        // Check if phone number already exists for another user
        if (!user.getPhone().equals(request.getPhone())
                && userRepository.existsByPhone(request.getPhone())) {

            throw new ResourceAlreadyExistsException(
                    AppConstants.PHONE_ALREADY_EXISTS);
        }

        // Update allowed fields
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhone(request.getPhone());

        // Save updated user
        User updatedUser = userRepository.save(user);

        return UserMapper.toResponse(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.USER_NOT_FOUND));

        userRepository.delete(user);

    }
}
