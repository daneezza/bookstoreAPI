package com.bookstore.api.exception;

// --- OutOfStockException ---
public class OutOfStockException extends RuntimeException {
    public OutOfStockException(String message) {
        super(message);
    }
}


