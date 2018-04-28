package com.njmsita.exam.utils.logutils;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLogAnnotation
{
    String module() default "";
    String methods() default "";
}
