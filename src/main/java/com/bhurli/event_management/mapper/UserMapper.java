package com.bhurli.event_management.mapper;

import com.bhurli.event_management.dto.request.UserRequest;
import com.bhurli.event_management.dto.response.UserResponse;
import com.bhurli.event_management.entity.User;
import com.bhurli.event_management.enums.AccountStatus;
import com.bhurli.event_management.enums.Role;

/**
 * Mapper class for converting User Entity and DTO objects.
 */

public class UserMapper {

    /**
     * Convert UserRequest into User Entity.
     */
    public static User toEntity(UserRequest request) {

        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .phone(request.getPhone())
//                .role(Role.USER)
//                .accountStatus(AccountStatus.ACTIVE)
                .build();

    }
    /**
     * Convert User Entity into UserResponse.
     */
    public static UserResponse toResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .profileImage(user.getProfileImage())
                .role(user.getRole())
                .accountStatus(user.getAccountStatus())
                .createdAt(user.getCreatedAt())
                .build();

    }
}
