package com.bookstore.api.exception;


// --- CustomerNotFoundException ---
public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}