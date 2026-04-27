package com.ahmedhassan.ecommerce.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Validated
@Builder
@Getter
@Setter
public class Address {
    @NotBlank
    @Size(min = 1, max = 20)
    private String houseNumber;
    @NotBlank
    @Size(min = 1, max = 20)
    private String street;
    @NotBlank
    @Size(min = 2, max = 50)
    private String city;
    @NotBlank
    @Size(min = 2, max = 50)
    private String state;
    @NotBlank
    @Size(min = 3, max = 10)
    private String zip;
}
