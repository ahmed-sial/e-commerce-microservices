package com.ahmedhassan.ecommerce.controller;

import com.ahmedhassan.ecommerce.dto.PaymentRequestDto;
import com.ahmedhassan.ecommerce.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @PostMapping
    public ResponseEntity<UUID> createPayment(PaymentRequestDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createPayment(dto));
    }
}
