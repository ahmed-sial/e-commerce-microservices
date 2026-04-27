package com.ahmedhassan.ecommerce.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record CustomerRequestDto(
        @NotBlank(message = "First name is required")
        @Size(min = 2, max = 50)
        String firstName,
        @NotBlank(message = "Last name is required")
        @Size(min = 2, max = 50)
        String lastName,
        @NotBlank(message = "Last name is required")
        @Email(message = "Invalid email format")
        @Max(100)
        String email,
        @Valid
        @NotNull
        AddressDto address
) {}
