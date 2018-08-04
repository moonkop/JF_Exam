package com.njmsita.exam.utils.timertask;

import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.QuestionTypeVo;
import com.njmsita.exam.manager.model.QuestionVo;
import com.njmsita.exam.manager.model.ScheduleVo;
import com.njmsita.exam.manager.service.ebi.ExamManageEbi;
import com.njmsita.exam.manager.service.ebo.ExamInvoker;
import com.njmsita.exam.manager.service.ebo.ExamManageEbo;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.ItemNotFoundException;
import com.njmsita.exam.utils.exception.OperationException;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.web.context.ContextLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchedulerJobUtil
{

    public static void createJob(ScheduleVo job, Scheduler scheduler) throws SchedulerException
    {
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
        //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
        CronTrigger trigger;
        //表达式调度构建器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronexpression());

            JobDetail jobDetail = JobBuilder.newJob(ExamStatusModifyJob.class)
                    .withIdentity(job.getJobName(), job.getJobGroup()).build();
            jobDetail.getJobDataMap().put("scheduleVo", job);


            //按新的cronExpression表达式构建一个新的trigger
            trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);

    }

    public static void pauseJob(ScheduleVo job, Scheduler scheduler) throws SchedulerException
    {
        JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
        scheduler.pauseJob(jobKey);
    }

    public static void deleteJob(ScheduleVo job, Scheduler scheduler) throws SchedulerException
    {
        JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
        scheduler.deleteJob(jobKey);
    }



    /**
     * 删除任务
     *
     * @param scheduleVo
     */
    public static void delJob(ScheduleVo scheduleVo) throws SchedulerException
    {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        TriggerKey triggerKey = new TriggerKey(scheduleVo.getJobName(), scheduleVo.getJobGroup());
        JobKey jobKey = new JobKey(scheduleVo.getJobName(), scheduleVo.getJobGroup());
        scheduler.pauseTrigger(triggerKey);// 停止触发器
        scheduler.unscheduleJob(triggerKey);// 删除触发器
        scheduler.deleteJob(jobKey);// 删除任务
    }
}
