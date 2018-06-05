package com.njmsita.exam.utils.timertask;

import com.njmsita.exam.manager.dao.dao.StudentExamDao;
import com.njmsita.exam.manager.model.ScheduleVo;
import com.njmsita.exam.manager.model.StudentExamQuestionVo;
import com.njmsita.exam.manager.model.StudentExamVo;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.format.FormatUtil;
import org.quartz.*;

import java.util.Set;

@DisallowConcurrentExecution
public class StudentSubmitJob implements Job
{
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        JobDataMap dataMap= context.getJobDetail().getJobDataMap();
        ScheduleVo scheduleVo = (ScheduleVo) dataMap.get("scheduleVo");
        StudentExamDao studentExamDao= (StudentExamDao) scheduleVo.getDao();
        StudentExamVo studentExamVo=studentExamDao.get(scheduleVo.getTargetVoId());
        studentExamVo.setStatus(SysConsts.STUDENT_EXAM_STATUS_SUBMITTED);
        studentExamVo.setSubmitTime(System.currentTimeMillis());
        Set<StudentExamQuestionVo> questions=studentExamVo.getStudentExamQuestionVos();
        double sum=0;
        for (StudentExamQuestionVo question : questions)
        {
            double score=0;
            if(question.getAnswer().equals(question.getWorkout())){
                score=question.getQuestionTypeVo().getScore();
            }else if(question.getAnswer().contains(question.getWorkout())){
                score=question.getQuestionTypeVo().getScore()/2;
            }
            question.setScore(FormatUtil.formatScore(score));
        }
        studentExamVo.setScore(FormatUtil.formatScore(sum));
        studentExamVo.setStudentExamQuestionVos(questions);
        studentExamDao.update(studentExamVo);
        try
        {
            SchedulerJobUtil.delJob(scheduleVo);
        } catch (SchedulerException e)
        {
            e.printStackTrace();
        }
    }
}