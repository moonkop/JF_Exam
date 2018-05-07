package com.njmsita.exam.utils.jsonfilter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * JSON过滤的注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JSON {
    //目标类
    Class<?> type();
    //包含的类型
    String include() default "";
    //不包含的类型
    String filter() default "";
}