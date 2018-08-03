package com.njmsita.exam.utils.exception;

import com.njmsita.exam.utils.json.CustomJsonSerializer;
import com.njmsita.exam.utils.json.JsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * 统一异常处理器
 */
public class UnifyExceptionResoler implements HandlerExceptionResolver
{
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception exception)
    {
        //输出异常
        exception.printStackTrace();

        //异常信息
        String message = null;
        UnifyException unifyException = null;

        if (exception instanceof UndeclaredThrowableException)
        {
            exception= (Exception) ((UndeclaredThrowableException) exception).getUndeclaredThrowable();
        }

        if (exception instanceof UnifyException)
        {
            unifyException = (UnifyException) exception;
        } else
        {
            unifyException = new UnifyException("未知错误，测试阶段详情见日志信息！");
        }

        message = unifyException.getMessage();
        String requestHeader = request.getHeader("x-requested-with");
        //如果是ajax异步请求 则返回异步错误字符串
        if (requestHeader != null && requestHeader.equals("XMLHttpRequest"))
        {
            try
            {
                //设置状态码
                response.setStatus(HttpStatus.OK.value());
                //设置ContentType
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                //避免乱码
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Cache-Control", "no-cache,must-revalidate");
                PrintWriter writer = response.getWriter();

                JsonResponse jsonResponse = new JsonResponse(unifyException);

                writer.write(CustomJsonSerializer.toJsonString_static(jsonResponse));
                writer.flush();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        } else
            //如果是同步请求则返回错误页面
        {
            if (exception instanceof UnLoginException)
            {
                try
                {
                    response.sendRedirect("/");
                } catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
            else{
                request.setAttribute("exceptionMessage", message);
                try
                {
                    response.setStatus(unifyException.getCode());
                    request.getRequestDispatcher("/WEB-INF/jsp/error/500.jsp").forward(request, response);
                } catch (ServletException e)
                {
                    e.printStackTrace();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

        }
        return new ModelAndView();
    }
}
