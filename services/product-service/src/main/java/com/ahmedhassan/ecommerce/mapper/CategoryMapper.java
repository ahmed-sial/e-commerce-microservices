package com.ahmedhassan.ecommerce.mapper;

import com.ahmedhassan.ecommerce.dto.category.CategoryResponseDto;
import com.ahmedhassan.ecommerce.model.Category;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toCategory(@NonNull CategoryResponseDto dto) {
       return Category
               .builder()
               .name(dto.name())
               .description(dto.description())
               .slug(dto.slug())
               .build();
    }

    public CategoryResponseDto toCategoryResponseDto(@NonNull Category category) {
        return CategoryResponseDto
                .builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .slug(category.getSlug())
                .build();
    }
}
