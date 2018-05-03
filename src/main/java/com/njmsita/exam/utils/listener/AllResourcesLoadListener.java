package com.njmsita.exam.utils.listener;

import com.njmsita.exam.authentic.model.TresourceVo;
import com.njmsita.exam.authentic.service.ebi.ResourceEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * 全资源加载监听器
 */
public class AllResourcesLoadListener implements ServletContextListener
{
    public void contextInitialized(ServletContextEvent servletContextEvent)
    {

        //读取所有资源信息，放入SerlvetContext范围
        ServletContext sc = servletContextEvent.getServletContext();
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
        ResourceEbi resourceEbi = (ResourceEbi) ctx.getBean("resourceEbi");
        List<TresourceVo> resourceList = resourceEbi.getAll();
        StringBuilder sbf = new StringBuilder();
        for (TresourceVo tresourceVo : resourceList)
        {
            sbf.append("["+tresourceVo.getUrl()+"]");
            sbf.append(",");
        }
        //放入sc中
        sc.setAttribute(SysConsts.ALL_RESOUCERS_AUTHENTIC_URL_NAME, sbf.toString());
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent)
    {

    }
}
