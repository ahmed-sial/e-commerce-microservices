package com.ahmedhassan.ecommerce.controller;

import com.ahmedhassan.ecommerce.dto.PagedResponse;
import com.ahmedhassan.ecommerce.dto.category.CategoryRequestDto;
import com.ahmedhassan.ecommerce.dto.category.CategoryResponseDto;
import com.ahmedhassan.ecommerce.dto.product.*;
import com.ahmedhassan.ecommerce.service.ProductService;
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
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(
            @RequestBody
            @Valid
            ProductRequestDto dto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.createProduct(dto));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponseDto>> purchaseProduct(
            @RequestBody
            @Valid
            List<ProductPurchaseRequestDto> dto
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.purchaseProducts(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findProductById(
            @PathVariable
            UUID id
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.findProductById(id));
    }

    @GetMapping
    public ResponseEntity<PagedResponse<ProductResponseDto>> findAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") @Min(0) Integer pageNumber,
            @RequestParam(name = "size", required = false, defaultValue = "10") @Max(25) Integer pageSize
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.findAll(pageNumber, pageSize));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable
            UUID id,
            @RequestBody
            @Valid
            ProductUpdateDto dto
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.updateProduct(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable
            UUID id
    ) {
        productService.deleteProduct(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PostMapping("/category")
    public ResponseEntity<CategoryResponseDto> createCategory(
            @RequestBody
            @Valid
            CategoryRequestDto dto
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.createCategory(dto));
    }

    @GetMapping("/c/{id}")
    public ResponseEntity<CategoryResponseDto> findCategoryById(
            @PathVariable
            Long id
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.findCategoryById(id));
    }

    @DeleteMapping("/c/{id}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable
            Long id
    ) {
        productService.deleteCategory(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
