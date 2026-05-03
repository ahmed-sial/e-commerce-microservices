package com.ahmedhassan.ecommerce.dto.order;

import com.ahmedhassan.ecommerce.dto.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmationDto(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerDto customer,
        List<ProductDto> products
) {
}
