package com.bank.app.exception;

import com.bank.app.exception.UserException;

public class InsufficientFundsException extends UserException {

    public InsufficientFundsException(String message) {
        super(message);
    }
}
