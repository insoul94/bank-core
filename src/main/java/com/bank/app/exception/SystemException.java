package com.bank.app.exception;

public class SystemException extends RuntimeException{
    public SystemException() {
        this("Internal server error");
    }

    public SystemException(String message) {
        super(message);
    }
}
