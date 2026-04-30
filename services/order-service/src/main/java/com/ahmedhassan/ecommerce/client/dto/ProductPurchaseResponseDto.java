package com.ahmedhassan.ecommerce.client.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record ProductPurchaseResponseDto(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        Integer purchasedQuantity,
        Long categoryId
) {}
