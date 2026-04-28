package com.ahmedhassan.ecommerce.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CategoryRequestDto(
        @NotBlank(message = "Name is required")
        @Size(min = 3, max = 20, message = "Name should be between 3 to 20 characters")
        String name,
        @NotBlank(message = "Description is required")
        @Size(min = 3, max = 100, message = "Description should be between 3 to 100 characters")
        String description
) {}
