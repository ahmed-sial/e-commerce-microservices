package com.ahmedhassan.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.Objects;

@Builder
public record AddressDto(
        @NotBlank(message = "House number is required")
        @Size(min = 1, max = 20, message = "House number should be between 1 to 20 characters")
        String houseNumber,
        @NotBlank(message = "Street is required")
        @Size(min = 1, max = 20, message = "Street should be between 1 to 20 characters")
        String street,
        @NotBlank(message = "City is required")
        @Size(min = 2, max = 50, message = "City should be between 1 to 50 characters")
        String city,
        @NotBlank(message = "State is required")
        @Size(min = 2, max = 50, message = "State number should be between 1 to 50 characters")
        String state,
        @NotBlank(message = "Zip code is required")
        @Size(min = 3, max = 10, message = "Zip code should be between 1 to 10 characters")
        String zip
) {
        @Override
        public String toString() {
                return houseNumber + ", " + street + ", " + city + ", " + state + "/" + zip;
        }

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
