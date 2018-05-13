package com.njmrita.aspercj.test;

import com.njmsita.exam.authentic.controller.TeacherController;
import com.njmsita.exam.authentic.dao.dao.StudentDao;
import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.manager.dao.dao.QuestionDao;
import com.njmsita.exam.manager.model.QuestionVo;
import com.njmsita.exam.utils.timertask.QuartzJobFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.plugin2.util.PojoUtil;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MyAspect
{
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    @Autowired
    private TeacherController teacherController;

    @Test
    public void fun3() throws SchedulerException
    {
        /*//schedulerFactoryBean 由spring创建注入
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        //这里获取任务信息数据
        List<ScheduleJob> jobList = DataWorkContext.getAllJob();
        for (ScheduleJob job : jobList) {
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
            //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            //不存在，创建一个
            if (null == trigger) {
                JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
                        .withIdentity(job.getJobName(), job.getJobGroup()).build();
                jobDetail.getJobDataMap().put("scheduleJob", job);
                //表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                        .getCronExpression());
                //按新的cronExpression表达式构建一个新的trigger
                trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(),
                        job.getJobGroup()).withSchedule(scheduleBuilder).build();
                scheduler.scheduleJob(jobDetail, trigger);
            } else {
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
        }*/
    }



    @Test
    public void fun1(){
        TeacherVo teacherVo= new TeacherVo();
        teacherVo.setTeacherId("0001");
        teacherVo.setPassword("123");

    }

    @Test
    public void fun2(){
        QuestionVo questionVo=questionDao.get("26e57ebd1d234cb5a5425510ed7697a3");
        System.out.println(PojoUtil.toJson(questionVo));
    }
}
