package com.bank.app.validator;


import com.bank.app.constant.Currency;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

public class CurrenciesValidator implements ConstraintValidator<ValidCurrencies, Collection<String>> {
    @Override
    public boolean isValid(Collection<String> values, ConstraintValidatorContext context) {
        try {
            for (String value : values) {
                Currency.valueOf(value);
            }
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }

    }
}
