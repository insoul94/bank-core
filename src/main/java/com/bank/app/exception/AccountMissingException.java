package com.bank.app.exception;

public class AccountMissingException extends UserException {

    public AccountMissingException() {
        super("Account missing.");
    }
}
