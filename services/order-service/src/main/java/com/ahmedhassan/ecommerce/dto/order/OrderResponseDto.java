package com.ahmedhassan.ecommerce.dto.order;

import com.ahmedhassan.ecommerce.dto.orderline.OrderLineResponseDto;
import com.ahmedhassan.ecommerce.model.PaymentMethod;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
public record OrderResponseDto(
        UUID orderId,
        String reference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        UUID customerId
) {}
