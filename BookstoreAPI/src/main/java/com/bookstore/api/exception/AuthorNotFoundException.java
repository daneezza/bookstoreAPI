package com.bookstore.api.exception;

// --- AuthorNotFoundException ---
public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(String message) {
        super(message);
    }
}


