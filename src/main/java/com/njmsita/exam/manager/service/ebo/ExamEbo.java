package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.authentic.dao.dao.StudentDao;
import com.njmsita.exam.authentic.dao.dao.TeacherDao;
import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.*;
import com.njmsita.exam.manager.model.*;
import com.njmsita.exam.manager.model.querymodel.ExamQueryModel;
import com.njmsita.exam.manager.service.ebi.ExamEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.format.FormatUtil;
import com.njmsita.exam.utils.format.StringUtil;
import com.njmsita.exam.utils.idutil.IdUtil;
import com.njmsita.exam.utils.timertask.SchedulerJobUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
public class ExamEbo implements ExamEbi
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
        return queryPaperFromMongoExamPaper(examDao.get(uuid));
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
     * @param examVo
     */
    public void update(ExamVo examVo)
    {
        examDao.update(examVo);
    }

    public void delete(ExamVo examVo)
    {
        examDao.delete(examVo);
        deletePaperFromMongoExamPaper(examVo);
    }


    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------

    public void save(ExamVo examVo, String[] markTeachers,String paperId ,String[] classroomIds) throws OperationException
    {
        infoValid(examVo,markTeachers,paperId,classroomIds);
        examDao.save(examVo);
        savaPaperToMongoExamPaper(examVo);
    }

    public void update(ExamVo examVo, String[] markTeachers,String paperId ,String[] classroomIds) throws OperationException
    {
        ExamVo temp=isNull(examVo);
        infoValid(examVo,markTeachers,paperId,classroomIds);

        temp.setCloseTime(examVo.getCloseTime());
        temp.setOpenTime(examVo.getOpenTime());
        temp.setDuration(examVo.getDuration());
        temp.setClassroomIds(examVo.getClassroomIds());
        temp.setMarkTeachers(examVo.getMarkTeachers());
        temp.setSubjectVo(examVo.getSubjectVo());
        temp.setCreateTeacher(examVo.getCreateTeacher());

        updatePaperFromMongoExamPaper(examVo);
    }

    public List<ExamVo> getByCreateTeacher(String teacherId)
    {
        List<ExamVo> list=examDao.getByCreateTeacher(teacherId);
        operationValid(list,teacherId);
        return list;
    }

    public List<ExamVo> getByMarkTeacher(String teacherId)
    {
        List<ExamVo> list=examDao.getByMarkTeacher(teacherId);
        operationValid(list,teacherId);
        return list;
    }

    public void setPass(ExamVo examVo, TeacherVo login) throws Exception
    {
        roleValid(login);
        ExamVo temp=isNull(examVo);
        if(temp.getExamStatus()!=SysConsts.EXAM_STATUS_NO_CHECK){
            throw new OperationException("所选该场考试不是待审核状态，请不要进行非法操作！");
        }
        temp.setExamStatus(SysConsts.EXAM_STATUS_PASS);
        createSchedulerJob(temp,SysConsts.SCHEDULEVO_JOB_TYPE_OPEN);
        createSchedulerJob(temp,SysConsts.SCHEDULEVO_JOB_TYPE_CLOSE);
        createSchedulerJob(temp,SysConsts.SCHEDULEVO_JOB_TYPE_FINISH);
    }

    public void setNoPass(ExamVo examVo, TeacherVo login) throws Exception
    {
        roleValid(login);
        ExamVo temp=isNull(examVo);
        if(temp.getExamStatus()!=SysConsts.EXAM_STATUS_NO_CHECK){
            throw new OperationException("所选该场考试不是待审核状态，请不要进行非法操作！");
        }
        if(StringUtil.isEmpty(examVo.getRemark())){
            throw new OperationException("驳回考试请求时“备注”不能为空，请不要进行非法操作！");
        }
        temp.setRemark(examVo.getRemark());
        temp.setExamStatus(SysConsts.EXAM_STATUS_NO_PASS);
    }

    public void cancel(ExamVo examVo, TeacherVo login) throws Exception
    {
        ExamVo temp=isNull(examVo);
        Integer status=temp.getExamStatus();
        if(login.getRole().getId().equals(SysConsts.ADMIN_ROLE_ID)){
            if(status!=SysConsts.EXAM_STATUS_NO_CHECK
                    &&status!=SysConsts.EXAM_STATUS_PASS
                    &&status!=SysConsts.EXAM_STATUS_NO_PASS){
                throw new OperationException("所选该场考试不是可取消状态，请不要进行非法操作！");
            }
        }else if(status!=SysConsts.EXAM_STATUS_NO_CHECK&&status!=SysConsts.EXAM_STATUS_NO_PASS){
            throw new OperationException("所选该场考试不是可取消状态，请不要进行非法操作！");
        }
        temp.setExamStatus(SysConsts.EXAM_STATUS_IN_CANCEL);
        studentExamDao.deleteAllByExam(examVo);
        List<ScheduleVo> scheduleList=scheduleDao.getByTarget(temp.getId());
        for (ScheduleVo scheduleVo : scheduleList)
        {
            scheduleVo.setJobStatus(SysConsts.SCHEDULEVO_JOB_STATUS_FORBIDDEN);
            new SchedulerJobUtil().pauseJob(scheduleVo,schedulerFactoryBean.getScheduler());
            savaLog(scheduleVo,"禁用任务");
        }
    }

    public void deleteCancel(ExamVo examVo, TeacherVo login) throws Exception
    {
        roleValid(login);
        ExamVo temp=isNull(examVo);
        Integer status=temp.getExamStatus();
        if(status!=SysConsts.EXAM_STATUS_IN_CANCEL){
            throw new OperationException("所选该场考试不是可删除状态，请不要进行非法操作！");
        }
        List<ScheduleVo> scheduleList=scheduleDao.getByTarget(temp.getId());
        for (ScheduleVo scheduleVo : scheduleList)
        {
            scheduleVo.setJobStatus(SysConsts.SCHEDULEVO_JOB_STATUS_DELETE);
            new SchedulerJobUtil().deleteJob(scheduleVo,schedulerFactoryBean.getScheduler());
            savaLog(scheduleVo,"删除任务");
        }
        examDao.delete(temp);
        deletePaperFromMongoExamPaper(temp);
    }

    public void updateMarkTeacher(ExamVo examVo, String[] markTeachers) throws Exception
    {
        ExamVo temp=isNull(examVo);

        //TODO 测试：是否可以逻辑更新
        temp.setMarkTeachers(markTeacherValid(markTeachers));
    }

    public List<ExamVo> getMyExamList(String studentId)
    {
        List<StudentExamVo> studentExamVos=studentExamDao.getByStudent(studentId);
        List<ExamVo> list=new ArrayList<>();
        for (StudentExamVo studentExamVo : studentExamVos)
        {
            ExamVo examVo=studentExamVo.getExamVo();
            Set<String> set=new HashSet<>();
            if(examVo.getExamStatus()==SysConsts.EXAM_STATUS_OPEN){
                set.add(SysConsts.EXAM_OPERATION_ATTEND);
            }
            if(examVo.getExamStatus()==SysConsts.EXAM_STATUS_ENDING){
                set.add(SysConsts.EXAM_OPERATION_VIEW_SCORE);
            }
            examVo.setOperation(set);
            list.add(examVo);
        }
        return list;
    }

    public List<ExamVo> getAllByAdmin(String teacherId, ExamQueryModel examQueryModel,
                                      Integer pageNum, Integer pageSize)throws Exception
    {
        List<ExamVo> list=examDao.getAll(examQueryModel,pageNum,pageSize);
        operationValid(list,teacherId);
        return list;
    }

    public Map<String, Object> attendExam(ExamVo examVo, StudentVo studentVo) throws Exception
    {
        examVo=isNull(examVo);
        StudentExamVo studentExamVo=studentExamDao.getByStudentAndExam(examVo,studentVo);
        if(studentExamVo==null){
            throw new OperationException("你没有该场考试，请不要进行非法操作！");
        }
        examVo=studentExamVo.getExamVo();
        if(examVo.getExamStatus()!=SysConsts.EXAM_STATUS_OPEN){
            throw new OperationException("尚未开始考试，请不要进行非法操作！");
        }
        if(studentExamVo.getOperation()==SysConsts.STUDENT_EXAM_OPERATION_SUBMIT){
            throw new OperationException("你已经提交该场考试，请不要进行非法操作！");
        }
        List<StudentExamQuestionVo> list=new ArrayList<>();
        if(studentExamVo.getOperation()==SysConsts.STUDENT_EXAM_OPERATION_ARCHIVE){
            list=studentExamQuestionDao.getAllByStudentExam(studentExamVo);
        }else {
            studentExamVo.setOperation(SysConsts.STUDENT_EXAM_OPERATION_ARCHIVE);
            Long systemTime=System.currentTimeMillis();
            studentExamVo.setStartTime(systemTime);

            examVo=queryPaperFromMongoExamPaper(examVo);
            List<QuestionVo> questionVoList= examVo.getPaperVo().getQuestionVoList();

            for (int i=0;i<questionVoList.size();i++)
            {
                StudentExamQuestionVo seq=new StudentExamQuestionVo();
                seq.setId(IdUtil.getUUID());
                seq.setStudentExamVo(studentExamVo);
                seq.setIndext(i+1);
                seq.setRightAnswer(questionVoList.get(i).getAnswer());
                seq.setQuestionTypeVo(questionVoList.get(i).getQuestionType());
                studentExamQuestionDao.save(seq);
                list.add(seq);
            }
            //创建自动提交定时任务
            ScheduleVo scheduleVo=new ScheduleVo();
            scheduleVo.setId(IdUtil.getUUID());
            scheduleVo.setJobName("学生考试："+studentExamVo.getId()+":自动提交定时任务");
            scheduleVo.setJobGroup("default");
            scheduleVo.setJobStatus(SysConsts.SCHEDULEVO_JOB_STATUS_ONETIME);
            scheduleVo.setCronexpression(FormatUtil.cronExpression(systemTime+examVo.getDuration()*60*100));
            scheduleVo.setDescribe(scheduleVo.getJobName());
            scheduleVo.setDao(studentExamDao);
            scheduleVo.setTargetVoId(studentExamVo.getId());
            scheduleVo.setJobType(SysConsts.SCHEDULEVO_JOB_TYPE_ONETIME);
            SchedulerJobUtil.addJob(scheduleVo);
            savaLog(scheduleVo,scheduleVo.getDescribe());
        }
        Map<String,Object> map=new HashMap<>();
        map.put("studentExamQuestionList",list);
        map.put("studentExamVo",studentExamVo);
        return map;
    }

    public void archive(StudentVo login, String studentExamId, List<StudentExamQuestionVo> studentExamQuestionList)throws Exception
    {
        if(StringUtil.isEmpty(studentExamId)){
            throw new OperationException("你的考试id不能为空，请不要进行非法操作！");
        }
        StudentExamVo studentExamVo=studentExamDao.get(studentExamId);
        if(studentExamVo==null){
            throw new OperationException("你的这场考试不存在，请不要进行非法操作！");
        }
        if(!studentExamVo.getStudentVo().getId().equals(login.getId())){
            throw new OperationException("无法保存他人的试卷，请不要进行非法操作！");
        }
        ExamVo examVo=studentExamVo.getExamVo();
        if(examVo.getExamStatus()>=SysConsts.EXAM_STATUS_IN_MARK){
            throw new OperationException("当前考试的状态不允许保存，请不要进行非法操作！");
        }
        if(studentExamQuestionList!=null&&studentExamQuestionList.size()!=0){
            for (StudentExamQuestionVo studentExamQuestionVo : studentExamQuestionList)
            {
                StudentExamQuestionVo temp=studentExamQuestionDao.get(studentExamQuestionVo.getId());
                if(temp==null){
                    throw new OperationException("你所保存的题目有可能不存在，请不要进行非法操作！");
                }
                if(!temp.getStudentExamVo().getId().equals(studentExamVo.getId())){
                    throw new OperationException("你所保存的题目有可能不不属于这场考试，请不要进行非法操作！");
                }
                temp.setAnswer(studentExamQuestionVo.getAnswer());
            }
        }
    }

    public void submitAnswer(StudentExamVo studentExamVo, StudentVo login) throws Exception
    {
        if(StringUtil.isEmpty(studentExamVo.getId())){
            throw new OperationException("请确认要提交的试卷不为空,不要进行非法操作！");
        }
        studentExamVo=studentExamDao.get(studentExamVo.getId());
        if(studentExamVo==null){
            throw new OperationException("请确认要提交的试卷不为空,不要进行非法操作！");
        }
        if(!studentExamVo.getStudentVo().getId().equals(login.getId())){
            throw new OperationException("不能提交他人试卷,请不要进行非法操作！");
        }
        Set<StudentExamQuestionVo> questions=studentExamVo.getStudentExamQuestionVos();
        double sum=0;
        for (StudentExamQuestionVo question : questions)
        {
            double score=0;
            if(question.getRightAnswer().equals(question.getAnswer())){
                score=question.getQuestionTypeVo().getScore();
            }else if(question.getRightAnswer().contains(question.getAnswer())){
                score=question.getQuestionTypeVo().getScore()/2;
            }
            question.setScore(FormatUtil.formatScore(score));
        }
        studentExamVo.setScore(FormatUtil.formatScore(sum));
        studentExamVo.setStudentExamQuestionVos(questions);

        //TODO answerContent存入MongoDB
        ScheduleVo scheduleVo=scheduleDao.getByTarget(studentExamVo.getId()).get(0);
        SchedulerJobUtil.delJob(scheduleVo);
    }

    public StudentExamVo getStudentExam(ExamVo examVo, StudentVo login)throws Exception
    {
        if(StringUtil.isEmpty(examVo.getId())){
            throw new OperationException("选择的考试不能为空，请不要进行非法操作！");
        }
        examVo=isNull(examVo);
        if(examVo.getExamStatus()!=SysConsts.EXAM_STATUS_ENDING){
            throw new OperationException("所选择的考试当前尚未结束，不能查看成绩！");
        }
        StudentExamVo studentExamVo=studentExamDao.getByStudentAndExam(examVo,login);
        if(null==studentExamVo){
            throw new OperationException("你不存在这场考试，请不要进行非法操作！");
        }
        return studentExamVo;
    }

    public List<StudentExamVo> getAllStudentExamByExam(ExamVo examVo, TeacherVo login)throws Exception
    {
        Set<TeacherVo> markTeachers=examVo.getMarkTeachers();
        if(!markTeachers.contains(login)){
            throw new OperationException("您不是该场考试的阅卷教师，请不要进行非法操作！");
        }
        List<StudentExamVo> list=studentExamDao.getbyExam(examVo);
        for (StudentExamVo studentExamVo : list)
        {
            List<StudentExamQuestionVo> questionList=this.getAllStudentexamQuestionByStudentExam(studentExamVo);
            if(questionList.size()==0){
                list.remove(studentExamVo);
            }
        }
        return null;
    }

    public List<StudentExamQuestionVo> getAllStudentexamQuestionByStudentExam(StudentExamVo studentExamVo)throws Exception
    {
        if(StringUtil.isEmpty(studentExamVo.getId())){
            throw new OperationException("所选择的学生试卷不能为空，请不要进行非法操作！");
        }
        studentExamVo=studentExamDao.get(studentExamVo.getId());
        if(studentExamVo==null){
            throw new OperationException("所选择的学生试卷不存在，请不要进行非法操作！");
        }
        List<StudentExamQuestionVo> list=studentExamQuestionDao.getAllByStudentExam(studentExamVo);
        for(int i=list.size()-1;i>=0;i--){
            StudentExamQuestionVo temp=list.get(i);
            if(!temp.getQuestionTypeVo().getName().equals(SysConsts.NO_ANSWER_QUESTION_TYPE_NAME)){
                list.remove(temp);
            }
            if(null!=temp.getTeacherVo()){
                list.remove(temp);
            }
        }
        return list;
    }

    public void saveMarked(List<StudentExamQuestionVo> studentExamQeustionList, String studentExamId)throws Exception
    {
        if(studentExamQeustionList==null||studentExamQeustionList.size()>0){
            throw new OperationException("所保存的题目不能为空,请不要进行非法操作！");
        }
        if(StringUtil.isEmpty(studentExamId)){
            throw new OperationException("所要保存的学生试卷,请不要进行非法操作！");
        }
        StudentExamVo studentExamVo = studentExamDao.get(studentExamId);
        if(null==studentExamVo){
            throw new OperationException("索要保存的学生试卷不存在,请不要进行非法操作！");
        }
        double scores=0;
        for (StudentExamQuestionVo studentExamQuestionVo : studentExamQeustionList)
        {
            StudentExamQuestionVo temp=studentExamQuestionDao.get(studentExamQuestionVo.getId());
            if(temp==null){
                throw new OperationException("个别题目不存在,请不要进行非法操作！");
            }
            if(!studentExamVo.getId().equals(temp.getStudentExamVo().getId())){
                throw new OperationException("个别题目属于所提交的学生试卷,请不要进行非法操作！");
            }
            scores+=studentExamQuestionVo.getScore();
            temp.setScore(FormatUtil.formatScore(studentExamQuestionVo.getScore()));
            temp.setRemark(studentExamQuestionVo.getRemark());
        }
        studentExamVo.setScore(FormatUtil.formatScore(scores));
    }

    public void submitMarked(ExamVo examVo, TeacherVo login)throws Exception
    {
        if(StringUtil.isEmpty(examVo.getId())){

        }
        examVo=isNull(examVo);
        if(!examVo.getMarkTeachers().contains(login)){

        }
        List<StudentExamQuestionVo> questionList=studentExamQuestionDao.getByExam(examVo);
        for (StudentExamQuestionVo studentExamQuestionVo : questionList)
        {
            if(studentExamQuestionVo.getQuestionTypeVo().equals(SysConsts.NO_ANSWER_QUESTION_TYPE_NAME)){
                if(null==studentExamQuestionVo.getTeacherVo()){
                    throw new OperationException("所选考试下有题目尚未批阅，不能提交阅卷！");
                }
            }
        }
        examVo.setExamStatus(SysConsts.EXAM_STATUS_ENDING);
    }

    //-----------------------------------以上为业务操作-------------------------------------
    //-----------------------------------以上为业务操作-------------------------------------
    //-----------------------------------以上为业务操作-------------------------------------
    //-----------------------------------以上为业务操作-------------------------------------
    //-----------------------------------以上为业务操作-------------------------------------

    /**
     * 创建定时任务
     * @param temp
     * @throws Exception
     */
    private void createSchedulerJob(ExamVo temp,Integer jobType) throws Exception
    {
        ScheduleVo scheduleVo=new ScheduleVo();
        String jobTypeView=SysConsts.SCHEDULEVO_JOB_TYPE_MAP.get(jobType);

        //设置任务信息
        scheduleVo.setId(IdUtil.getUUID());
        scheduleVo.setJobName(temp.getName()+"_"+jobTypeView);
        scheduleVo.setJobGroup(temp.getId());
        scheduleVo.setDescribe("定时"+jobTypeView+",ExamId："+temp.getId());
        scheduleVo.setJobType(jobType);
        scheduleVo.setTargetVoId(temp.getId());

        if(jobType==SysConsts.SCHEDULEVO_JOB_TYPE_OPEN){
            scheduleVo.setNowStatu(SysConsts.EXAM_STATUS_PASS);
            scheduleVo.setAffterStatu(SysConsts.EXAM_STATUS_OPEN);
            scheduleVo.setCronexpression(FormatUtil.cronExpression(temp.getOpenTime()));
        }else if(jobType==SysConsts.SCHEDULEVO_JOB_TYPE_CLOSE){
            scheduleVo.setNowStatu(SysConsts.EXAM_STATUS_OPEN);
            scheduleVo.setAffterStatu(SysConsts.EXAM_STATUS_CLOSE);
            scheduleVo.setCronexpression(FormatUtil.cronExpression(temp.getOpenTime()+
                    SysConsts.EXAM_OPEN_TO_CLOSE_DURING_TIME*60*100));
        }else {
            List<QuestionVo> questions = temp.getPaperVo().getQuestionVoList();
            boolean flag=true;
            for(int i=0;i<questions.size();i++)
            {
                QuestionTypeVo questionTypeVo=questionTypeDao.get(questions.get(i).getQuestionType().getId());
                if(questionTypeVo.getName().equals(SysConsts.NO_ANSWER_QUESTION_TYPE_NAME)){
                    flag=false;
                }
            }
            scheduleVo.setNowStatu(SysConsts.EXAM_STATUS_CLOSE);
            if(flag){
                scheduleVo.setAffterStatu(SysConsts.EXAM_STATUS_ENDING);
            }else {
                scheduleVo.setAffterStatu(SysConsts.EXAM_STATUS_IN_MARK);
            }
            scheduleVo.setCronexpression(FormatUtil.cronExpression(temp.getOpenTime()+
                    (temp.getDuration()+SysConsts.EXAM_OPEN_TO_CLOSE_DURING_TIME)*60*100));
        }
        scheduleVo.setDao(examDao);
        scheduleDao.save(scheduleVo);
        new SchedulerJobUtil().createJob(scheduleVo,schedulerFactoryBean.getScheduler());
        savaLog(scheduleVo,scheduleVo.getDescribe());
    }

    /**
     * 解析得到题目的JSON数组
     * @param paperContent
     * @return
     */
    private JSONArray getQuestionJsonArray(String paperContent)
    {
        JSONObject jsonObject=JSONObject.fromObject(paperContent);
        String questionContainJson=jsonObject.getString("questionContain");
        return JSONArray.fromObject(questionContainJson);
    }

    /**
     * 记录日志
     * @param scheduleVo
     */
    private void savaLog(ScheduleVo scheduleVo,String method){

        LogVo logVo = new LogVo();
        logVo.setModule("考试管理");
        logVo.setMethod(method);
        logVo.setTime(System.currentTimeMillis());
        logVo.setArgument(scheduleVo.getClass().getName()+"-->"+scheduleVo.toString());
        logDao.save(logVo);
    }

    /**
     * 角色校验
     * @param teacherVo
     * @throws OperationException
     */
    private void roleValid(TeacherVo teacherVo) throws OperationException
    {
        if(teacherVo.getRole().getId()!=SysConsts.ADMIN_ROLE_ID){
            throw new OperationException("您不是管理员，请不要进行非法操作！");
        }
    }

    /**
     * 判空
     * @param examVo
     * @return
     * @throws OperationException
     */
    private ExamVo isNull(ExamVo examVo) throws OperationException
    {
        ExamVo temp=examDao.get(examVo.getId());
        if(temp==null){
            throw new OperationException("所选该场考试不存在，请不要进行非法操作！");
        }
        return temp;
    }

    /**
     * 操作校验
     * @param list
     * @param teacherId
     */
    private void operationValid(List<ExamVo> list, String teacherId)
    {
        TeacherVo login=teacherDao.get(teacherId);
        for (ExamVo examVo : list)
        {
            Set<String> set = new HashSet<>();
            set.add(SysConsts.EXAM_OPERATION_VIEW);
            examVo.setOperation(set);
            if(examVo.getExamStatus()==SysConsts.EXAM_STATUS_OPEN||examVo.getExamStatus()==SysConsts.EXAM_STATUS_CLOSE){
                continue;
            }
            if(examVo.getExamStatus()== SysConsts.EXAM_STATUS_NO_CHECK){
                if(login.getRole().getId().equals(SysConsts.ADMIN_ROLE_ID)){
                    set.add(SysConsts.EXAM_OPERATION_NO_CHECK);
                }
                set.add(SysConsts.EXAM_OPERATION_TO_EDIT);
                set.add(SysConsts.EXAM_OPERATION_CANCEL);
                set.add(SysConsts.EXAM_OPERATION_ADD_MARK_TEACHER);
                continue;
            }
            if(examVo.getExamStatus()==SysConsts.EXAM_STATUS_PASS){
                if(login.getRole().getId().equals(SysConsts.ADMIN_ROLE_ID)){
                    set.add(SysConsts.EXAM_OPERATION_CANCEL);
                }
                set.add(SysConsts.EXAM_OPERATION_ADD_MARK_TEACHER);
                continue;
            }
            if(examVo.getExamStatus()==SysConsts.EXAM_STATUS_NO_PASS){
                set.add(SysConsts.EXAM_OPERATION_TO_EDIT);
                set.add(SysConsts.EXAM_OPERATION_CANCEL);
                set.add(SysConsts.EXAM_OPERATION_ADD_MARK_TEACHER);
                continue;
            }
            if(examVo.getExamStatus()==SysConsts.EXAM_STATUS_IN_MARK){
                set.add(SysConsts.EXAM_OPERATION_ADD_MARK_TEACHER);
                if(examVo.getMarkTeachers().contains(login)){
                    set.add(SysConsts.EXAM_OPERATION_MARK);
                    set.add(SysConsts.EXAM_OPERATION_SUBMIT_MARK);
                }
                continue;
            }
            if(examVo.getExamStatus()==SysConsts.EXAM_STATUS_IN_CANCEL){
                if(login.getRole().getId().equals(SysConsts.ADMIN_ROLE_ID)){
                    set.add(SysConsts.EXAM_OPERATION_DELETE);
                }
                continue;
            }
            if(examVo.getExamStatus()==SysConsts.EXAM_STATUS_ENDING){
                set.add(SysConsts.EXAM_OPERATION_VIEW_SCORE);
                continue;
            }
        }
    }

    /**
     * 信息校验
     * @param examVo
     * @param markTeachers
     * @param paperId
     * @param classroomIds
     * @throws OperationException
     */
    private void infoValid(ExamVo examVo, String[] markTeachers,String paperId ,String[] classroomIds) throws OperationException
    {
        if(examVo.getSubjectVo()==null){
            throw new OperationException("科目不能为空,请不要进行非法操作！");
        }
        if (examVo.getSubjectVo().getId()==null||examVo.getSubjectVo().getId()==0){
            throw new OperationException("科目不能为空,请不要进行非法操作！");
        }
        if(classroomIds==null||classroomIds.length==0){
            throw new OperationException("参加考试的班级不能为空,请不要进行非法操作！");
        }
        if(StringUtil.isEmpty(paperId)){
            throw new OperationException("试卷不能为空,请不要进行非法操作！");
        }
        PaperVo paperVo=paperMongoDao.queryOne(new Query(Criteria.where("id").is(paperId)));
        SubjectVo temp= subjectDao.get(examVo.getSubjectVo().getId());
        if(paperVo==null){
            throw new OperationException("所选择的试卷不存在,请不要进行非法操作！");
        }
        if(temp==null){
            throw new OperationException("所选择的科目不存在,请不要进行非法操作！");
        }
        //删除该场考试的所有学生
        studentExamDao.deleteAllByExam(examVo);
        Set<TeacherVo> teacherSet=markTeacherValid(markTeachers);
        Map<String,ClassroomVo> map=new HashMap<>();
        int i=0;
        for (String classroomId : classroomIds)
        {
            ClassroomVo classroomVo=classroomDao.get(classroomId);
            if(classroomVo!=null){
                List<StudentVo> studentList=studentDao.getByClassroom(classroomId);
                for (StudentVo studentVo : studentList)
                {
                    StudentExamVo studentExamVo= new StudentExamVo();
                    studentExamVo.setId(IdUtil.getUUID());
                    studentExamVo.setExamVo(examVo);
                    studentExamVo.setStudentVo(studentVo);
                    studentExamDao.save(studentExamVo);
                }
                map.put(i+"",classroomVo);
            }
        }
        if(map.entrySet().size()==0){
            throw new OperationException("所选择的班级均不存在,请不要进行非法操作！");
        }
        JSONArray jsonObject=JSONArray.fromObject(map);
        examVo.setPaperVo(paperVo);
        examVo.setSubjectVo(temp);
        examVo.setMarkTeachers(teacherSet);
        examVo.setClassroomIds(jsonObject.toString());
    }

    /**
     * 校验教师ID
     * @param markTeachers
     * @return
     */
    private Set<TeacherVo> markTeacherValid(String[] markTeachers){
        Set<TeacherVo> teacherSet=new HashSet<>();
        if(markTeachers!=null&&markTeachers.length>0){
            for (String markTeacher : markTeachers)
            {
                TeacherVo teacherVo=teacherDao.get(markTeacher);
                if(teacherVo!=null){
                    teacherSet.add(teacherVo);
                }
            }
        }
        return teacherSet;
    }

    /**
     * 将考试所需试卷存入指定Collection
     * @param examVo
     */
    private void savaPaperToMongoExamPaper(ExamVo examVo){
        PaperVo paperVo=examVo.getPaperVo();
        paperVo.setExamId(examVo.getId());
        paperMongoDao.insert(paperVo,SysConsts.EXAM_PAPER_SAVA_MONGO_OF_COLLECTION);
    }

    /**
     * 从指定Collection取出指定的考试所需试卷
     * @param examVo
     * @return
     */
    private ExamVo queryPaperFromMongoExamPaper(ExamVo examVo){
        PaperVo paperVo=paperMongoDao.queryOne(new Query(Criteria.where("examId").is(examVo.getId())),SysConsts.EXAM_PAPER_SAVA_MONGO_OF_COLLECTION);
        examVo.setPaperVo(paperVo);
        return examVo;
    }

    /**
     * 从指定Collection删除指定的考试所需试卷
     * @param examVo
     * @return
     */
    private void deletePaperFromMongoExamPaper(ExamVo examVo){
        paperMongoDao.delete(new Query(Criteria.where("examId").is(examVo.getId())),SysConsts.EXAM_PAPER_SAVA_MONGO_OF_COLLECTION);
    }

    /**
     * 更新指定Collection中指定的考试所需试卷
     * @param examVo
     * @return
     */
    private void updatePaperFromMongoExamPaper(ExamVo examVo){
        paperMongoDao.save(examVo.getPaperVo(),SysConsts.EXAM_PAPER_SAVA_MONGO_OF_COLLECTION);
    }
}