package com.ahmedhassan.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.Objects;

@Builder
public record AddressDto(
        @NotBlank(message = "House number is required")
        String houseNumber,
        @NotBlank(message = "Street is required")
        String street,
        @NotBlank(message = "City is required")
        String city,
        @NotBlank(message = "State is required")
        String state,
        @NotBlank(message = "Zip code is required")
        String zip
) {
        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof AddressDto)) return false;
                AddressDto that = (AddressDto) o;
                if (houseNumber.equals(that.houseNumber) &&
                        street.equals(that.street) &&
                        city.equals(that.city) &&
                        state.equals(that.state) &&
                        zip.equals(that.zip)) {
                        return true;
                }
                return false;
        }
        @Override
        public int hashCode() {
                return Objects.hash(houseNumber, street, city, state, zip);
        }
}
