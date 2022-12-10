package com.bank.app.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AmountValidator.class)
@Documented
public @interface ValidAmount {
    String message() default "Invalid amount";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
