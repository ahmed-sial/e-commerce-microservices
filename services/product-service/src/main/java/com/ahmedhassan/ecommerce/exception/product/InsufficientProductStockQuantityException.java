package com.ahmedhassan.ecommerce.exception.product;

public class InsufficientProductStockQuantityException extends RuntimeException {
    public InsufficientProductStockQuantityException(String message) {
        super(message);
    }
}
