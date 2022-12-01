package com.bank.app.exception;

import com.bank.app.constant.Currency;

public class InvalidCurrencyException extends UserException {

    public InvalidCurrencyException() {
        this(String.format("Invalid currency. Allowed: [%s].",
                String.join(", ", Currency.valuesAsStringArray())));
    }

    public InvalidCurrencyException(String message) {
        super(message);
    }
}
