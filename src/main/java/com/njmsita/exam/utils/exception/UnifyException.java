package com.njmsita.exam.utils.exception;

public class UnifyException extends Exception
{
    //异常信息
    private String message;

    public String getMessage()
    {
        return this.message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public UnifyException(String message)
    {
        this.message = message;
    }

    public UnifyException(String message, String message1)
    {
        super(message);
        this.message = message1;
    }

    public UnifyException(String message, Throwable cause, String message1)
    {
        super(message, cause);
        this.message = message1;
    }

    public UnifyException(Throwable cause, String message)
    {
        super(cause);
        this.message = message;
    }

    public UnifyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String message1)
    {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
    }
}
