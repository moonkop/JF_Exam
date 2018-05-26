package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.authentic.dao.dao.StudentDao;
import com.njmsita.exam.authentic.dao.dao.TeacherDao;
import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.manager.dao.dao.*;
import com.njmsita.exam.manager.model.*;
import com.njmsita.exam.manager.service.ebi.ExamManageEbi;
import com.njmsita.exam.manager.service.ebi.ExamStudentEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.format.FormatUtil;
import com.njmsita.exam.utils.format.StringUtil;
import com.njmsita.exam.utils.idutil.IdUtil;
import com.njmsita.exam.utils.timertask.SchedulerJobUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ExamStudentEbo implements ExamStudentEbi
{
    @Autowired
    private ExamDao examDao;
    @Autowired
    private ScheduleDao scheduleDao;
    @Autowired
    private LogDao logDao;
    @Autowired
    private StudentExamDao studentExamDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StudentExamQuestionDao studentExamQuestionDao;
    @Autowired
    private ExamManageEbi examManageEbi;
    @Autowired
    private PaperMongoDao paperMongoDao;



    public void archive(StudentVo login, String studentExamId, List<StudentExamQuestionVo> studentExamQuestionList) throws Exception
    {
        if (StringUtil.isEmpty(studentExamId))
        {
            throw new OperationException("你的考试id不能为空，请不要进行非法操作！");
        }
        StudentExamVo studentExamVo = studentExamDao.get(studentExamId);
        if (studentExamVo == null)
        {
            throw new OperationException("你的这场考试不存在，请不要进行非法操作！");
        }
        if (!studentExamVo.getStudentVo().getId().equals(login.getId()))
        {
            throw new OperationException("无法保存他人的试卷，请不要进行非法操作！");
        }
        ExamVo examVo = studentExamVo.getExamVo();
        if (examVo.getExamStatus() >= SysConsts.EXAM_STATUS_IN_MARK)
        {
            throw new OperationException("当前考试的状态不允许保存，请不要进行非法操作！");
        }
        if (studentExamQuestionList != null && studentExamQuestionList.size() != 0)
        {
            for (StudentExamQuestionVo studentExamQuestionVo : studentExamQuestionList)
            {
                StudentExamQuestionVo temp = studentExamQuestionDao.get(studentExamQuestionVo.getId());
                if (temp == null)
                {
                    throw new OperationException("你所保存的题目有可能不存在，请不要进行非法操作！");
                }
                if (!temp.getStudentExamVo().getId().equals(studentExamVo.getId()))
                {
                    throw new OperationException("你所保存的题目有可能不不属于这场考试，请不要进行非法操作！");
                }
                temp.setAnswer(studentExamQuestionVo.getAnswer());
            }
        }
    }

    public void submitAnswer(StudentExamVo studentExamVo, StudentVo login) throws Exception
    {
        if (StringUtil.isEmpty(studentExamVo.getId()))
        {
            throw new OperationException("请确认要提交的试卷不为空,不要进行非法操作！");
        }
        studentExamVo = studentExamDao.get(studentExamVo.getId());
        if (studentExamVo == null)
        {
            throw new OperationException("请确认要提交的试卷不为空,不要进行非法操作！");
        }
        if (!studentExamVo.getStudentVo().getId().equals(login.getId()))
        {
            throw new OperationException("不能提交他人试卷,请不要进行非法操作！");
        }
        Set<StudentExamQuestionVo> questions = studentExamVo.getStudentExamQuestionVos();
        double sum = 0;
        for (StudentExamQuestionVo question : questions)
        {
            double score = 0;
            if (question.getRightAnswer().equals(question.getAnswer()))
            {
                score = question.getQuestionTypeVo().getScore();
            } else if (question.getRightAnswer().contains(question.getAnswer()))
            {
                score = question.getQuestionTypeVo().getScore() / 2;
            }
            question.setScore(FormatUtil.formatScore(score));
        }
        studentExamVo.setScore(FormatUtil.formatScore(sum));
        studentExamVo.setStudentExamQuestionVos(questions);

        //TODO answerContent存入MongoDB
        ScheduleVo scheduleVo = scheduleDao.getByTarget(studentExamVo.getId()).get(0);
        SchedulerJobUtil.delJob(scheduleVo);
    }

    public StudentExamVo getStudentExam(ExamVo examVo, StudentVo login) throws Exception
    {
        if (StringUtil.isEmpty(examVo.getId()))
        {
            throw new OperationException("选择的考试不能为空，请不要进行非法操作！");
        }
        examVo = examManageEbi.getExamNotNull(examVo);

        StudentExamVo studentExamVo = studentExamDao.getByStudentAndExam(examVo, login);
        if (null == studentExamVo)
        {
            throw new OperationException("你不存在这场考试，请不要进行非法操作！");
        }
        return studentExamVo;
    }

    public Map<String, Object> attendExam(ExamVo examVo, StudentVo studentVo) throws Exception
    {
        examVo = examManageEbi.getExamNotNull(examVo);
        StudentExamVo studentExamVo = studentExamDao.getByStudentAndExam(examVo, studentVo);
        if (studentExamVo == null)
        {
            throw new OperationException("你没有该场考试，请不要进行非法操作！");
        }
        examVo = studentExamVo.getExamVo();
        if (examVo.getExamStatus() != SysConsts.EXAM_STATUS_OPEN)
        {
            throw new OperationException("尚未开始考试，请不要进行非法操作！");
        }
        if (studentExamVo.getOperation() == SysConsts.STUDENT_EXAM_OPERATION_SUBMIT)
        {
            throw new OperationException("你已经提交该场考试，请不要进行非法操作！");
        }
        List<StudentExamQuestionVo> list = new ArrayList<>();
        if (studentExamVo.getOperation() == SysConsts.STUDENT_EXAM_OPERATION_ARCHIVE)
        {
            list = studentExamQuestionDao.getAllByStudentExam(studentExamVo);
        } else
        {
            studentExamVo.setOperation(SysConsts.STUDENT_EXAM_OPERATION_ARCHIVE);
            Long systemTime = System.currentTimeMillis();
            studentExamVo.setStartTime(systemTime);
            examVo = examManageEbi.get(examVo.getId());
            List<QuestionVo> questionVoList = examVo.getPaperVo().getQuestionList();

            for (int i = 0; i < questionVoList.size(); i++)
            {
                StudentExamQuestionVo seq = new StudentExamQuestionVo();
                seq.setId(IdUtil.getUUID());
                seq.setStudentExamVo(studentExamVo);
                seq.setIndext(i + 1);
                seq.setRightAnswer(questionVoList.get(i).getAnswer());
                seq.setQuestionTypeVo(questionVoList.get(i).getQuestionType());
                studentExamQuestionDao.save(seq);
                list.add(seq);
            }
            //创建自动提交定时任务
            ScheduleVo scheduleVo = new ScheduleVo();
            scheduleVo.setId(IdUtil.getUUID());
            scheduleVo.setJobName("学生考试：" + studentExamVo.getId() + ":自动提交定时任务");
            scheduleVo.setJobGroup("default");
            scheduleVo.setJobStatus(SysConsts.SCHEDULEVO_JOB_STATUS_ONETIME);
            scheduleVo.setCronexpression(FormatUtil.cronExpression(systemTime + examVo.getDuration() * 60 * 100));
            scheduleVo.setDescribe(scheduleVo.getJobName());
            scheduleVo.setDao(studentExamDao);
            scheduleVo.setTargetVoId(studentExamVo.getId());
            scheduleVo.setJobType(SysConsts.SCHEDULEVO_JOB_TYPE_ONETIME);
            SchedulerJobUtil.addJob(scheduleVo);
            examManageEbi.saveLog(scheduleVo, scheduleVo.getDescribe());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("studentExamQuestionList", list);
        map.put("studentExamVo", studentExamVo);
        return map;
    }

}
