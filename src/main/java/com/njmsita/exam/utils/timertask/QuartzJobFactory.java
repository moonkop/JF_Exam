package com.njmsita.exam.utils.timertask;

import com.njmsita.exam.manager.dao.dao.ExamDao;
import com.njmsita.exam.manager.dao.impl.ExamDaoImpl;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.service.ebi.ExamEbi;
import com.njmsita.exam.manager.service.ebo.ExamEbo;
import com.njmsita.exam.utils.exception.OperationException;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

//@DisallowConcurrentExecution
@Component
public class QuartzJobFactory implements Job
{

    @Autowired
    private ExamDao examDao1;
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        System.out.println("任务成功运行");
        ScheduleJob scheduleJob = (ScheduleJob)context.getMergedJobDataMap().get("scheduleJob");
        ExamEbi examDao=scheduleJob.getExamEbi();
        ExamVo exam=examDao.get("1264882423");
        if(exam.getExamStatus()==scheduleJob.getNowStatu()){
            exam.setExamStatus(scheduleJob.getAffterStatu());
            try
            {
                examDao.update(exam);
            } catch (OperationException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("任务名称 = [" + scheduleJob.getJobName() + "]");
    }


}
