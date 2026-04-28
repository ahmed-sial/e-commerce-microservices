package com.ahmedhassan.ecommerce.dto.category;

import lombok.Builder;

@Builder
public record CategoryResponseDto(
        Long id,
        String name,
        String description,
        String slug
) {}
