package com.bank.app.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CurrenciesValidator.class)
@Documented
public @interface ValidCurrencies {
    String message() default "Invalid currency";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
