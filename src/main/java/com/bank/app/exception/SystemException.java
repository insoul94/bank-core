package com.bank.app.exception;

public class SystemException extends AppException{

    public static final String DEFAULT_MESSAGE = "Internal server error";
    public SystemException() {
        this(DEFAULT_MESSAGE);
    }

    public SystemException(String message) {
        super(message);
    }
}
