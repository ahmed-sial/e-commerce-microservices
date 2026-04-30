package com.ahmedhassan.ecommerce.kafka;

import com.ahmedhassan.ecommerce.client.dto.CustomerResponseDto;
import com.ahmedhassan.ecommerce.client.dto.ProductPurchaseResponseDto;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record OrderConfirmationDto(
        String orderReference,
        BigDecimal totalAmount,
        CustomerResponseDto customer,
        List<ProductPurchaseResponseDto> products
) {
}
