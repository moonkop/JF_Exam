package com.njmsita.exam.utils.timertask;

import com.njmsita.exam.manager.dao.dao.ExamDao;
import com.njmsita.exam.manager.dao.impl.ExamDaoImpl;
import com.njmsita.exam.manager.dao.impl.ScheduleDaoImpl;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.ScheduleVo;
import com.njmsita.exam.manager.service.ebi.ExamManageEbi;
import com.njmsita.exam.manager.service.ebo.ExamManageEbo;
import com.njmsita.exam.utils.exception.OperationException;
import org.hibernate.FlushMode;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.WebApplicationContextUtils;

import static org.springframework.web.context.ContextLoader.getCurrentWebApplicationContext;

@DisallowConcurrentExecution
public class ExamStatusModifyJob implements Job
{

    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        System.out.println("任务正在运行");
        ScheduleVo scheduleVo = (ScheduleVo) context.getMergedJobDataMap().get("scheduleVo");
        System.out.println("任务名称 = [" + scheduleVo.getJobName() + "]");
        ExamManageEbi examManageEbo = ContextLoader.getCurrentWebApplicationContext().getBean(ExamManageEbi.class);
        ExamVo exam = examManageEbo.get(scheduleVo.getTargetVoId());

        if (exam.getExamStatus() == scheduleVo.getNowStatu())
        {
            exam.setExamStatus(scheduleVo.getAffterStatu());
            try
            {
                examManageEbo.update(exam);
            } catch (OperationException e)
            {
                e.printStackTrace();
            }
        }
        examManageEbo.outmodedSchedul(scheduleVo);
    }
}
