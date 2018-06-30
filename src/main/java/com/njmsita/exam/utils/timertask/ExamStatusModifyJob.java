package com.njmsita.exam.utils.timertask;

import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.ScheduleVo;
import com.njmsita.exam.manager.service.ebi.ExamManageEbi;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.context.ContextLoader;

@DisallowConcurrentExecution
public class ExamStatusModifyJob implements Job
{

    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        System.out.println("任务正在运行");
        ScheduleVo scheduleVo = (ScheduleVo) context.getMergedJobDataMap().get("scheduleVo");
        System.out.println("任务名称 = [" + scheduleVo.getJobName() + "]");
        ContextLoader.getCurrentWebApplicationContext().getBean(ExamManageEbi.class).invoke(examManageEbo ->
        {
            ExamVo exam = examManageEbo.get(scheduleVo.getTargetVoId());

            if (exam.getExamStatus() == scheduleVo.getNowStatu())
            {
                exam.setExamStatus(scheduleVo.getAffterStatu());
            }
            examManageEbo.outmodedSchedule(scheduleVo);
        });
    }
}
