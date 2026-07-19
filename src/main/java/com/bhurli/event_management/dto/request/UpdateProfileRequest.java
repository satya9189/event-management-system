package com.bhurli.event_management.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProfileRequest {

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
            description = "10-digit mobile number",
            example = "9876543210"
    )
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Invalid mobile number"
    )
    private String phone;
}