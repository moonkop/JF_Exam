package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.authentic.dao.dao.StudentDao;
import com.njmsita.exam.authentic.dao.dao.TeacherDao;
import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.authentic.model.UserModel;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.*;
import com.njmsita.exam.manager.model.*;
import com.njmsita.exam.manager.model.querymodel.ExamQueryModel;
import com.njmsita.exam.manager.service.ebi.ExamManageEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.exception.UnAuthorizedException;
import com.njmsita.exam.utils.exception.UnLoginException;
import com.njmsita.exam.utils.format.FormatUtil;
import com.njmsita.exam.utils.format.StringUtil;
import com.njmsita.exam.utils.idutil.IdUtil;
import com.njmsita.exam.utils.timertask.SchedulerJobUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

/**
 * 考试业务层实现类
 */
@Service
@Transactional
public class ExamManageEbo implements ExamManageEbi
{
    @Autowired
    private ExamDao examDao;
    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private ClassroomDao classroomDao;
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
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
    private QuestionTypeDao questionTypeDao;

    @Autowired
    private PaperMongoDao paperMongoDao;

    /**
     * 作废
     *
     * @param examVo
     */
    public void save(ExamVo examVo)
    {
        examDao.save(examVo);
    }

    public List<ExamVo> getAll()
    {
        return examDao.getAll();
    }

    public ExamVo get(Serializable uuid)
    {
        ExamVo examPo = examDao.get(uuid);
        return examPo;
    }
    public ExamVo getWithPaper(Serializable uuid)
    {
        ExamVo examPo = examDao.getExamWithPaper(uuid);
        return examPo;
    }


    public List<ExamVo> getAll(BaseQueryVO qm, Integer pageNum, Integer pageCount)
    {
        return examDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryVO qm)
    {
        return examDao.getCount(qm);
    }

    /**
     * 作废
     *
     * @param examVo
     */
    public void update(ExamVo examVo)
    {
        examDao.update(examVo);
    }

    public void delete(ExamVo examVo)
    {
        examDao.delete(examVo);
        paperMongoDao.deletePaperFromMongoExamPaper(examVo.getId());
    }


    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------

    public void save(ExamVo examVo, String[] markTeachers, String paperId, String[] classroomIds) throws OperationException
    {
        setFieldsAndValidate(examVo, markTeachers, paperId, classroomIds);
        examVo.setExamStatus(SysConsts.EXAM_STATUS_NO_CHECK);
        examDao.save(examVo);
        createStudentExamByExam(examVo, classroomIds);
        paperMongoDao.savaPaperToMongoExamPaper(examVo.getPaperVo(), examVo.getId());
    }

    public void update(ExamVo examVo, String[] markTeachers, String paperId, String[] classroomIds, TeacherVo teacherVo) throws Exception
    {
        ExamVo examPo = getExamNotNull(examVo);
        checkPermission(SysConsts.EXAM_OPERATION_EDIT, teacherVo, examPo);
        examPo.setCloseTime(examVo.getCloseTime());
        examPo.setOpenTime(examVo.getOpenTime());
        examPo.setDuration(examVo.getDuration());
        examPo.setSubject(examVo.getSubject());
        examPo.setName(examVo.getName());
        examPo.setOpenDuration(examPo.getOpenDuration());
        examPo.setRemark(examVo.getRemark());
        if (examPo.getExamStatus() == SysConsts.EXAM_STATUS_NO_PASS || examPo.getExamStatus() == SysConsts.EXAM_STATUS_IN_CANCEL)
        {
            examPo.setExamStatus(SysConsts.EXAM_STATUS_NO_CHECK);
        }
        setFieldsAndValidate(examPo, markTeachers, paperId, classroomIds);
        createStudentExamByExam(examPo, classroomIds);
        paperMongoDao.updatePaperFromMongoExamPaper(examPo.getPaperVo(), examPo.getId());

    }

    public List<ExamVo> getByCreateTeacher(String teacherId) throws Exception
    {
        List<ExamVo> list = examDao.getByCreateTeacher(teacherId);
        operationValid(list, teacherId);
        return list;
    }

    public List<ExamVo> getByMarkTeacher(String teacherId) throws Exception
    {
        List<ExamVo> list = examDao.getByMarkTeacher(teacherId);
        operationValid(list, teacherId);
        return list;
    }

