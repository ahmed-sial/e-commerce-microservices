package com.ahmedhassan.ecommerce.client.dto;

import com.ahmedhassan.ecommerce.model.PaymentMethod;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record PaymentRequestDto(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        UUID orderId,
        String orderReference,
        CustomerResponseDto customer
) {
}
