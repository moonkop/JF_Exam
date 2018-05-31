package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.authentic.dao.dao.StudentDao;
import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.*;
import com.njmsita.exam.manager.model.*;
import com.njmsita.exam.manager.model.querymodel.StudentExamListQueryModel;
import com.njmsita.exam.manager.service.ebi.ExamManageEbi;
import com.njmsita.exam.manager.service.ebi.ExamStudentEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.exception.UnLoginException;
import com.njmsita.exam.utils.format.FormatUtil;
import com.njmsita.exam.utils.format.StringUtil;
import com.njmsita.exam.utils.idutil.IdUtil;
import com.njmsita.exam.utils.timertask.SchedulerJobUtil;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.wsdl.Operation;
import java.io.Serializable;
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


    public List<StudentExamVo> getAll()
    {
        return studentExamDao.getAll();
    }

    public StudentExamVo get(Serializable uuid)
    {
        return studentExamDao.get(uuid);
    }

    public List<StudentExamVo> getAll(BaseQueryVO qm, Integer pageNum, Integer pageCount)
    {
        return studentExamDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryVO qm)
    {
        return studentExamDao.getCount(qm);
    }


    public void archive(StudentVo login, String studentExamId, List<StudentExamQuestionVo> studentExamQuestionList) throws Exception
    {
        if (StringUtil.isEmpty(studentExamId))
        {
            throw new OperationException("你的考试id不能为空");
        }
        StudentExamVo studentExamVo = studentExamDao.get(studentExamId);
        if (studentExamVo == null)
        {
            throw new OperationException("你的这场考试不存在");
        }
        if (!studentExamVo.getStudent().getId().equals(login.getId()))
        {
            throw new OperationException("无法保存他人的试卷");
        }
        ExamVo examVo = studentExamVo.getExam();
        if (examVo.getExamStatus() >= SysConsts.EXAM_STATUS_IN_MARK)
        {
            throw new OperationException("考试已结束，不允许保存");
        }
        if (studentExamQuestionList != null && studentExamQuestionList.size() != 0)
        {
            for (StudentExamQuestionVo studentExamQuestionVo : studentExamQuestionList)
            {
                StudentExamQuestionVo temp = studentExamQuestionDao.get(studentExamQuestionVo.getId());
                if (temp == null)
                {
                    throw new OperationException("你所保存的题目有可能不存在");
                }
                if (!temp.getStudentExam().getId().equals(studentExamVo.getId()))
                {
                    throw new OperationException("你所保存的题目有可能不不属于这场考试");
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
        if (!studentExamVo.getStudent().getId().equals(login.getId()))
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
            throw new OperationException("选择的考试不能为空");
        }
        examVo = examManageEbi.getExamNotNull(examVo);

        StudentExamVo studentExamVo = studentExamDao.getByStudentAndExam(examVo, login);
        if (null == studentExamVo)
        {
            throw new OperationException("你不存在这场考试");
        }
        return studentExamVo;
    }

    //------------学生方法---------------------//
    //------------学生方法---------------------//
    //------------学生方法---------------------//
    //------------学生方法---------------------//
    public List<StudentExamVo> getStudentExamList(StudentExamListQueryModel queryModel) throws UnLoginException
    {
        StudentVo loginStudent = studentDao.get(queryModel.getStudent().getId());
        List<StudentExamVo> studentExamVos = studentExamDao.getAll(queryModel);

        for (StudentExamVo studentExamVo : studentExamVos)
        {
            ExamVo examVo = studentExamVo.getExam();
            examVo.setOperation(examManageEbi.getValidOperations(examVo, loginStudent));
        }
        return studentExamVos;
    }

    @Override
    public int getStudentExamCount(StudentExamListQueryModel queryModel)
    {
        return studentExamDao.getCount(queryModel);
    }

    public void enterExam(String studentExamId, StudentVo loginStudent) throws Exception
    {
        StudentExamVo studentExamPo = studentExamDao.get(studentExamId);

        if (studentExamPo == null)
        {
            throw new OperationException("未找到该场考试");
        }
        ExamVo examPo = studentExamPo.getExam();
        examManageEbi.checkPermission(SysConsts.EXAM_OPERATION_ENTER, loginStudent, examPo);
        switch (studentExamPo.getStatus())
        {
            case SysConsts.STUDENT_EXAM_STATUS_NOT_STARTED:
                StudentExamStart(studentExamPo);
                break;

            case SysConsts.STUDENT_EXAM_STATUS_STARTED:
                break;

            case SysConsts.STUDENT_EXAM_STATUS_SUBMITTED:
                throw new OperationException("您已经提交该场考试");
        }
    }

    public void StudentExamStart(StudentExamVo studentExamPo) throws SchedulerException
    {
        ExamVo examPo = studentExamPo.getExam();
        studentExamPo.setStatus(SysConsts.STUDENT_EXAM_STATUS_STARTED);
        Long systemTime = System.currentTimeMillis();
        studentExamPo.setStartTime(systemTime);
        examPo = examManageEbi.getWithPaper(examPo.getId());
        List<QuestionVo> questionVoList = examPo.getPaperVo().getQuestionList();
        for (int i = 0; i < questionVoList.size(); i++)
        {
            StudentExamQuestionVo seq = new StudentExamQuestionVo();
            seq.setId(IdUtil.getUUID());
            seq.setStudentExam(studentExamPo);
            seq.setIndex(i + 1);
            seq.setRightAnswer(questionVoList.get(i).getAnswer());
            seq.setQuestionTypeVo(questionVoList.get(i).getQuestionType());
            studentExamQuestionDao.save(seq);
        }
        //创建自动提交定时任务
        ScheduleVo scheduleVo = new ScheduleVo();
        scheduleVo.setId(IdUtil.getUUID());
        scheduleVo.setJobName("学生考试：" + studentExamPo.getId() + ":自动提交定时任务");
        scheduleVo.setJobGroup("default");
        scheduleVo.setJobStatus(SysConsts.SCHEDULEVO_JOB_STATUS_ONETIME);
        //不限制考试时间则 考试终止时交卷。
        if (examPo.getDuration() != 0)
        {
            scheduleVo.setCronexpression(FormatUtil.cronExpression(systemTime + examPo.getDuration() * 60 * 100));
        }else {
            scheduleVo.setCronexpression(FormatUtil.cronExpression(examPo.getCloseTime()));
        }
        scheduleVo.setDescribe(scheduleVo.getJobName());
        scheduleVo.setDao(studentExamDao);
        scheduleVo.setTargetVoId(studentExamPo.getId());
        scheduleVo.setJobType(SysConsts.SCHEDULEVO_JOB_TYPE_ONETIME);
        SchedulerJobUtil.addJob(scheduleVo);
        examManageEbi.saveLog(scheduleVo, scheduleVo.getDescribe());
    }

    public List<StudentExamQuestionVo> getStudentAnswer(String studentExamId, StudentVo loginStudent) throws Exception
    {
        StudentExamVo studentExamPo = studentExamDao.get(studentExamId);
        ExamVo examPo = studentExamPo.getExam();
        examManageEbi.checkPermission(SysConsts.EXAM_OPERATION_ENTER, loginStudent, examPo);

        List<StudentExamQuestionVo> answerList = studentExamQuestionDao.getAllByStudentExam(studentExamPo);
        return answerList;
    }

    public List<QuestionVo> getPaperQuestion(String studentExamId, StudentVo loginStudent) throws Exception
    {
        StudentExamVo studentExamPo = studentExamDao.get(studentExamId);

        List<QuestionVo> questionList;
        PaperVo paperPo;
        ExamVo examPo;
        if ((examPo=studentExamPo.getExam()) == null
                ||(paperPo=paperMongoDao.getPaperVoByExamId(examPo.getId()))==null
                ||(questionList=paperPo.getQuestionList())==null)
        {
            throw new OperationException("未找到考试题目");
        }

        examManageEbi.checkPermission(SysConsts.EXAM_OPERATION_ENTER, loginStudent, examPo);
        return questionList;
    }


}
