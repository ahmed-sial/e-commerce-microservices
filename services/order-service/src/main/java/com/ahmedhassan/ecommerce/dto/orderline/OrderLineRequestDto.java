package com.ahmedhassan.ecommerce.dto.orderline;

import com.ahmedhassan.ecommerce.model.Order;
import lombok.Builder;

import java.util.UUID;

@Builder
public record OrderLineRequestDto(
        Integer quantity,
        Order order,
        UUID productId
) {
}
