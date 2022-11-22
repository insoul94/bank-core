package com.bank.app.exception;

import com.bank.app.exception.UserException;

public class InvalidDirectionException extends UserException {

    public InvalidDirectionException(String message) {
        super(message);
    }
}
