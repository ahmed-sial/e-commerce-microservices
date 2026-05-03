package com.ahmedhassan.ecommerce.client;

import com.ahmedhassan.ecommerce.client.dto.PaymentRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(
        name = "payment-service",
        url = "${spring.application.config.payment-url}"
)
public interface PaymentClient {

    @PostMapping
    UUID requestOrderPayment(
            @RequestBody PaymentRequestDto dto
    );
}
