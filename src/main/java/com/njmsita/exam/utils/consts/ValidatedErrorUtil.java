package com.njmsita.exam.utils.consts;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidatedErrorUtil
{
    public static Map<String,Object> getErrorMessage(BindingResult bindingResult){
        List<FieldError> list = bindingResult.getFieldErrors();
        Map<String,Object> error= new HashMap<>();
        for (FieldError fieldError : list)
        {
            //校验信息，key=属性名+Error
            error.put(fieldError.getField() + "Error", fieldError.getDefaultMessage());
        }
        return error;
    }
}
