package com.ahmedhassan.ecommerce.dto.order;

import java.util.UUID;

public record CustomerDto(
        UUID id,
        String firstname,
        String lastname,
        String email
) {}