package com.ahmedhassan.ecommerce.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CustomerResponseDto(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String address
) {}
