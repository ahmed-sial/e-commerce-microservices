package com.ahmedhassan.ecommerce.exception;

public class ProductAlreadyExistsWithNameException extends RuntimeException {
    public ProductAlreadyExistsWithNameException(String message) {
        super(message);
    }
}
