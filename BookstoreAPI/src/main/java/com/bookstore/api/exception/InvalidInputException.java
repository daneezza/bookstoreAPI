package com.bookstore.api.exception;

// --- InvalidInputException ---
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}

