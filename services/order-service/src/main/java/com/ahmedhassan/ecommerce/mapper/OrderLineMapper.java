package com.ahmedhassan.ecommerce.mapper;

import com.ahmedhassan.ecommerce.dto.orderline.OrderLineRequestDto;
import com.ahmedhassan.ecommerce.dto.orderline.OrderLineResponseDto;
import com.ahmedhassan.ecommerce.model.OrderLine;
import org.springframework.stereotype.Component;

@Component
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequestDto dto) {
        return OrderLine
                .builder()
                .quantity(dto.quantity())
                .order(dto.order())
                .productId(dto.productId())
                .build();
    }

    public OrderLineResponseDto toOrderLineResponseDto(OrderLine orderLine) {
        return OrderLineResponseDto
                .builder()
                .id(orderLine.getId())
                .quantity(orderLine.getQuantity())
                .orderId(orderLine.getOrder().getId())
                .productId(orderLine.getProductId())
                .build();
    }
}
