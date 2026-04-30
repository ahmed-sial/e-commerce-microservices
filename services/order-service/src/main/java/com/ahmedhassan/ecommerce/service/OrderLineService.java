package com.ahmedhassan.ecommerce.service;

import com.ahmedhassan.ecommerce.dto.orderline.OrderLineRequestDto;
import com.ahmedhassan.ecommerce.dto.orderline.OrderLineResponseDto;
import com.ahmedhassan.ecommerce.mapper.OrderLineMapper;
import com.ahmedhassan.ecommerce.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;

    public OrderLineResponseDto createOrderLine(OrderLineRequestDto dto) {
        var orderLine = mapper.toOrderLine(dto);
        var savedOrderLine = repository.save(orderLine);
        return mapper.toOrderLineResponseDto(savedOrderLine);
    }
}
