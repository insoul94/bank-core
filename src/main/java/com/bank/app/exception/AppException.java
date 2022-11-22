package com.bank.app.exception;

import java.util.Objects;

public class AppException extends RuntimeException{

    public AppException(String message) {
        super(Objects.requireNonNull(message));
    }
}
