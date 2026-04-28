package com.ahmedhassan.ecommerce.dto.product;

import jakarta.validation.constraints.Min;

import java.math.BigDecimal;

public record ProductUpdateDto(
        @Min(value = 0, message = "Price should be greater that 0")
        BigDecimal price,
        @Min(value = 0, message = "Available quantity should be greater that 0")
        Integer availableQuantity
) {}
