package com.ahmedhassan.ecommerce.mapper;

import com.ahmedhassan.ecommerce.dto.ProductRequestDto;
import com.ahmedhassan.ecommerce.dto.ProductResponseDto;
import com.ahmedhassan.ecommerce.model.Category;
import com.ahmedhassan.ecommerce.model.Product;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final CategoryMapper mapper;

    public Product toProduct(@NonNull ProductRequestDto dto, Category category) {
        return Product
                .builder()
                .name(dto.name())
                .description(dto.description())
                .price(dto.price())
                .availableQuantity(dto.availableQuantity())
                .category(category)
                .build();
    }

    public ProductResponseDto toProductResponseDto(@NonNull Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .availableQuantity(product.getAvailableQuantity())
                .category(mapper.toCategoryResponseDto(product.getCategory()))
                .build();
    }
}
