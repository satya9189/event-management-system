package com.bhurli.event_management.dto.response;
import com.bhurli.event_management.enums.Role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * DTO returned after successful authentication.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    @Schema(
            description = "JWT access token returned after successful login",
            example = "eyJhbGciOiJIUzUxMiJ9.xxxxxxxxxxxxxxxxx"
    )
    private String token;

    private String tokenType;

    private Long userId;

    @Schema(
            description = "Full name of the logged-in user",
            example = "Satyajeet Pandey"
    )
    private String fullName;

    @Schema(
            description = "Email address of the logged-in user",
            example = "satyajeet@example.com"
    )
    private String email;

    @Schema(
            description = "Role of the authenticated user",
            example = "ORGANIZER"
    )
    private String role;
//    private Role role;

}
