package com.bank.app.exception;

public class UserException extends AppException{
    public UserException() {
        this("Invalid input data");
    }
    public UserException(String message) {
        super(message);
    }
}
