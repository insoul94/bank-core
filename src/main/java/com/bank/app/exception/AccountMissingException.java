package com.bank.app.exception;

import com.bank.app.exception.UserException;

public class AccountMissingException extends UserException {

    public AccountMissingException(String message) {
        super(message);
    }
}
