package com.ahmedhassan.ecommerce.exception.product;

public class ProductAlreadyExistsWithNameException extends RuntimeException {
    public ProductAlreadyExistsWithNameException(String message) {
        super(message);
    }
}
