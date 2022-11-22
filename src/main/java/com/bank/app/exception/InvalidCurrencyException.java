package com.bank.app.exception;

import com.bank.app.constant.Currency;
import com.bank.app.exception.UserException;

public class InvalidCurrencyException extends UserException {

    public InvalidCurrencyException() {
        super("Invalid currency. Allowed currencies: " +
                "{" + String.join(", ", Currency.valuesAsStringArray()) + "}");
    }
}
