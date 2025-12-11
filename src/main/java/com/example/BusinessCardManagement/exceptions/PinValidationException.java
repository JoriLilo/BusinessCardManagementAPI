package com.example.BusinessCardManagement.exceptions;

public class PinValidationException extends RuntimeException {
    public PinValidationException() {
        super();
    }
    public PinValidationException(String message) {
        super(message);
    }
}
