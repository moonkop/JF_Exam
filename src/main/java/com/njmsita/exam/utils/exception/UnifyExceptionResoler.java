package com.njmsita.exam.utils.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 统一异常处理器
 */
public class UnifyExceptionResoler implements HandlerExceptionResolver
{
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception exception)
    {
        //输出异常
        exception.printStackTrace();

        //异常信息
        String message=null;
        UnifyException unifyException=null;

        if(exception instanceof UnifyException){
            unifyException= (UnifyException) exception;
        }else{
            unifyException=new UnifyException("未知错误，测试阶段详情见日志信息！");
        }

        message=unifyException.getMessage();
        request.setAttribute("exceptionMessage",message);

        try
        {
            //TODO 定义错误码500 页面
            response.setStatus(500);
            request.getRequestDispatcher("/WEB-INF/jsp/error/500.jsp").forward(request,response);
        } catch (ServletException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return new ModelAndView();
    }
}
