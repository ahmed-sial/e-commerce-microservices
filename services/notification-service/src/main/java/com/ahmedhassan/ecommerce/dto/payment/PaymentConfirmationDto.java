package com.ahmedhassan.ecommerce.dto.payment;

import java.math.BigDecimal;

public record PaymentConfirmationDto(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {
}
