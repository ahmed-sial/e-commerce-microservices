package com.ahmedhassan.ecommerce.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record CustomerUpdateDto(
        @Valid
        @NotNull
        AddressDto address
) {
}
