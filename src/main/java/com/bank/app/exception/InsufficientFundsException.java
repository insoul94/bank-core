package com.bank.app.exception;

public class InsufficientFundsException extends UserException {

    public InsufficientFundsException() {
        this("Insufficient funds.");
    }

    public InsufficientFundsException(String message) {
        super(message);
    }
}
