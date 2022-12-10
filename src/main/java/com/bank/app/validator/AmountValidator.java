package com.bank.app.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.regex.Pattern;

public class AmountValidator implements ConstraintValidator<ValidAmount, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        if (!Pattern.matches("(?!0+$)(^[0-9]*)(\\.?(?:[0-9]+)?$)", value)) {
            return false;
        }

        try {
            new BigDecimal(value);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
