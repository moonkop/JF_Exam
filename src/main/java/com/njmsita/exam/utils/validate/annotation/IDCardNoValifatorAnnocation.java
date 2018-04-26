package com.njmsita.exam.utils.validate.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IDCardNoValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IDCardNoValifatorAnnocation
{
    int lenth15() default 15;
    int lenth18() default 18;

    String message() default "{idcard.number.format.error}";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
