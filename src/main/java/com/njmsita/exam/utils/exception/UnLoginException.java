package com.njmsita.exam.utils.exception;

public class UnLoginException extends UnifyException
{

    public UnLoginException(String message)
    {
        super(message);
        this.code=401;
    }
}
