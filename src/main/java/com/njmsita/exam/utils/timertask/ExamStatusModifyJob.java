package com.njmsita.exam.utils.timertask;

import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.ScheduleVo;
import com.njmsita.exam.manager.service.ebi.ExamManageEbi;
import com.njmsita.exam.manager.service.ebo.ExamInvoker;
import com.njmsita.exam.manager.service.ebo.ExamManageEbo;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.ItemNotFoundException;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

@Component
@DisallowConcurrentExecution
public class ExamStatusModifyJob implements Job
{
    @Transactional
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        System.out.println("任务正在运行");
        ScheduleVo scheduleVo = (ScheduleVo) context.getMergedJobDataMap().get("scheduleVo");
        System.out.println("任务名称 = [" + scheduleVo.getJobName() + "]");
        try
        {
            ContextLoader.getCurrentWebApplicationContext().getBean(ExamManageEbi.class).invoke(new ExamInvoker()
            {
                @Override
                public void invoke(ExamManageEbo examManageEbo) throws ItemNotFoundException
                {
                    ExamVo examPo = examManageEbo.get(scheduleVo.getTargetVoId());
                    if (examPo.getExamStatus()<scheduleVo.getStatusAfter())
                    {
                        examPo.setExamStatus(scheduleVo.getStatusAfter());
                        switch (scheduleVo.getScheduleActionType())
                        {
                            case SysConsts.EXAM_SCHEDULE_ACTION_TYPE_EXAM_START:
                                break;
                            case SysConsts.EXAM_SCHEDULE_ACTION_TYPE_EXAM_CLOSE_ENTRANCE:
                                break;
                            case SysConsts.EXAM_SCHEDULE_ACTION_TYPE_EXAM_END:
                                if (examManageEbo.paperIsSelectProblemsOnly(examPo))
                                {
                                    examPo.setExamStatus(SysConsts.EXAM_STATUS_ENDING);
                                }
                                break;
                        }
                    }else{
                        System.err.println("任务执行失败，条件不满足！");
                    }
                    examManageEbo.deleteUsedSchedule(scheduleVo);
                }
            });
        } catch (Exception e)
        {
            System.err.println("任务执行失败");
            e.printStackTrace();
        }

    }
}
