package com.bank.app.exception;

public class UserException extends RuntimeException {
    public UserException() {
        this("Invalid input data");
    }
    public UserException(String message) {
        super(message);
    }
}
