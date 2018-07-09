package com.njmsita.exam.utils.listener;

import com.njmsita.exam.authentic.model.TresourceVo;
import com.njmsita.exam.authentic.service.ebi.ResourceEbi;
import com.njmsita.exam.manager.dao.dao.ScheduleDao;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.ScheduleVo;
import com.njmsita.exam.manager.service.ebi.ExamManageEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.format.FormatUtil;
import com.njmsita.exam.utils.timertask.SchedulerJobUtil;
import org.quartz.SchedulerException;
import org.quartz.impl.StdScheduler;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        //恢复定时任务
        ExamManageEbi examManageEbo = ContextLoader.getCurrentWebApplicationContext().getBean(ExamManageEbi.class);
        Object o=ctx.getBean("schedulerFactoryBean");
        StdScheduler stdScheduler= (StdScheduler) o;

        /*List<ScheduleVo> list=examManageEbo.getAllByExecutable();
        Set<String> outmodedGroup=new HashSet<>();
        try
        {
            for (ScheduleVo scheduleVo : list)
            {
                if(outmodedGroup.contains(scheduleVo.getJobGroup()))
                {
                    setOutmoded(examManageEbo,scheduleVo);
                    continue;
                }
                Long time= FormatUtil.getTimeByCron(scheduleVo.getCronexpression());
                if(time<System.currentTimeMillis())
                {
                    //setOutmoded(examManageEbo,scheduleVo);
                    ExamVo examVo=examManageEbo.get(scheduleVo.getTargetVoId());
                    examVo.setExamStatus(SysConsts.EXAM_STATUS_OUTMODED);
                    examManageEbo.update(examVo);
                    outmodedGroup.add(scheduleVo.getJobGroup());
                }else {
                    SchedulerJobUtil.createJob(scheduleVo,stdScheduler);
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }*/
    }

    private void setOutmoded(ExamManageEbi examManageEbo,ScheduleVo scheduleVo)
    {
        scheduleVo.setJobStatus(SysConsts.SCHEDULEVO_JOB_STATUS_OUTMODED);
        examManageEbo.updateSchedule(scheduleVo);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent)
    {

    }
}
