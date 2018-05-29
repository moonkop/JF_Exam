package com.njmsita.exam.utils.interceptor;

import com.njmsita.exam.authentic.model.UserModel;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.UnLoginException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆拦截器（认证用户是否登陆）
 */
public class LoginInterceptor implements HandlerInterceptor
{
    private UserModel user;

    //执行handler之前
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception
    {
        //得到请求的URL
        String url = request.getRequestURI();
        ServletContext sc = request.getServletContext();
        //获取权限资源
        String allResource = (String) sc.getAttribute(SysConsts.ALL_RESOUCERS_AUTHENTIC_URL_NAME);
        //放行公开地址
        if (!allResource.contains(url))
        {
            return true;
        }
        //获取并判断用户身份
        this.user = (UserModel) request.getSession().getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
        if (this.user != null)
        {
            return true;
        }else {
            String requestHeader = request.getHeader("x-requested-with");
            if (requestHeader != null && requestHeader.equals("XMLHttpRequest"))
            {
                throw new UnLoginException();
            }
            response.sendRedirect("/teacher/login");
        }
        return false;
    }

    //执行handler返回modelAndView之前
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception
    {

    }

    //执行handler之后
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception
    {
    }
}
