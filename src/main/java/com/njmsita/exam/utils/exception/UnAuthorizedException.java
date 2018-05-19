package com.njmsita.exam.utils.exception;

public class UnAuthorizedException extends UnifyException
{
    public UnAuthorizedException(String message)
    {
        super(message);
        this.code = 403;
    }
}
