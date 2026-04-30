package com.ahmedhassan.ecommerce.dto.orderline;

import lombok.Builder;

import java.util.UUID;

@Builder
public record OrderLineResponseDto(
        UUID id,
        Integer quantity,
        UUID orderId,
        UUID productId
) {
}
