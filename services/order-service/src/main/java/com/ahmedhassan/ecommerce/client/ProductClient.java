package com.ahmedhassan.ecommerce.client;

import com.ahmedhassan.ecommerce.dto.order.ProductPurchaseRequestDto;
import com.ahmedhassan.ecommerce.client.dto.ProductPurchaseResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name = "product-service",
        url = "${application.config.product-url}"
)
public interface ProductClient {

    @GetMapping("/purchase")
    Optional<List<ProductPurchaseResponseDto>> purchaseProducts(
            @RequestBody List<ProductPurchaseRequestDto> dto
    );
}