    public void setPass(ExamVo examVo, TeacherVo loginTeacher) throws Exception
    {
        ExamVo examPo = getExamNotNull(examVo);
        checkPermission(SysConsts.EXAM_OPERATION_JUDGE, loginTeacher, examPo);
        examPo.setExamStatus(SysConsts.EXAM_STATUS_PASS);
        createSchedulerJob(examPo, SysConsts.SCHEDULEVO_JOB_TYPE_OPEN);

        if (examVo.getOpenDuration() !=0)
        {
            createSchedulerJob(examPo, SysConsts.SCHEDULEVO_JOB_TYPE_CLOSE);
        }
        createSchedulerJob(examPo, SysConsts.SCHEDULEVO_JOB_TYPE_FINISH);
    }

    public void setNoPass(ExamVo examVo, TeacherVo loginTeacher) throws Exception
    {
        ExamVo examPo = getExamNotNull(examVo);
        checkPermission(SysConsts.EXAM_OPERATION_JUDGE, loginTeacher, examPo);
        if (StringUtil.isEmpty(examVo.getRemark()))
        {
            throw new OperationException("驳回考试请求时“备注”不能为空");
        }
        examPo.setRemark(examVo.getRemark());
        examPo.setExamStatus(SysConsts.EXAM_STATUS_NO_PASS);
    }

    public void cancel(ExamVo examVo, TeacherVo loginTeacher) throws Exception
    {
        ExamVo examPo = getExamNotNull(examVo);
        checkPermission(SysConsts.EXAM_OPERATION_CANCEL, loginTeacher, examPo);

        examPo.setExamStatus(SysConsts.EXAM_STATUS_IN_CANCEL);
        studentExamDao.deleteAllByExam(examVo);
        List<ScheduleVo> scheduleList = scheduleDao.getByTarget(examPo.getId());
        for (ScheduleVo scheduleVo : scheduleList)
        {
            scheduleVo.setJobStatus(SysConsts.SCHEDULEVO_JOB_STATUS_FORBIDDEN);
            new SchedulerJobUtil().pauseJob(scheduleVo, schedulerFactoryBean.getScheduler());
            saveLog(scheduleVo, "禁用任务");
        }
    }

    public void deleteCanceled(ExamVo examVo, TeacherVo loginTeacher) throws Exception
    {
        ExamVo examPo = getExamNotNull(examVo);
        checkPermission(SysConsts.EXAM_OPERATION_DELETE, loginTeacher, examVo);

        List<ScheduleVo> scheduleList = scheduleDao.getByTarget(examPo.getId());
        for (ScheduleVo scheduleVo : scheduleList)
        {
            scheduleVo.setJobStatus(SysConsts.SCHEDULEVO_JOB_STATUS_DELETE);
            new SchedulerJobUtil().deleteJob(scheduleVo, schedulerFactoryBean.getScheduler());
            saveLog(scheduleVo, "删除任务");
        }
        examDao.delete(examPo);
        paperMongoDao.deletePaperFromMongoExamPaper(examPo.getId());
    }

    public void updateMarkTeacher(ExamVo examVo, String[] markTeachers) throws Exception
    {
        ExamVo temp = getExamNotNull(examVo);

        //TODO 测试：是否可以逻辑更新
        temp.setMarkTeachers(getMarkTeachers(markTeachers));
    }


    //------------学生方法---------------------//
    //------------学生方法---------------------//
    //------------学生方法---------------------//
    //------------学生方法---------------------//
    public List<ExamVo> getStudentExamList(String studentId) throws UnLoginException
    {

        List<StudentExamVo> studentExamVos = studentExamDao.getByStudent(studentId);
        StudentVo loginStudent = studentDao.get(studentId);
        List<ExamVo> list = new ArrayList<>();
        for (StudentExamVo studentExamVo : studentExamVos)
        {
            ExamVo examVo = studentExamVo.getExamVo();
            examVo.setOperation(getValidOperations(examVo, loginStudent));
            list.add(examVo);
        }
        return list;
    }

