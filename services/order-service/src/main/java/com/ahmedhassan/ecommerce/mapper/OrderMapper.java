package com.ahmedhassan.ecommerce.mapper;

import com.ahmedhassan.ecommerce.dto.PagedResponse;
import com.ahmedhassan.ecommerce.dto.order.OrderRequestDto;
import com.ahmedhassan.ecommerce.dto.order.OrderResponseDto;
import com.ahmedhassan.ecommerce.dto.orderline.OrderLineResponseDto;
import com.ahmedhassan.ecommerce.model.Order;
import com.ahmedhassan.ecommerce.model.PaymentMethod;
import com.ahmedhassan.ecommerce.repository.OrderLineRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    public Order toOrder(OrderRequestDto dto) {
       return Order
               .builder()
               .reference(dto.reference())
               .totalAmount(dto.totalAmount())
               .paymentMethod(PaymentMethod.valueOf(dto.paymentMethod()))
               .customerId(dto.customerId())
               .build();
    }

    public OrderResponseDto toOrderResponseDto(Order order) {
        return OrderResponseDto
                .builder()
                .orderId(order.getId())
                .reference(order.getReference())
                .totalAmount(order.getTotalAmount())
                .paymentMethod(order.getPaymentMethod())
                .customerId(order.getCustomerId())
                .build();
    }

    public PagedResponse<OrderResponseDto> toPagedResponseDto(Page<Order> orders) {
        var mappedOrders = orders.stream().map(this::toOrderResponseDto).toList();
        return new PagedResponse<>(
                mappedOrders,
                orders.getNumber(),
                orders.getSize(),
                orders.getTotalElements(),
                orders.getTotalPages(),
                orders.isFirst(),
                orders.isLast()
        );
    }
}
