package com.njmsita.exam.utils.exception;

import java.util.ArrayList;
import java.util.List;

public class FieldErrorException extends UnifyException
{
    public List<String> errorMessages = new ArrayList<>();

    public FieldErrorException()
    {
        super();
        this.setCode(417);
    }

    public List<String> getErrorMessages()
    {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages)
    {
        this.errorMessages = errorMessages;
    }


}
