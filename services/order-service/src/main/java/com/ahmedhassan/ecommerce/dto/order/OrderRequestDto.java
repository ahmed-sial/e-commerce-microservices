package com.ahmedhassan.ecommerce.dto.order;

import com.ahmedhassan.ecommerce.model.PaymentMethod;
import com.ahmedhassan.ecommerce.validation.ValidEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
public record OrderRequestDto(
        @NotBlank(message = "Reference is required")
        String reference,
        @Min(value = 0, message = "Total amount should be greater than or equal to zero")
        BigDecimal totalAmount,
        @ValidEnum(enumClass = PaymentMethod.class, message = "Invalid payment method value")
        String paymentMethod,
        @NotNull(message = "Customer ID is required")
        UUID customerId,
        @Valid
        @NotEmpty(message = "No product specified in order")
        List<ProductPurchaseRequestDto> products
) {}