    public Set<String> getValidOperations(ExamVo exam, UserModel loginUser) throws UnLoginException
    {

        Set<String> operationSet = new HashSet<>();

        if (loginUser == null)
        {
            throw new UnLoginException("登录信息失效请重新登录");
        }
        if (loginUser instanceof TeacherVo)
        {
            TeacherVo loginTeacher = (TeacherVo) loginUser;
            Hibernate.initialize(loginTeacher);
            //所有状态下
            // 是该老师发起的考试
            // 或老师批阅的考试
            // 或登录的老师是管理员
            // 则允许浏览
            if (exam.getCreateTeacher().getId().equals(loginTeacher.getId())
                    || loginTeacher.IsAdmin()
                    || exam.getMarkTeachers().contains(loginTeacher))
            {
                operationSet.add(SysConsts.EXAM_OPERATION_VIEW);
            }

            if (exam.getExamStatus() == null)
            {
                return operationSet;
            }
            switch (exam.getExamStatus())
            {
                case SysConsts.EXAM_STATUS_NO_CHECK:
                    if (loginTeacher.IsAdmin())
                    {
                        operationSet.add(SysConsts.EXAM_OPERATION_JUDGE);
                    }
                    if (exam.getCreateTeacher().equalsById(loginTeacher))
                    {
                        operationSet.add(SysConsts.EXAM_OPERATION_EDIT);
                        operationSet.add(SysConsts.EXAM_OPERATION_CANCEL);
                    }
                    if (exam.getCreateTeacher().equalsById(loginTeacher)||loginTeacher.IsAdmin())
                    {
                        operationSet.add(SysConsts.EXAM_OPERATION_ADD_MARK_TEACHER);
                    }

                    break;
                case SysConsts.EXAM_STATUS_PASS:
                    if (loginTeacher.IsAdmin())
                    {
                        operationSet.add(SysConsts.EXAM_OPERATION_CANCEL);
                    }
                    operationSet.add(SysConsts.EXAM_OPERATION_ADD_MARK_TEACHER);
                    break;
                case SysConsts.EXAM_STATUS_NO_PASS:
                    operationSet.add(SysConsts.EXAM_OPERATION_EDIT);
                    operationSet.add(SysConsts.EXAM_OPERATION_CANCEL);
                    operationSet.add(SysConsts.EXAM_OPERATION_ADD_MARK_TEACHER);
                    break;
                case SysConsts.EXAM_STATUS_OPEN:
                    operationSet.add(SysConsts.EXAM_OPERATION_ADD_MARK_TEACHER);

                    break;
                case SysConsts.EXAM_STATUS_CLOSE:

                    break;
                case SysConsts.EXAM_STATUS_IN_MARK:
                    operationSet.add(SysConsts.EXAM_OPERATION_ADD_MARK_TEACHER);
                    if (exam.getMarkTeachers().contains(loginTeacher))
                    {
                        operationSet.add(SysConsts.EXAM_OPERATION_MARK);
                        operationSet.add(SysConsts.EXAM_OPERATION_SUBMIT_MARK);
                    }
                    break;
                case SysConsts.EXAM_STATUS_IN_CANCEL:
                    if (exam.getCreateTeacher().equalsById(loginTeacher))
                    {
                        operationSet.add(SysConsts.EXAM_OPERATION_EDIT);
                    }
                    if (loginTeacher.IsAdmin())
                    {
                        operationSet.add(SysConsts.EXAM_OPERATION_DELETE);
                    }
                    break;
                case SysConsts.EXAM_STATUS_ENDING:
                    operationSet.add(SysConsts.EXAM_OPERATION_VIEW_SCORE);
                    break;
            }
        } else if (loginUser instanceof StudentVo)
        {
            StudentVo loginStudent = (StudentVo) loginUser;
            switch (exam.getExamStatus())
            {
                case SysConsts.EXAM_STATUS_NO_CHECK:
                    break;
                case SysConsts.EXAM_STATUS_PASS:
                    operationSet.add(SysConsts.EXAM_OPERATION_VIEW_BRIEF);
                    break;
                case SysConsts.EXAM_STATUS_NO_PASS:
                    break;
                case SysConsts.EXAM_STATUS_OPEN:
                    operationSet.add(SysConsts.EXAM_OPERATION_VIEW_BRIEF);
                    operationSet.add(SysConsts.EXAM_OPERATION_ATTEND);
                    break;
                case SysConsts.EXAM_STATUS_CLOSE:
                    operationSet.add(SysConsts.EXAM_OPERATION_VIEW_BRIEF);
                    break;
                case SysConsts.EXAM_STATUS_IN_MARK:
                    operationSet.add(SysConsts.EXAM_OPERATION_VIEW_BRIEF);
                    break;
                case SysConsts.EXAM_STATUS_IN_CANCEL:
                    break;
                case SysConsts.EXAM_STATUS_ENDING:
                    operationSet.add(SysConsts.EXAM_OPERATION_VIEW_SCORE);
                    break;
            }
        }

        return operationSet;

    }

