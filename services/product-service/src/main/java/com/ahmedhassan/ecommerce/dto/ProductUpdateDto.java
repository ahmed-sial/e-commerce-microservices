package com.ahmedhassan.ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record ProductUpdateDto(
        @NotBlank(message = "Price is required")
        @Min(value = 0, message = "Price should be greater that 0")
        BigDecimal price,
        @Min(value = 0, message = "Available quantity should be greater that 0")
        Integer availableQuantity
) {}
