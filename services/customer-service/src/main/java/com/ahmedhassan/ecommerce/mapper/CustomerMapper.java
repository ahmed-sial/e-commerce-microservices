package com.ahmedhassan.ecommerce.mapper;

import com.ahmedhassan.ecommerce.dto.AddressDto;
import com.ahmedhassan.ecommerce.dto.CustomerRequestDto;
import com.ahmedhassan.ecommerce.dto.CustomerResponseDto;
import com.ahmedhassan.ecommerce.dto.PagedResponse;
import com.ahmedhassan.ecommerce.model.Address;
import com.ahmedhassan.ecommerce.model.Customer;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerResponseDto toCustomerResponseDto(Customer customer) {
        return CustomerResponseDto
                .builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .address(customer.getAddress().toString())
                .build();
    }
    public Customer toCustomer(@NonNull CustomerRequestDto dto) {
        return Customer
                .builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .address(toAddress(dto.address()))
                .build();
    }
    public AddressDto toAddressDto(@NonNull Address address) {
        return AddressDto
                .builder()
                .houseNumber(address.getHouseNumber())
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .zip(address.getZip())
                .build();
    }

    public Address toAddress(@NonNull AddressDto dto) {
        return Address
                .builder()
                .houseNumber(dto.houseNumber())
                .street(dto.street())
                .city(dto.city())
                .state(dto.state())
                .zip(dto.zip())
                .build();
    }

    public PagedResponse<CustomerResponseDto> toPagedCustomerResponse(Page<Customer> customers) {
        var mappedCustomers = customers
                .stream()
                .map(this::toCustomerResponseDto)
                .toList();
        return new PagedResponse<>(
                mappedCustomers,
                customers.getNumber(),
                customers.getSize(),
                customers.getTotalElements(),
                customers.getTotalPages(),
                customers.isFirst(),
                customers.isLast()
        );
    }
}
