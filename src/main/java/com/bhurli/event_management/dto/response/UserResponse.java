package com.bhurli.event_management.dto.response;

import com.bhurli.event_management.enums.AccountStatus;
import com.bhurli.event_management.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

/**
 * DTO returned after user-related operations.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    @Schema(
            description = "Unique user ID",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Schema(
            description = "First name",
            example = "Satyajeet"
    )
    private String firstName;

    @Schema(
            description = "Last name",
            example = "Pandey"
    )
    private String lastName;

    @Schema(
            description = "Registered email address",
            example = "satyajeet@example.com"
    )
    private String email;

    @Schema(
            description = "Registered phone number",
            example = "9876543210"
    )
    private String phone;

    private String profileImage;

    @Schema(
            description = "Role assigned to the user",
            example = "ORGANIZER"
    )
    private Role role;

    @Schema(
            description = "Current account status",
            example = "ACTIVE"
    )
    private AccountStatus accountStatus;

    @Schema(
            description = "User registration date and time",
            example = "2026-07-10T18:30:00",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime createdAt;
}
