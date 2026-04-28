package com.ahmedhassan.ecommerce.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ProductPurchaseRequestDto(
        @NotNull(message = "Product ID is required")
        UUID productId,
        @NotNull(message = "Order quantity is required")
        @Min(value = 1, message = "Order quantity should be greater than or equal to 1")
        Integer orderQuantity
) {}
