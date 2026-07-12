package com.bhurli.event_management.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * DTO used for user registration.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    @Schema(
            description = "First name of the user",
            example = "Satyajeet"
    )
    @NotBlank(message = "First name is required")
    private String firstName;

    @Schema(
            description = "Last name of the user",
            example = "Pandey"
    )
    @NotBlank(message = "Last name is required")
    private String lastName;

    @Schema(
            description = "Email address used for login",
            example = "satyajeet@example.com"
    )
    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    @Schema(
            description = "User account password",
            example = "Password@123"
    )
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @Schema(
            description = "10-digit mobile number",
            example = "9876543210"
    )
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number")
    private String phone;
}
