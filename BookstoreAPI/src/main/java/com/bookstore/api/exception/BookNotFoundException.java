
package com.bookstore.api.exception;

// --- BookNotFoundException ---
public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super(message);
    }
}

