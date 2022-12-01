package com.bank.app.exception;


public class AccountNotFoundException extends UserException {

    public AccountNotFoundException(Long accountId) {
        this("Account #" + accountId + " not found.");
    }

    public AccountNotFoundException(String message) {
        super(message);
    }
}
