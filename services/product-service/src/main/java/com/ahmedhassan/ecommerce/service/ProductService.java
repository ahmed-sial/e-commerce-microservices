package com.ahmedhassan.ecommerce.service;

import com.ahmedhassan.ecommerce.dto.*;
import com.ahmedhassan.ecommerce.exception.CategoryNotFoundException;
import com.ahmedhassan.ecommerce.exception.ProductAlreadyExistsWithNameException;
import com.ahmedhassan.ecommerce.mapper.ProductMapper;
import com.ahmedhassan.ecommerce.repository.CategoryRepository;
import com.ahmedhassan.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    // TODO: slug for category
    public ProductResponseDto createProduct(ProductRequestDto dto) {
        var category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        var existsByName = productRepository.existsByName(dto.name());
        if (existsByName) {
            throw new ProductAlreadyExistsWithNameException("Product already exists with the name");
        }
        var product = productMapper.toProduct(dto, category);
        var savedProduct = productRepository.save(product);
        return productMapper.toProductResponseDto(savedProduct);
    }

    public PagedResponse<ProductPurchaseResponseDto> purchaseProducts(
            List<ProductPurchaseRequestDto> dto
    ) {
        // TODO
        return null;
    }

    public ProductResponseDto findById(UUID id) {
        // TODO
        return null;
    }

    public PagedResponse<ProductResponseDto> findAll() {
        // TODO
        return null;
    }
}
