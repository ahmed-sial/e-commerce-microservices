package com.ahmedhassan.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.Instant;
import java.util.Map;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ErrorResponse(
        Instant timeStamp,
        Integer status,
        String error,
        String message,
        String path,
        Map<String, String> details
) {}
