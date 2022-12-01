package com.bank.app.exception;

public class DescriptionMissingException extends UserException {

    public DescriptionMissingException() {
        super("Description missing.");
    }
}
