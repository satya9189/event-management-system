package com.bhurli.event_management.service;

import com.bhurli.event_management.dto.request.LoginRequest;
import com.bhurli.event_management.dto.request.UpdateProfileRequest;
import com.bhurli.event_management.dto.request.UserRequest;
import com.bhurli.event_management.dto.response.LoginResponse;
import com.bhurli.event_management.dto.response.UserResponse;
import com.bhurli.event_management.entity.User;
import java.util.List;

/**
 * Service interface for managing users.
 *
 * It defines all business operations related to users
 * such as registration, profile management and account operations.
 */
public interface UserService {

//    User registerUser(User user);
//
//
//    User getUserById(Long id);
//
//    List<User> getAllUsers();
//
//    User updateUser(Long id, User user);
//
//    void deleteUser(Long id);

    UserResponse registerUser(UserRequest request);

    LoginResponse loginUser(LoginRequest request);

    //User APIs

    UserResponse getUserById(Long id);

    UserResponse getUserByEmail(String email);

    List<UserResponse> getAllUsers();

    UserResponse updateUser(Long id, UserRequest request);

    void deleteUser(Long id);

    // Profile APIs
    UserResponse getMyProfile();

    UserResponse updateMyProfile(UpdateProfileRequest request);

}
