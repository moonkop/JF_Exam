package com.njmsita.exam.utils.validate.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TelephoneValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TelephoneValidatorAnnotation
{
    String message() default "{phone.number.format.error}";
    int length() default 11;

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
