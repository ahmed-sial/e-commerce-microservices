package com.ahmedhassan.ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductRequestDto(
        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 50, message = "Name should be between 2 to 50 characters")
        String name,
        @NotBlank(message = "Description is required")
        @Size(min = 2, max = 100, message = "Description should be between 2 to 100 characters")
        String description,
        @NotBlank(message = "Price is required")
        @Min(value = 0, message = "Price should be greater that 0")
        BigDecimal price,
        @Min(value = 0, message = "Available quantity should be greater that 0")
        Integer availableQuantity,
        @NotNull
        Long categoryId
) {}
