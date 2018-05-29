package com.njmsita.exam.utils.exception;

public class UnLoginException extends UnifyException
{

    public UnLoginException()
    {
        super("");
        this.setMessage("登录信息失效，请重新登录！");
    }

    public UnLoginException(String message)
    {
        super(message);
        this.code=401;
    }
}
