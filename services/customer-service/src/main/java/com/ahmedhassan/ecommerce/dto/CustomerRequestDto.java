package com.ahmedhassan.ecommerce.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record CustomerRequestDto(
        @NotBlank(message = "First name is required")
        @Size(min = 2, max = 50, message = "First name should be between 2 to 50 characters")
        String firstName,
        @NotBlank(message = "Last name is required")
        @Size(min = 2, max = 50, message = "Last name should be between 2 to 50 characters")
        String lastName,
        @NotBlank(message = "Last name is required")
        @Email(message = "Invalid email format")
        @Size(max = 100, message = "Email should be at max 100 characters long")
        String email,
        @Valid
        @NotNull
        AddressDto address
) {}
