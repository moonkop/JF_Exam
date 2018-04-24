package com.njmsita.exam.utils.exception;

/**
 * 自定义操作异常类
 */
public class OperationException extends Exception
{
    public OperationException()
    {
    }

    public OperationException(String message)
    {
        super(message);
    }
}
