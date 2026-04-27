package com.ahmedhassan.ecommerce.service;

import com.ahmedhassan.ecommerce.dto.CustomerRequestDto;
import com.ahmedhassan.ecommerce.dto.CustomerResponseDto;
import com.ahmedhassan.ecommerce.dto.CustomerUpdateDto;
import com.ahmedhassan.ecommerce.dto.PagedResponse;
import com.ahmedhassan.ecommerce.exception.CustomerNotFoundException;
import com.ahmedhassan.ecommerce.exception.EmailAlreadyExistsException;
import com.ahmedhassan.ecommerce.mapper.CustomerMapper;
import com.ahmedhassan.ecommerce.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public CustomerResponseDto createCustomer(@NonNull CustomerRequestDto dto) {
        var emailExists = repository.existsByEmail(dto.email());
        if (emailExists) {
            throw new EmailAlreadyExistsException("Email already registered");
        }
        var customerModel = mapper.toCustomer(dto);
        var savedCustomer = repository.save(customerModel);
        return mapper.toCustomerResponseDto(savedCustomer);
    }

    public CustomerResponseDto updateCustomer(String id, @NonNull CustomerUpdateDto dto) {
        var existingCustomer = repository
                .findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        var addressDto = mapper.toAddressDto(existingCustomer.getAddress());
        if (addressDto.equals(dto.address()))
            return mapper.toCustomerResponseDto(existingCustomer);
        existingCustomer.setAddress(mapper.toAddress(dto.address()));
        var updatedCustomer = repository.save(existingCustomer);
        return mapper.toCustomerResponseDto(updatedCustomer);
    }

    public PagedResponse<CustomerResponseDto> getAllCustomers(int pageNumber, int pageSize) {
        var pageable = PageRequest.of(pageNumber, pageSize);
        var customers = repository.findAll(pageable);
        return mapper.toPagedCustomerResponse(customers);
    }

    public CustomerResponseDto findById(String id) {
        var customer = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(("Customer not found")));
        return mapper.toCustomerResponseDto(customer);
    }

    public boolean customerExists(String id) {
        return repository.existsById(id);
    }

    public void deleteById(String id) {
        if (!repository.existsById(id)) {
            throw new CustomerNotFoundException("Customer not found");
        }
        repository.deleteById(id);
    }
}
