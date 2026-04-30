package com.ahmedhassan.ecommerce.client.dto;

public record CategoryResponseDto(
        Long id,
        String name,
        String description,
        String slug
) {}
