package com.bank.app.exception;

import com.bank.app.exception.UserException;

public class InvalidAmountException extends UserException {

    public InvalidAmountException(String message) {
        super(message);
    }
}
