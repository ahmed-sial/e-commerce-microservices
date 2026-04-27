package com.ahmedhassan.ecommerce.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record ProductResponseDto(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        Integer availableQuantity,
        CategoryResponseDto category
) {}
