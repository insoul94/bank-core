package com.bank.app.exception;

import com.bank.app.exception.UserException;

public class AccountNotFoundException extends UserException {

    public AccountNotFoundException(String message) {
        super(message);
    }
}
