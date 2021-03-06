package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.authentic.dao.dao.StudentDao;
import com.njmsita.exam.authentic.dao.dao.TeacherDao;
import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.*;
import com.njmsita.exam.manager.dao.dao.PaperExamDao;
import com.njmsita.exam.manager.model.*;
import com.njmsita.exam.manager.model.querymodel.ExamEditWrapper;
import com.njmsita.exam.manager.model.querymodel.ExamListQueryModel;
import com.njmsita.exam.manager.service.ebi.ExamManageEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.ItemNotFoundException;
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
    private PaperExamDao paperExamDao;

    @Autowired
    private PaperTemplateDao paperTemplateDao;


    /**
     * 作废
     *
     * @param examVo
     */
    @Transactional
    public void save(ExamVo examVo)
    {
        examDao.save(examVo);
    }

    public List<ExamVo> getAll()
    {
        return examDao.getAll();
    }

    @Transactional
    public ExamVo get(Serializable uuid)
    {
        ExamVo examPo = examDao.get(uuid);
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


    @Transactional
    public void update(ExamVo examVo)
    {
        examDao.update(examVo);
    }

    @Transactional
    public void delete(ExamVo examVo)
    {
        examDao.delete(examVo);
        paperExamDao.deletePaperFromMongoExamPaper(examVo.getId());
    }

    @Transactional
    public void save(ExamEditWrapper wrapper, TeacherVo loginTeacher) throws Exception
    {

        checkPermission(SysConsts.EXAM_OPERATION_EDIT, loginTeacher, null);
        wrapper.getExam().setCreateTeacher(loginTeacher);
        if (loginTeacher.IsAdmin())
        {
            wrapper.getExam().setExamStatus(SysConsts.EXAM_STATUS_WAITING);
        } else
        {
            wrapper.getExam().setExamStatus(SysConsts.EXAM_STATUS_NO_CHECK);
        }
        setFieldsAndValidate(wrapper.getExam(), wrapper);
        examDao.save(wrapper.getExam());
        paperExamDao.updatePaperFromMongoExamPaper(wrapper.getExam().getPaperVo(), wrapper.getExam().getId());
        createStudentExamByExam(wrapper.getExam(), wrapper.get_classroomIds());
        startIfNecessary(wrapper, loginTeacher);
    }

    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------

    /**
     * 编辑操作只有在考试未开始时才能执行
     *
     * @param wrapper
     * @param loginTeacher
     *
     * @throws Exception
     */
    @Transactional
    public void update(ExamEditWrapper wrapper, TeacherVo loginTeacher) throws Exception
    {
        ExamVo examPo = getExamNotNull(wrapper.getExam());
        checkPermission(SysConsts.EXAM_OPERATION_EDIT, loginTeacher, examPo);
        examPo.setCloseTime(wrapper.getExam().getCloseTime());
        examPo.setOpenTime(wrapper.getExam().getOpenTime());
        examPo.setDuration(wrapper.getExam().getDuration());
        examPo.setSubject(wrapper.getExam().getSubject());
        examPo.setName(wrapper.getExam().getName());
        examPo.setOpenDuration(wrapper.getExam().getOpenDuration());
        wrapper.setExam(examPo);

        setFieldsAndValidate(examPo, wrapper);
        paperExamDao.updatePaperFromMongoExamPaper(examPo.getPaperVo(), examPo.getId());
        createStudentExamByExam(examPo, wrapper.get_classroomIds());
        startIfNecessary(wrapper, loginTeacher);
    }


    private void startIfNecessary(ExamEditWrapper wrapper, TeacherVo teacherVo) throws Exception
    {
        if (teacherVo.IsAdmin())
        {
            createExamSchedules(wrapper.getExam(), wrapper.getImmediately());
        }else
        {
            wrapper.getExam().setExamStatus(SysConsts.EXAM_STATUS_NO_CHECK);

        }
    }

    public ExamVo getWithPaper(Serializable uuid)
    {
        ExamVo examPo = examDao.getExamWithPaper(uuid);
        return examPo;
    }

    public List<ExamVo> getByCreateTeacher(String teacherId) throws Exception
    {
        List<ExamVo> list = examDao.getEvictObjects(examDao.getByCreateTeacher(teacherId));
        operationValidate(list, teacherId);
        return list;
    }

    public List<ExamVo> getByMarkTeacher(String teacherId) throws Exception
    {
        List<ExamVo> list = examDao.getEvictObjects(examDao.getByMarkTeacher(teacherId));
        operationValidate(list, teacherId);
        return list;
    }

    @Transactional
    public void setPass(ExamVo examVo, TeacherVo loginTeacher) throws Exception
    {
        ExamVo examPo = getExamNotNull(examVo);
        if ((examPo.getOpenTime() - System.currentTimeMillis()) < 0)
        {
            examPo.setExamStatus(SysConsts.EXAM_STATUS_OUTDATED);
            throw new OperationException("该场考试已经过时，请勿执行该操作！");
        }
        checkPermission(SysConsts.EXAM_OPERATION_REVIEW, loginTeacher, examPo);
        examPo.setExamStatus(SysConsts.EXAM_STATUS_WAITING);
        createExamSchedules(examPo, false);
    }

    @Transactional
    public void setNoPass(ExamVo examVo, TeacherVo loginTeacher) throws Exception
    {
        ExamVo examPo = getExamNotNull(examVo);
        checkPermission(SysConsts.EXAM_OPERATION_REVIEW, loginTeacher, examPo);
        if (StringUtil.isEmpty(examVo.getRemark()))
        {
            throw new OperationException("驳回考试请求时“备注”不能为空");
        }
        examPo.setRemark(examVo.getRemark());
        examPo.setExamStatus(SysConsts.EXAM_STATUS_NO_PASS);
    }

    @Transactional
    public void cancel(ExamVo examVo, TeacherVo loginTeacher) throws Exception
    {
        ExamVo examPo = getExamNotNull(examVo);
        checkPermission(SysConsts.EXAM_OPERATION_CANCEL, loginTeacher, examPo);

        examPo.setExamStatus(SysConsts.EXAM_STATUS_CANCELED);
        studentExamDao.deleteAllByExam(examVo);

        //取消时直接删除任务
        List<ScheduleVo> scheduleList = scheduleDao.getByTarget(examPo.getId());
        for (ScheduleVo scheduleVo : scheduleList)
        {
            scheduleVo.setJobStatus(SysConsts.SCHEDULEVO_JOB_STATUS_FORBIDDEN);
            new SchedulerJobUtil().deleteJob(scheduleVo, schedulerFactoryBean.getScheduler());
            //new SchedulerJobUtil().pauseJob(scheduleVo, schedulerFactoryBean.getScheduler());
           // log(scheduleVo, "删除任务");
            //log(scheduleVo, "禁用任务");
        }
    }

    @Transactional
    public void deleteCanceled(ExamVo examVo, TeacherVo loginTeacher) throws Exception
    {
        ExamVo examPo = getExamNotNull(examVo);
        checkPermission(SysConsts.EXAM_OPERATION_DELETE, loginTeacher, examPo);

        List<ScheduleVo> scheduleList = scheduleDao.getByTarget(examPo.getId());
        //取消时直接删除任务
//        for (ScheduleVo scheduleVo : scheduleList)
//        {
//            scheduleVo.setJobStatus(SysConsts.SCHEDULEVO_JOB_STATUS_DELETE);
//            new SchedulerJobUtil().deleteJob(scheduleVo, schedulerFactoryBean.getScheduler());
//            log(scheduleVo, "删除任务");
//        }
        examDao.delete(examPo);
        paperExamDao.deletePaperFromMongoExamPaper(examPo.getId());
    }

    @Transactional
    public void updateMarkTeacher(ExamVo examVo, String[] markTeachers) throws Exception
    {
        ExamVo examPo = getExamNotNull(examVo);
        examPo.setMarkTeachers(getMarkTeachers(markTeachers));
    }

    public Set<String> getValidOperations(ExamVo exam, TeacherVo loginTeacher) throws Exception
    {

        Set<String> operationSet = new HashSet<>();

        if (loginTeacher == null)
        {
            throw new UnLoginException();
        }
        Hibernate.initialize(loginTeacher);
        if (exam == null)
        {
            //exam ==null 则为添加考试
            operationSet.add(SysConsts.EXAM_OPERATION_EDIT);
        }else{
            //所有状态下
            // 是该老师发起的考试
            // 或老师批阅的考试
            // 或登录的老师是管理员
            // 则允许浏览
            if (loginTeacher.IsAdmin()
                    || exam.getCreateTeacher().getId().equals(loginTeacher.getId())
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
                        operationSet.add(SysConsts.EXAM_OPERATION_REVIEW);
                    }
                    if (exam.getCreateTeacher().equalsById(loginTeacher))
                    {
                        operationSet.add(SysConsts.EXAM_OPERATION_EDIT);
                        operationSet.add(SysConsts.EXAM_OPERATION_CANCEL);
                    }
                    if (exam.getCreateTeacher().equalsById(loginTeacher) || loginTeacher.IsAdmin())
                    {
                        operationSet.add(SysConsts.EXAM_OPERATION_ADD_MARK_TEACHER);
                    }

                    break;
                case SysConsts.EXAM_STATUS_WAITING:
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
                    if (loginTeacher.IsAdmin())
                    {
                        operationSet.add(SysConsts.EXAM_OPERATION_CANCEL);
                    }
                    operationSet.add(SysConsts.EXAM_OPERATION_ADD_MARK_TEACHER);
                    if (SysConsts.SUPER_ADMIN_ID.equals(loginTeacher.getId()))
                    {
                        operationSet.add(SysConsts.EXAM_OPERATION_STOP);
                    }
                    break;

                case SysConsts.EXAM_STATUS_CLOSED:
                    if (loginTeacher.IsAdmin())
                    {
                        operationSet.add(SysConsts.EXAM_OPERATION_CANCEL);
                    }
                    if (SysConsts.SUPER_ADMIN_ID.equals(loginTeacher.getId()))
                    {
                        operationSet.add(SysConsts.EXAM_OPERATION_STOP);
                    }
                    break;
                case SysConsts.EXAM_STATUS_MARKING:
                    if (loginTeacher.IsAdmin())
                    {
                        operationSet.add(SysConsts.EXAM_OPERATION_CANCEL);
                    }
                    operationSet.add(SysConsts.EXAM_OPERATION_ADD_MARK_TEACHER);
                    if (exam.getMarkTeachers().contains(loginTeacher))
                    {
                        operationSet.add(SysConsts.EXAM_OPERATION_MARK);
                        operationSet.add(SysConsts.EXAM_OPERATION_SUBMIT_MARK);
                    }
                    break;
                case SysConsts.EXAM_STATUS_CANCELED:
                    if (exam.getCreateTeacher().equalsById(loginTeacher))
                    {
                        operationSet.add(SysConsts.EXAM_OPERATION_EDIT);
                    }
                    if (loginTeacher.IsAdmin())
                    {
                        operationSet.add(SysConsts.EXAM_OPERATION_DELETE);
                    }
                    break;
                case SysConsts.EXAM_STATUS_OUTDATED:
                    if (exam.getCreateTeacher().equalsById(loginTeacher))
                    {
                        operationSet.add(SysConsts.EXAM_OPERATION_CANCEL);
                        operationSet.add(SysConsts.EXAM_OPERATION_EDIT);
                    }
                    if (loginTeacher.IsAdmin())
                    {
                        operationSet.add(SysConsts.EXAM_OPERATION_CANCEL);
                        operationSet.add(SysConsts.EXAM_OPERATION_DELETE);
                    }
                    break;
                case SysConsts.EXAM_STATUS_ENDING:
                    operationSet.add(SysConsts.EXAM_OPERATION_REPORT);
                    break;
            }
        }



        return operationSet;

    }

    public Set<String> getValidOperations(StudentExamVo studentExamVo, StudentVo loginStudent) throws UnLoginException
    {
        ExamVo exam = studentExamVo.getExam();
        Set<String> operationSet = new HashSet<>();
        if (loginStudent == null)
        {
            throw new UnLoginException();
        }
        switch (exam.getExamStatus())
        {
            case SysConsts.EXAM_STATUS_NO_CHECK:
                break;
            case SysConsts.EXAM_STATUS_WAITING:
                operationSet.add(SysConsts.EXAM_OPERATION_PREVIEW_VISIBLE);
                operationSet.add(SysConsts.EXAM_OPERATION_PREVIEW);
                break;
            case SysConsts.EXAM_STATUS_NO_PASS:
                break;
            case SysConsts.EXAM_STATUS_OPEN:
                operationSet.add(SysConsts.EXAM_OPERATION_ENTER);
                operationSet.add(SysConsts.EXAM_OPERATION_PREVIEW);

                break;
            case SysConsts.EXAM_STATUS_CLOSED:
                operationSet.add(SysConsts.EXAM_OPERATION_PREVIEW);
                if (studentExamVo.getStatus() == SysConsts.STUDENT_EXAM_STATUS_STARTED)
                {
                    operationSet.add(SysConsts.EXAM_OPERATION_ENTER);
                } else
                {
                    operationSet.add(SysConsts.EXAM_OPERATION_PREVIEW_VISIBLE);
                }
                break;
            case SysConsts.EXAM_STATUS_SUBMITTED:
                operationSet.add(SysConsts.EXAM_OPERATION_PREVIEW_VISIBLE);
                operationSet.add(SysConsts.EXAM_OPERATION_PREVIEW);
                break;
            case SysConsts.EXAM_STATUS_MARKING:
                operationSet.add(SysConsts.EXAM_OPERATION_PREVIEW_VISIBLE);
                operationSet.add(SysConsts.EXAM_OPERATION_PREVIEW);
                break;
            case SysConsts.EXAM_STATUS_CANCELED:
                break;
            case SysConsts.EXAM_STATUS_ENDING:
                operationSet.add(SysConsts.EXAM_OPERATION_RESULT);
                break;
        }

        return operationSet;
    }

    public boolean checkPermission(String permission, TeacherVo loginTeacher, ExamVo exam) throws Exception
    {
        if (!getValidOperations(exam, loginTeacher).contains(permission))
        {
            throw new UnAuthorizedException("您没有当前" + SysConsts.ExamOperationViewMap.get(permission) + "操作的权限");
        }
        return true;
    }

    public boolean checkPermission(String permission, StudentVo loginStudent, StudentExamVo studentExam) throws UnAuthorizedException, UnLoginException, Exception
    {
        if (!getValidOperations(studentExam, loginStudent).contains(permission))
        {
            throw new UnAuthorizedException("您没有当前" + SysConsts.ExamOperationViewMap.get(permission) + "操作的权限");
        }
        return true;
    }

    @Transactional
    public void invoke(ExamInvoker examInvoker) throws Exception
    {
        examInvoker.invoke(this);
    }

    @Transactional(readOnly = true)
    public List<ExamVo> getAllByAdmin(String teacherId, ExamListQueryModel examListQueryModel) throws Exception
    {
        List<ExamVo> list = examDao.getAll(examListQueryModel);
        operationValidate(list, teacherId);
        return list;
    }

    public List<StudentExamVo> getAllStudentExamByExam(ExamVo examVo, TeacherVo login) throws Exception
    {
        Set<TeacherVo> markTeachers = examVo.getMarkTeachers();
        if (!markTeachers.contains(login))
        {
            throw new OperationException("您不是该场考试的阅卷教师");
        }
        List<StudentExamVo> list = studentExamDao.getAllStudentExambyExamId(examVo.getId());
        for (StudentExamVo studentExamVo : list)
        {
//            List<StudentExamQuestionVo> questionList = this.getAllStudentexamQuestionByStudentExam(studentExamVo);
//            if (questionList.size() == 0)
//            {
//                list.remove(studentExamVo);
//            }
        }
        return null;
    }


    //-----------------------------------以上为业务操作-------------------------------------
    //-----------------------------------以上为业务操作-------------------------------------
    //-----------------------------------以上为业务操作-------------------------------------
    //-----------------------------------以上为业务操作-------------------------------------
    //-----------------------------------以上为业务操作-------------------------------------

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

    @Override
    public ExamVo getExamNotNull(String examId) throws OperationException
    {
        ExamVo temp = examDao.get(examId);
        if (temp == null)
        {
            throw new OperationException("所选该场考试不存在");
        }
        return temp;
    }

    /**
     * 记录日志
     *
     * @param scheduleVo
     */
    public void log(ScheduleVo scheduleVo, String method)
    {

        LogVo logVo = new LogVo();
        logVo.setModule("考试管理");
        logVo.setMethod(method);
        logVo.setTime(System.currentTimeMillis());
        logVo.setArgument(scheduleVo.getClass().getName() + "-->" + scheduleVo.toString());
        logDao.save(logVo);
    }

    @Transactional
    public void deleteUsedSchedule(ScheduleVo scheduleVo)
    {
        scheduleDao.delete(scheduleVo);
    }

    @Override
    @Transactional
    public void stop(String examId, TeacherVo loginTeacher) throws Exception
    {
        ExamVo examPo = examDao.get(examId);
        checkPermission(SysConsts.EXAM_OPERATION_STOP, loginTeacher, examPo);
        examPo.setExamStatus(SysConsts.EXAM_STATUS_MARKING);
        examPo.setCloseTime(System.currentTimeMillis());
        List<ScheduleVo> scheduleList = scheduleDao.getByTarget(examPo.getId());
        for (ScheduleVo scheduleVo : scheduleList)
        {
            SchedulerJobUtil.deleteJob(scheduleVo, schedulerFactoryBean.getScheduler());
            scheduleDao.delete(scheduleVo);
        }
    }

    public List<ScheduleVo> getAllByExecutable()
    {
        return scheduleDao.getAllByExecutable();
    }

    public void updateSchedule(ScheduleVo scheduleVo)
    {
        scheduleDao.update(scheduleVo);
    }

    //检查该考试的试卷是否只有选择题
    public boolean paperIsSelectProblemsOnly(ExamVo examVo) throws ItemNotFoundException
    {
        if (examVo == null)
        {
            throw new ItemNotFoundException("该考试不存在");
        }
        if (examVo.getPaperVo() == null)
        {
            examDao.initPaperForExam(examVo);
        }
        examVo.getPaperVo();
        List<QuestionVo> questions = examVo.getPaperVo().getQuestionList();
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
                return false;
            }
        }
        return true;
    }

    private void finish(ExamVo examPo)
    {

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
    public ExamVo getExamWithPaperNotNull(ExamVo examVo) throws ItemNotFoundException
    {
        ExamVo temp = examDao.getExamWithPaper(examVo.getId());
        if (temp == null)
        {
            throw new ItemNotFoundException("所选该场考试不存在");
        }
        if (examVo.getPaperVo() == null)
        {
            throw new ItemNotFoundException("本场考试的试卷不存在");
        }
        return temp;
    }

    /**
     * 创建定时任务
     *
     * @param exam
     *
     * @throws Exception
     */
    private void createSchedulerJob(ExamVo exam, Integer action) throws Exception
    {
        ScheduleVo scheduleVo = new ScheduleVo();
        String jobTypeView = SysConsts.EXAM_SCHEDULE_ACTION_TYPE_MAP.get(action);

        //设置任务信息
        scheduleVo.setId(IdUtil.getUUID());
        scheduleVo.setJobName(exam.getName() + "_" + jobTypeView);
        scheduleVo.setJobGroup(exam.getId());
        scheduleVo.setDescribe("定时" + jobTypeView + ".ExamId：" + exam.getId());
        scheduleVo.setJobType(action);
        scheduleVo.setTargetVoId(exam.getId());
        scheduleVo.setScheduleActionType(action);
        switch (action)
        {
            case SysConsts.EXAM_SCHEDULE_ACTION_TYPE_EXAM_START:
                scheduleVo.setStatusAfter(SysConsts.EXAM_STATUS_OPEN);
                scheduleVo.setCronexpression(FormatUtil.cronExpression(exam.getOpenTime()));
                break;
            case SysConsts.EXAM_SCHEDULE_ACTION_TYPE_EXAM_CLOSE_ENTRANCE:
                scheduleVo.setStatusAfter(SysConsts.EXAM_STATUS_CLOSED);
                scheduleVo.setCronexpression(FormatUtil.cronExpression(exam.getOpenTime() + exam.getOpenDuration() * 60 * 1000));
                break;
            case SysConsts.EXAM_SCHEDULE_ACTION_TYPE_EXAM_END:
                scheduleVo.setStatusAfter(SysConsts.EXAM_STATUS_MARKING);

              scheduleVo.setCronexpression(FormatUtil.cronExpression(exam.getCloseTime()));
                break;
        }
        scheduleVo.setJobStatus(SysConsts.SCHEDULEVO_JOB_STATUS_START);
        scheduleDao.save(scheduleVo);
        SchedulerJobUtil.createJob(scheduleVo, schedulerFactoryBean.getScheduler());
        log(scheduleVo, scheduleVo.getDescribe());
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
    private void operationValidate(List<ExamVo> list, String teacherId) throws Exception
    {
        TeacherVo loginTeacher = teacherDao.get(teacherId);
        for (ExamVo examVo : list)
        {
            examVo.setOperation(getValidOperations(examVo, loginTeacher));
        }
    }

    public void setFieldsAndValidate(ExamVo exam, ExamEditWrapper wrapper) throws Exception
    {
        if (wrapper.get_classroomIds() == null || wrapper.get_classroomIds().size() == 0)
        {
            throw new OperationException("参加考试的班级不能为空");
        }
        Set<ClassroomVo> classroomVoSet = new HashSet<>();
        for (String classroomid : wrapper.get_classroomIds())
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
        if (StringUtil.isEmpty(wrapper.getPaperId())
                || ((paperVo = paperTemplateDao.get(wrapper.getPaperId())) == null))
        {
            throw new OperationException("试卷不能为空");
        }
        exam.setPaperVo(paperVo);

        Set<TeacherVo> teacherSet = getMarkTeachers(wrapper.getMarkerTeachers());
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
        if (exam.getOpenTime() > exam.getCloseTime() || wrapper.getImmediately() == true && System.currentTimeMillis() > exam.getCloseTime())
        {
            throw new OperationException("结束时间不能早于开始时间");
        }
        if (exam.getCloseTime() < exam.getOpenTime() + exam.getDuration() * 60 * 1000)
        {
            throw new OperationException("答题时间不足");
        }
        if (wrapper.getImmediately() == true)
        {

        } else if (exam.getOpenTime() < System.currentTimeMillis())
        {
            throw new OperationException("开始时间已过");
        }

    }




    private void createExamSchedules(ExamVo examVo, boolean immediately) throws Exception
    {
        if (immediately)
        {
            examVo.setExamStatus(SysConsts.EXAM_STATUS_OPEN);

        } else
        {
            examVo.setExamStatus(SysConsts.EXAM_STATUS_WAITING);
            createSchedulerJob(examVo, SysConsts.EXAM_SCHEDULE_ACTION_TYPE_EXAM_START);
        }
        if (examVo.getOpenDuration() != 0)
        {
            createSchedulerJob(examVo, SysConsts.EXAM_SCHEDULE_ACTION_TYPE_EXAM_CLOSE_ENTRANCE);
        }
        createSchedulerJob(examVo, SysConsts.EXAM_SCHEDULE_ACTION_TYPE_EXAM_END);
    }


    private void createStudentExamByExam(ExamVo examVo, List<String> classroomIds) throws OperationException
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
                    studentExamVo.setExam(examVo);
                    studentExamVo.setStudent(studentVo);
                    studentExamVo.setStatus(SysConsts.STUDENT_EXAM_STATUS_NOT_STARTED);
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
        if (markTeachers == null || markTeachers.length == 0)
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