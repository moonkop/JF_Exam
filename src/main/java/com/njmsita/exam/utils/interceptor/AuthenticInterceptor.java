package com.njmsita.exam.utils.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.njmsita.exam.authentic.model.UserModel;
import com.njmsita.exam.manager.service.ebi.LogEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.OperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * 权限拦截器
 * 
 */
public class AuthenticInterceptor implements HandlerInterceptor {


	@Autowired
	private LogEbi logEbi;

	private UserModel user;
	/**
	 * 在调用controller具体方法前拦截
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception
	{
		//得到请求的URL
		String url = request.getRequestURI();
		ServletContext sc=request.getServletContext();
		//获取权限资源
		String allResource= (String) sc.getAttribute(SysConsts.ALL_RESOUCERS_AUTHENTIC_URL_NAME);
		//放行公开地址
		if(!allResource.contains("["+url+"]")){
			return true;
		}
		//获取并判断用户身份
		user= (UserModel) request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME);
		String containRes= (String)  request.getSession().getAttribute(SysConsts.USER_RESOURCE_NAME);
		if(containRes.contains(url)){
			return true;
		}
		throw new OperationException("对不起，您没有该权限，请不要进行非法操作！");
	}

	/**
	 * 完成页面的render后调用
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception
	{

	}

	/**
	 * 在调用controller具体方法后拦截
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception
	{
	}
}
