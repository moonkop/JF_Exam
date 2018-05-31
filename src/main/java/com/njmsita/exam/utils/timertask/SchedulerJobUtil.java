package com.njmsita.exam.utils.timertask;

import com.njmsita.exam.manager.model.ScheduleVo;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class SchedulerJobUtil
{

    public static void createJob(ScheduleVo job, Scheduler scheduler) throws SchedulerException
    {
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
        //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        //表达式调度构建器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                .getCronexpression());
        //不存在，创建一个
        if (trigger == null)
        {
            JobDetail jobDetail = JobBuilder.newJob(ExamStatusModifyJob.class)
                    .withIdentity(job.getJobName(), job.getJobGroup()).build();
            jobDetail.getJobDataMap().put("scheduleVo", job);

            //按新的cronExpression表达式构建一个新的trigger
            trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).
                    withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } else
        {
            // Trigger已存在，那么更新相应的定时设置
            //按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                    .withSchedule(scheduleBuilder).build();
            //按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        }
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
     * 添加一次性任务
     *
     * @param scheduleVo
     *
     * @throws SchedulerException
     */
    public static void addJob(ScheduleVo scheduleVo) throws SchedulerException
    {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetail jobDetail = JobBuilder.newJob(StudentSubmitJob.class)
                .withIdentity(scheduleVo.getJobName(), scheduleVo.getJobGroup()).build();
        jobDetail.getJobDataMap().put("scheduleVo", scheduleVo);
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(scheduleVo.getJobName(), scheduleVo.getJobGroup())
                .withSchedule(CronScheduleBuilder.cronSchedule(scheduleVo.getCronexpression()))
                .build();
        scheduler.scheduleJob(jobDetail, trigger);// 将任务信息添加到sheduler中
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