    public boolean checkPermission(String permission, UserModel loginTeacher, ExamVo exam) throws Exception
    {
        if (!getValidOperations(exam, loginTeacher).contains(permission))
        {
            throw new UnAuthorizedException("您没有当前" + SysConsts.ExamOperationViewMap.get(permission) + "操作的权限");
        }
        return true;
    }

    public Set<String> getValidOperations(ExamVo exam, String id)
    {
        UserModel user = studentDao.get(id);
        if (user == null)
        {
            user = teacherDao.get(id);
        }
        return getValidOperations(exam, id);
    }

    public boolean checkPermission(String permission, String LoginUserId, ExamVo exam) throws UnAuthorizedException
    {
        if (!getValidOperations(exam, LoginUserId).contains(permission))
        {
            throw new UnAuthorizedException("您没有当前" + SysConsts.ExamStatusViewMap.get(permission) + "操作的权限");
        }
        return true;
    }

    public List<ExamVo> getAllByAdmin(String teacherId, ExamQueryModel examQueryModel,
                                      Integer pageNum, Integer pageSize) throws Exception
    {
        List<ExamVo> list = examDao.getAll(examQueryModel, pageNum, pageSize);
        operationValid(list, teacherId);
        return list;
    }


    //-----------------------------------以上为业务操作-------------------------------------
    //-----------------------------------以上为业务操作-------------------------------------
    //-----------------------------------以上为业务操作-------------------------------------
    //-----------------------------------以上为业务操作-------------------------------------
    //-----------------------------------以上为业务操作-------------------------------------

    public List<StudentExamVo> getAllStudentExamByExam(ExamVo examVo, TeacherVo login) throws Exception
    {
        Set<TeacherVo> markTeachers = examVo.getMarkTeachers();
        if (!markTeachers.contains(login))
        {
            throw new OperationException("您不是该场考试的阅卷教师");
        }
        List<StudentExamVo> list = studentExamDao.getbyExam(examVo);
        for (StudentExamVo studentExamVo : list)
        {
            List<StudentExamQuestionVo> questionList = this.getAllStudentexamQuestionByStudentExam(studentExamVo);
            if (questionList.size() == 0)
            {
                list.remove(studentExamVo);
            }
        }
        return null;
    }

    public List<StudentExamQuestionVo> getAllStudentexamQuestionByStudentExam(StudentExamVo studentExamVo) throws Exception
    {
        if (StringUtil.isEmpty(studentExamVo.getId()))
        {
            throw new OperationException("所选择的学生试卷不能为空");
        }
        studentExamVo = studentExamDao.get(studentExamVo.getId());
        if (studentExamVo == null)
        {
            throw new OperationException("所选择的学生试卷不存在");
        }
        List<StudentExamQuestionVo> list = studentExamQuestionDao.getAllByStudentExam(studentExamVo);
        for (int i = list.size() - 1; i >= 0; i--)
        {
            StudentExamQuestionVo temp = list.get(i);
            if (!temp.getQuestionTypeVo().getName().equals(SysConsts.NO_ANSWER_QUESTION_TYPE_NAME))
            {
                list.remove(temp);
            }
            if (null != temp.getTeacherVo())
            {
                list.remove(temp);
            }
        }
        return list;
    }

    /**
     * 判空
     *
     * @param examVo
     *
     * @return
     *
     * @throws OperationException
     */
    public ExamVo getExamNotNull(ExamVo examVo) throws OperationException
    {
        ExamVo temp = examDao.get(examVo.getId());
        if (temp == null)
        {
            throw new OperationException("所选该场考试不存在");
        }
        return temp;
    }
    /**
     * 判空
     *
     * @param examVo
     *
     * @return
     *
     * @throws OperationException
     */
    public ExamVo getExamWithPaperNotNull(ExamVo examVo) throws OperationException
    {
        ExamVo temp = examDao.getExamWithPaper(examVo.getId());
        if (temp == null)
        {
            throw new OperationException("所选该场考试不存在");
        }
        if (examVo.getPaperVo() == null)
        {
            throw new OperationException("本场考试的试卷不存在");
        }
        return temp;
    }
    /**
     * 记录日志
     *
     * @param scheduleVo
     */
    public void saveLog(ScheduleVo scheduleVo, String method)
    {

        LogVo logVo = new LogVo();
        logVo.setModule("考试管理");
        logVo.setMethod(method);
        logVo.setTime(System.currentTimeMillis());
        logVo.setArgument(scheduleVo.getClass().getName() + "-->" + scheduleVo.toString());
        logDao.save(logVo);
    }

