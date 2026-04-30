package com.ahmedhassan.ecommerce.dto.order;

import java.util.UUID;

public record ProductPurchaseRequestDto(
        UUID productId,
        Integer orderQuantity
) {}
