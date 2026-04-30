package com.ahmedhassan.ecommerce.service;

import com.ahmedhassan.ecommerce.client.CustomerClient;
import com.ahmedhassan.ecommerce.client.ProductClient;
import com.ahmedhassan.ecommerce.config.KafkaOrderTopicConfig;
import com.ahmedhassan.ecommerce.dto.PagedResponse;
import com.ahmedhassan.ecommerce.dto.order.OrderRequestDto;
import com.ahmedhassan.ecommerce.dto.order.OrderResponseDto;
import com.ahmedhassan.ecommerce.dto.orderline.OrderLineRequestDto;
import com.ahmedhassan.ecommerce.dto.orderline.OrderLineResponseDto;
import com.ahmedhassan.ecommerce.exception.CustomerNotFoundException;
import com.ahmedhassan.ecommerce.exception.OrderNotFoundException;
import com.ahmedhassan.ecommerce.exception.ProductPurchaseNotSuccessfulException;
import com.ahmedhassan.ecommerce.kafka.OrderConfirmationDto;
import com.ahmedhassan.ecommerce.kafka.OrderProducer;
import com.ahmedhassan.ecommerce.mapper.OrderMapper;
import com.ahmedhassan.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.Uuid;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    public OrderResponseDto createOrder(OrderRequestDto dto) {
        var customer = customerClient.findCustomerById(dto.customerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        var purchasedProducts = productClient.purchaseProducts(dto.products())
                .orElseThrow(() -> new ProductPurchaseNotSuccessfulException("Product purchase not successful"));
        var order = mapper.toOrder(dto);
        var savedOrder = repository.save(order);
        for (var purchaseReq : dto.products()) {
            var orderLineReq = OrderLineRequestDto
                    .builder()
                    .quantity(purchaseReq.orderQuantity())
                    .order(order)
                    .productId(purchaseReq.productId())
                    .build();
            orderLineService.createOrderLine(orderLineReq);
        }
        var msg = OrderConfirmationDto
                .builder()
                .orderReference(dto.reference())
                .totalAmount(dto.totalAmount())
                .customer(customer)
                .products(purchasedProducts)
                .build();
        orderProducer.sendOrderConfirmationMessage(msg);
        return mapper.toOrderResponseDto(savedOrder);
    }
    public PagedResponse<OrderResponseDto> findAll(int pageNumber, int pageSize) {
       var pageable = PageRequest.of(pageNumber, pageSize);
       var orders = repository.findAll(pageable);
       return mapper.toPagedResponseDto(orders);
    }

    public OrderResponseDto findById(UUID id) {
        var order = repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        return mapper.toOrderResponseDto(order);
    }

}
