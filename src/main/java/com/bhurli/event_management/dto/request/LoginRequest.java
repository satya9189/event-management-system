package com.bhurli.event_management.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * DTO used for user authentication.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {

    @Schema(
            description = "Registered email address of the user",
            example = "satyajeet@example.com"
    )
    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    @Schema(
            description = "Password of the user account",
            example = "Password@123"
    )
    @NotBlank(message = "Password is required")
    private String password;
}
