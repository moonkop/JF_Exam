package com.njmsita.exam.utils.validate.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelephoneValidator implements ConstraintValidator<TelephoneValidatorAnnotation,String>
{

    private int len;

    public void initialize(TelephoneValidatorAnnotation telephoneValidatorAnnotation)
    {
        this.len=telephoneValidatorAnnotation.length();
    }

    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext)
    {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        if(phone.length() != len){
            //禁用默认的message的值
            constraintValidatorContext.disableDefaultConstraintViolation();
            //重新添加错误提示语句
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("手机号应为11位数").addConstraintViolation();
            return false;
        }else{
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if(isMatch){
                return true;
            } else {
                return false;
            }
        }
    }
}
