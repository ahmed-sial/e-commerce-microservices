package com.ahmedhassan.ecommerce.controller;

import com.ahmedhassan.ecommerce.dto.CustomerRequestDto;
import com.ahmedhassan.ecommerce.dto.CustomerResponseDto;
import com.ahmedhassan.ecommerce.dto.CustomerUpdateDto;
import com.ahmedhassan.ecommerce.dto.PagedResponse;
import com.ahmedhassan.ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(
            @RequestBody
            @Valid
            CustomerRequestDto customerRequestDto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerService.createCustomer(customerRequestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> updateCustomer(
            @PathVariable
            String id,
            @RequestBody
            @Valid
            CustomerUpdateDto customerUpdateDto
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.updateCustomer(id, customerUpdateDto));
    }

    @GetMapping
    public ResponseEntity<PagedResponse<CustomerResponseDto>> getAllCustomers(
            @RequestParam(name = "page", defaultValue = "0", required = false) @Min(0) Integer pageNumber,
            @RequestParam(name = "size", defaultValue = "10", required = false) @Max(25) Integer pageSize
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.getAllCustomers(pageNumber, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> findById(@PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.findById(id));
    }

    @GetMapping("/e/{id}")
    public ResponseEntity<Boolean> customerExists(@PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.customerExists(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        customerService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
