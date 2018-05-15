package com.njmsita.exam.utils.timertask;

import com.njmsita.exam.manager.model.ScheduleVo;
import org.apache.tiles.request.ApplicationContext;
import org.quartz.*;

@DisallowConcurrentExecution
public class QuartzJobOfExamSubmitByStudent implements Job
{
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        JobDataMap dataMap= context.getJobDetail().getJobDataMap();
        ScheduleVo scheduleVo = (ScheduleVo) dataMap.get("scheduleVo");
        try
        {
            SchedulerJobUtil.delJob(scheduleVo);
        } catch (SchedulerException e)
        {
            e.printStackTrace();
        }
    }
}
