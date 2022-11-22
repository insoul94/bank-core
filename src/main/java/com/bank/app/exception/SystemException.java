package com.bank.app.exception;

public class SystemException extends AppException{
    public SystemException() {
        this("Internal server error");
    }

    public SystemException(String message) {
        super(message);
    }
}