    /**
     * 创建定时任务
     *
     * @param exam
     *
     * @throws Exception
     */
    private void createSchedulerJob(ExamVo exam, Integer jobType) throws Exception
    {
        ScheduleVo scheduleVo = new ScheduleVo();
        String jobTypeView = SysConsts.SCHEDULEVO_JOB_TYPE_MAP.get(jobType);

        //设置任务信息
        scheduleVo.setId(IdUtil.getUUID());
        scheduleVo.setJobName(exam.getName() + "_" + jobTypeView);
        scheduleVo.setJobGroup(exam.getId());
        scheduleVo.setDescribe("定时" + jobTypeView + ".ExamId：" + exam.getId());
        scheduleVo.setJobType(jobType);
        scheduleVo.setTargetVoId(exam.getId());

        switch (jobType)
        {
            case SysConsts.SCHEDULEVO_JOB_TYPE_OPEN:
                scheduleVo.setNowStatu(SysConsts.EXAM_STATUS_PASS);
                scheduleVo.setAffterStatu(SysConsts.EXAM_STATUS_OPEN);
                scheduleVo.setCronexpression(FormatUtil.cronExpression(exam.getOpenTime()));
                break;
            case SysConsts.SCHEDULEVO_JOB_TYPE_CLOSE:
                scheduleVo.setNowStatu(SysConsts.EXAM_STATUS_OPEN);
                scheduleVo.setAffterStatu(SysConsts.EXAM_STATUS_CLOSE);
                scheduleVo.setCronexpression(FormatUtil.cronExpression(exam.getOpenTime() + exam.getOpenDuration() * 60 * 100));
                break;
            case SysConsts.SCHEDULEVO_JOB_TYPE_FINISH:
                examDao.SetPaper(exam);

                List<QuestionVo> questions = exam.getPaperVo().getQuestionList();
                boolean flag = true;
                Map<Integer, QuestionTypeVo> questionTypeMap = new HashMap<>();
                for (QuestionTypeVo questionType : questionTypeDao.getAll())
                {
                    questionTypeMap.put(questionType.getId(), questionType);
                }
                for (int i = 0; i < questions.size(); i++)
                {
                    QuestionTypeVo questionTypeVo = questionTypeMap.get(questions.get(i).getType());
                    if (questionTypeVo.getName().equals(SysConsts.NO_ANSWER_QUESTION_TYPE_NAME))
                    {
                        flag = false;
                    }
                }
                scheduleVo.setNowStatu(SysConsts.EXAM_STATUS_CLOSE);
                if (flag)
                {
                    scheduleVo.setAffterStatu(SysConsts.EXAM_STATUS_ENDING);
                } else
                {
                    scheduleVo.setAffterStatu(SysConsts.EXAM_STATUS_IN_MARK);
                }
                scheduleVo.setCronexpression(FormatUtil.cronExpression(exam.getOpenTime() + (exam.getDuration() + exam.getOpenDuration() * 60 * 100)));
                break;
        }

        scheduleVo.setDao(examDao);
        scheduleDao.save(scheduleVo);
        new SchedulerJobUtil().createJob(scheduleVo, schedulerFactoryBean.getScheduler());
        saveLog(scheduleVo, scheduleVo.getDescribe());
    }

    /**
     * 解析得到题目的JSON数组
     *
     * @param paperContent
     *
     * @return
     */
    private JSONArray getQuestionJsonArray(String paperContent)
    {
        JSONObject jsonObject = JSONObject.fromObject(paperContent);
        String questionContainJson = jsonObject.getString("questionContain");
        return JSONArray.fromObject(questionContainJson);
    }

    /**
     * 角色校验
     *
     * @param teacherVo
     *
     * @throws OperationException
     */
    private void roleValid(TeacherVo teacherVo) throws OperationException
    {
        if (!SysConsts.ADMIN_ROLE_ID.equals(teacherVo.getRole().getId()))
        {
            throw new OperationException("您不是管理员");
        }
    }

