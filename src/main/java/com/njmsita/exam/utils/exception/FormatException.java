package com.njmsita.exam.utils.exception;

/**
 * 自定义格式异常
 */
public class FormatException extends Exception
{
    public FormatException()
    {
    }

    public FormatException(String message)
    {
        super(message);
    }

    public FormatException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public FormatException(Throwable cause)
    {
        super(cause);
    }

    public FormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
