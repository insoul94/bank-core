package com.bank.app.exception;

import com.bank.app.exception.UserException;

public class DescriptionMissingException extends UserException {

    public DescriptionMissingException(String message) {
        super(message);
    }
}
