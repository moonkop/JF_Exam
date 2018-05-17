package com.njmsita.exam.utils.timertask;

import com.njmsita.exam.manager.dao.dao.ExamDao;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.ScheduleVo;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution
public class QuartzJobFactory implements Job
{

    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        System.out.println("任务成功运行");
        ScheduleVo scheduleVo = (ScheduleVo)context.getMergedJobDataMap().get("scheduleVo");
        ExamDao examDao= (ExamDao) scheduleVo.getDao();
        ExamVo exam=examDao.get(scheduleVo.getTargetVoId());
        if(exam.getExamStatus()==scheduleVo.getNowStatu()){
            exam.setExamStatus(scheduleVo.getAffterStatu());
            examDao.update(exam);
        }
        System.out.println("任务名称 = [" + scheduleVo.getJobName() + "]");
    }
}
