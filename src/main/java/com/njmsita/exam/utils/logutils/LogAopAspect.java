package com.njmsita.exam.utils.logutils;

import com.njmsita.exam.authentic.model.UserModel;
import com.njmsita.exam.manager.model.LogVo;
import com.njmsita.exam.manager.service.ebi.LogEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.format.IPUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
public class LogAopAspect
{
    //注入service,用来将日志信息保存在数据库
    @Autowired
    private LogEbi logEbi;

    //定义一个切入点
    @Pointcut("execution(* com.njmsita.exam.*.controller..*.*(..))")
    private void controllerAspect()
    {
    }

    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable
    {

        LogVo log = new LogVo();
        //获取登录用户账户
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取系统时间
        Long time=System.currentTimeMillis();
        log.setTime(time);
        log.setIp(IPUtil.getIP(request));

        //方法通知前获取时间,为什么要记录这个时间呢？当然是用来计算模块执行时间的
        long start = System.currentTimeMillis();
        // 拦截的实体类，就是当前正在执行的controller
        Object target = pjp.getTarget();
        // 拦截的方法名称。当前正在执行的方法
        String methodName = pjp.getSignature().getName();
        // 拦截的方法参数
        Object[] args = pjp.getArgs();

        StringBuilder sb=new StringBuilder();
        //绑定参数
        for (Object arg : args)
        {
            if(arg!=null){
                Class argClass=arg.getClass();
                String argName=argClass.getName();
                String argStr=arg.toString();
                sb.append("$$"+argName+"-->"+argStr+"$$");
                sb.append("，");
            }
        }
        if (args.length!=0)
        {
            log.setArgument(sb.toString().substring(0,sb.length()-1));
        }
        // 拦截的放参数类型
        Signature sig = pjp.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature))
        {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Class[] parameterTypes = msig.getMethod().getParameterTypes();

        Object object = null;
        // 获得被拦截的方法
        Method method = null;
        try
        {
            method = target.getClass().getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e1)
        {
            e1.printStackTrace();
        } catch (SecurityException e1)
        {
            e1.printStackTrace();
        }
        if (null != method)
        {
            // 判断是否包含自定义的注解
            if (method.isAnnotationPresent(SystemLogAnnotation.class))
            {
                SystemLogAnnotation systemlog = method.getAnnotation(SystemLogAnnotation.class);
                log.setModule(systemlog.module());
                log.setMethod(systemlog.methods());
                UserModel user = (UserModel) request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME);
                log.setUserId(user.getUuid());
                log.setRealName(user.getRealName());
                log.setUserRole(user.getUserRole());

                try
                {
                    object = pjp.proceed();
                    long end = System.currentTimeMillis();
                    //将计算好的时间保存在实体中
                    log.setResponseTime(end - start);
                    log.setCommite("执行成功！");
                    //保存进数据库
                    logEbi.save(log);
                } catch (Throwable e)
                {
                    long end = System.currentTimeMillis();
                    log.setResponseTime(end - start);
                    log.setCommite("执行失败");
                    logEbi.save(log);
                }
            } else
            {//没有包含注解
                object = pjp.proceed();
            }
        } else
        { //不需要拦截直接执行
            object = pjp.proceed();
        }
        return object;
    }
}
