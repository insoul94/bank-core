package com.bank.app.exception;

public class InvalidAmountException extends UserException {

    public InvalidAmountException() {
        this("Invalid amount.");
    }

    public InvalidAmountException(String message) {
        super(message);
    }
}
