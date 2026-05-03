package com.ahmedhassan.ecommerce.dto.order;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDto(
        UUID productId,
        String name,
        String description,
        BigDecimal price,
        Integer quantity
) {
}
