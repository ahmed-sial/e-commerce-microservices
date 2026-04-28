package com.ahmedhassan.ecommerce.mapper;

import com.ahmedhassan.ecommerce.dto.PagedResponse;
import com.ahmedhassan.ecommerce.dto.product.ProductPurchaseResponseDto;
import com.ahmedhassan.ecommerce.dto.product.ProductRequestDto;
import com.ahmedhassan.ecommerce.dto.product.ProductResponseDto;
import com.ahmedhassan.ecommerce.model.Category;
import com.ahmedhassan.ecommerce.model.Product;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public PagedResponse<ProductResponseDto> toPagedProductResponseDto(@NonNull Page<Product> products) {
        var mappedProducts = products.stream().map(this::toProductResponseDto).toList();
        return new PagedResponse<>(
                mappedProducts,
                products.getNumber(),
                products.getSize(),
                products.getTotalElements(),
                products.getTotalPages(),
                products.isFirst(),
                products.isLast()
        );
    }

    public ProductPurchaseResponseDto toProductPurchaseResponseDto(@NonNull Product product, int purchasedQuantity) {
        return ProductPurchaseResponseDto
                .builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .purchasedQuantity(purchasedQuantity)
                .categoryId(product.getCategory().getId())
                .build();
    }

    public PagedResponse<ProductPurchaseResponseDto> toPagedProductPurchaseResponseDto(List<ProductPurchaseResponseDto> products) {
//       return new PagedResponse<>(
//              // TODO: Implement own login for paged response fields
//       );
       return null;
    }
}
