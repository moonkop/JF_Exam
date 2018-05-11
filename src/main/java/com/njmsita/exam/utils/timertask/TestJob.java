package com.njmsita.exam.utils.timertask;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.List;

public class TestJob
{
    @Autowired
    private QuartzJobFactory quartzJobFactory;

    public void quartzAddJob(String jobName,String jobId,String cronExpression,Scheduler scheduler) throws SchedulerException
    {
        ScheduleJob job = new ScheduleJob();
        job.setJobId(jobId);
        job.setJobName(jobName);
        job.setJobGroup("dataWork");
        job.setJobStatus("1");
        job.setCronExpression(cronExpression);//"0/5 * * * * ?"
        job.setDesc("数据导入任务");

        createJob(job,scheduler);
    }

    public void createJob(ScheduleJob job,Scheduler scheduler) throws SchedulerException
    {
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
        //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        //不存在，创建一个
        if (null == trigger)
        {
            JobDetail jobDetail = JobBuilder.newJob(quartzJobFactory.getClass())
                    .withIdentity(job.getJobName(), job.getJobGroup()).build();
            jobDetail.getJobDataMap().put("scheduleJob", job);
            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                    .getCronExpression());
            //按新的cronExpression表达式构建一个新的trigger
            trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(),
                    job.getJobGroup()).withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } else
        {
            // Trigger已存在，那么更新相应的定时设置
            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                    .getCronExpression());
            //按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                    .withSchedule(scheduleBuilder).build();
            //按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        }
    }
}
