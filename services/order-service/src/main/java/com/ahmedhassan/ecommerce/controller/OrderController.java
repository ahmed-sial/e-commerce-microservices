package com.ahmedhassan.ecommerce.controller;

import com.ahmedhassan.ecommerce.dto.PagedResponse;
import com.ahmedhassan.ecommerce.dto.order.OrderRequestDto;
import com.ahmedhassan.ecommerce.dto.order.OrderResponseDto;
import com.ahmedhassan.ecommerce.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(
            @RequestBody
            @Valid
            OrderRequestDto dto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createOrder(dto));
    }

    @GetMapping
    public ResponseEntity<PagedResponse<OrderResponseDto>> findAll(
            @RequestParam(name = "page", defaultValue = "0", required = false)
            @Min(0)
            Integer pageNumber,
            @RequestParam(name = "size", defaultValue = "10", required = false)
            @Max(25)
            Integer pageSize
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findAll(pageNumber, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> findById(
            @PathVariable UUID id
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findById(id));
    }

}
