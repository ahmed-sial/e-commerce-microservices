package com.ahmedhassan.ecommerce.dto;

import com.ahmedhassan.ecommerce.model.PaymentMethod;
import com.ahmedhassan.ecommerce.validation.ValidEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record PaymentRequestDto(
        @Min(value = 0, message = "Amount should be greater than zero")
        BigDecimal amount,
        @ValidEnum(enumClass = PaymentMethod.class, message = "Invalid payment method specified")
        PaymentMethod paymentMethod,
        @NotNull(message = "Order ID is required")
        UUID orderId,
        @NotBlank(message = "Order Reference is required")
        String orderReference,
        @NotNull(message = "Customer information is required")
        @Valid
        Customer customer
) {
}