    /**
     * 操作校验
     *
     * @param list
     * @param teacherId
     */
    private void operationValid(List<ExamVo> list, String teacherId) throws Exception
    {
        TeacherVo loginTeacher = teacherDao.get(teacherId);
        for (ExamVo examVo : list)
        {
            examVo.setOperation(getValidOperations(examVo, loginTeacher));
        }
    }

    public void setFieldsAndValidate(ExamVo exam, String[] markTeacherIds, String paperId, String[] classroomIds) throws OperationException
    {
        if (classroomIds == null || classroomIds.length == 0)
        {
            throw new OperationException("参加考试的班级不能为空");
        }
        Set<ClassroomVo> classroomVoSet = new HashSet<>();
        for (String classroomid : classroomIds)
        {
            ClassroomVo classroomVo = classroomDao.get(classroomid);
            if (classroomVo != null)
            {
                classroomVoSet.add(classroomVo);
            } else
            {
                throw new OperationException("您添加的班级不存在");
            }
        }
        exam.setClassrooms(classroomVoSet);

        SubjectVo subjectVo;
        if (exam.getSubject() == null
                || exam.getSubject().getId() == null
                || exam.getSubject().getId() == 0
                || ((subjectVo = subjectDao.get(exam.getSubject().getId())) == null))
        {
            throw new OperationException("科目不能为空");
        }
        exam.setSubject(subjectVo);

        PaperVo paperVo;
        if (StringUtil.isEmpty(paperId)
                || ((paperVo = paperMongoDao.get(paperId)) == null))
        {
            throw new OperationException("试卷不能为空");
        }
        exam.setPaperVo(paperVo);

        Set<TeacherVo> teacherSet = getMarkTeachers(markTeacherIds);
        exam.setMarkTeachers(teacherSet);

        if (StringUtil.isEmpty(exam.getName()))
        {
            throw new OperationException("考试名称不能为空");
        }
        if (exam.getRemark() == null)
        {
            exam.setRemark("");
        }
        if (exam.getOpenTime() == null || exam.getOpenTime() == 0)
        {
            throw new OperationException("开始时间不能为空");
        }
        if (exam.getCloseTime() == null || exam.getCloseTime() == 0)
        {
            throw new OperationException("结束时间不能为空");
        }
        if (exam.getDuration() == null)
        {
            exam.setDuration(0);
        }
        if (exam.getOpenDuration() == null)
        {
            exam.setOpenDuration(0);
        }
        if (exam.getOpenTime() > exam.getCloseTime())
        {
            throw new OperationException("结束时间不能早于开始时间");
        }
        if (exam.getCloseTime() < exam.getOpenTime() + exam.getDuration() * 60 * 1000)
        {
            throw new OperationException("答题时间不足");
        }
        if (exam.getOpenTime() < System.currentTimeMillis())
        {
            throw new OperationException("开始时间已过");
        }
    }

    private void createStudentExamByExam(ExamVo examVo, String[] classroomIds) throws OperationException
    {
        //删除该场考试的所有学生
        studentExamDao.deleteAllByExam(examVo);

        int i = 0;
        for (String classroomId : classroomIds)
        {
            ClassroomVo classroomVo = classroomDao.get(classroomId);
            if (classroomVo != null)
            {
                List<StudentVo> studentList = studentDao.getByClassroom(classroomId);
                for (StudentVo studentVo : studentList)
                {
                    StudentExamVo studentExamVo = new StudentExamVo();
                    studentExamVo.setId(IdUtil.getUUID());
                    studentExamVo.setExamVo(examVo);
                    studentExamVo.setStudentVo(studentVo);
                    studentExamDao.save(studentExamVo);
                }
            } else
            {
                throw new OperationException("所选班级id为" + classroomId + "的班级不存在");
            }
        }
    }


    /**
     * 校验教师ID
     *
     * @param markTeachers
     *
     * @return
     */
    private Set<TeacherVo> getMarkTeachers(String[] markTeachers)
    {
        Set<TeacherVo> teacherSet = new HashSet<>();
        if (markTeachers == null || markTeachers.length > 0)
        {
            return teacherSet;
        }

        for (String markTeacher : markTeachers)
        {
            TeacherVo teacherVo = teacherDao.get(markTeacher);
            if (teacherVo != null)
            {
                teacherSet.add(teacherVo);
            }
        }

        return teacherSet;
    }


}