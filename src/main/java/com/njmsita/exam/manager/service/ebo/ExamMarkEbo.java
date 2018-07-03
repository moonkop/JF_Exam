package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.authentic.dao.dao.StudentDao;
import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.manager.dao.dao.*;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.QuestionVo;
import com.njmsita.exam.manager.model.StudentExamQuestionVo;
import com.njmsita.exam.manager.model.StudentExamVo;
import com.njmsita.exam.manager.model.querymodel.*;
import com.njmsita.exam.manager.model.querymodel.report.ExamReport;
import com.njmsita.exam.manager.model.querymodel.report.OptionReport;
import com.njmsita.exam.manager.model.querymodel.report.QuestionReport;
import com.njmsita.exam.manager.model.querymodel.report.WorkoutReport;
import com.njmsita.exam.manager.service.ebi.ExamManageEbi;
import com.njmsita.exam.manager.service.ebi.ExamMarkEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.ItemNotFoundException;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.format.StringUtil;
import com.njmsita.exam.utils.json.JsonListObjectMapper;
import com.njmsita.exam.utils.json.JsonObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ExamMarkEbo implements ExamMarkEbi
{

    @Autowired
    private ExamDao examDao;
    @Autowired
    private StudentExamDao studentExamDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StudentExamQuestionDao studentExamQuestionDao;
    @Autowired
    private QuestionTypeDao questionTypeDao;
    @Autowired
    private ExamManageEbi examManageEbi;
    @Autowired
    private ExamReportDao examReportDao;
    @Autowired
    private StudentExamArchiveDao studentExamArchiveDao;


    public void saveMarked(List<StudentExamQuestionVo> studentExamQuestionList, TeacherVo loginTeacher) throws Exception
    {
        if (studentExamQuestionList == null || studentExamQuestionList.size() < 0)
        {
            return;
        }

        StudentExamQuestionVo first = studentExamQuestionDao.get(studentExamQuestionList.get(0).getId());
        StudentExamVo studentExamPo = first.getStudentExam();
        if (studentExamPo == null)
        {
            throw new OperationException("未找到该试卷");
        }
        ExamVo examPo = studentExamPo.getExam();
        if (examPo == null)
        {
            throw new OperationException("未找到该考试");
        }
        examManageEbi.checkPermission(SysConsts.EXAM_OPERATION_MARK, loginTeacher, examPo);

        for (StudentExamQuestionVo studentExamQuestionVo : studentExamQuestionList)
        {
            StudentExamQuestionVo studentExamQuestionPo = studentExamQuestionDao.get(studentExamQuestionVo.getId());
            if (studentExamQuestionPo == null)
            {
                throw new OperationException("个别题目未找到");
            }
            if (!studentExamPo.getId().equals(studentExamQuestionPo.getStudentExam().getId()))
            {
                if (studentExamQuestionPo.getStudentExam().getExam().getId() == examPo.getId())
                {
                    studentExamPo = studentExamQuestionPo.getStudentExam();
                } else
                {
                    throw new OperationException("个别题目不属于该场考试");
                }
            }

            if (studentExamQuestionPo.getScore() == studentExamQuestionVo.getScore()
                    && studentExamQuestionPo.getRemark() == studentExamQuestionVo.getRemark())
            {
                continue;
            }
            if (studentExamQuestionVo.getScore() == null && StringUtil.isEmpty(studentExamQuestionVo.getRemark()))
            {
                continue;
            }


            // studentExamQuestionPo.setScore(FormatUtil.formatScore(studentExamQuestionVo.getScore()));
            studentExamQuestionPo.setScore(studentExamQuestionVo.getScore());
            studentExamQuestionPo.setRemark(studentExamQuestionVo.getRemark());
            studentExamQuestionPo.setTeacherVo(loginTeacher);

        }

    }

    public void finishMark(String examId, TeacherVo loginTeacher) throws Exception
    {
        if (StringUtil.isEmpty(examId))
        {

        }
        ExamVo examPo = examManageEbi.getExamNotNull(examId);
        examManageEbi.checkPermission(SysConsts.EXAM_OPERATION_SUBMIT_MARK, loginTeacher, examPo);
        List<StudentExamQuestionVo> questionList = studentExamQuestionDao.getByExam(examPo);

        for (StudentExamQuestionVo studentExamQuestionVo : questionList)
        {
            if (!studentExamQuestionVo.IsMarked())
            {
                throw new OperationException("所选考试下有题目尚未批阅，不能提交阅卷！");
            }
        }
        List<StudentExamVo> papers = studentExamDao.getAllStudentExambyExamId(examId);
        for (StudentExamVo paper : papers)
        {
            paper.setScore(getStudentExamScore(paper));
        }
        ExamReport report = buildExamReport(examId);

        examReportDao.insert(report);
        //todo delete student exam question

        examPo.setExamStatus(SysConsts.EXAM_STATUS_ENDING);
    }

    @Override
    public ExamReport buildExamReport(String examId)
    {
        ExamVo examPo = examManageEbi.getWithPaper(examId);
        ExamReport report = new ExamReport();
        report.setId(examId);
        List<StudentExamQuestionVo> studentExamQuestionVoList = studentExamQuestionDao.getByExam(examPo);
        List<StudentExamVo> studentExamList = studentExamDao.getAllStudentExambyExamId(examId);

        Iterator<StudentExamVo> iterator = studentExamList.iterator();

        double examScoreMax = 0.0;
        double examScoreMin = Double.MAX_VALUE;
        double examScoreSum = 0.0;
        report.setStudentCount(studentExamList.size());

        while (iterator.hasNext())
        {
            StudentExamVo paper = iterator.next();
            if (paper.IsEmpty())
            {
                iterator.remove();
                continue;
            }
            paper.buildWorkoutMap();
            if (examScoreMax < paper.getScore())
            {
                examScoreMax = paper.getScore();
            }
            if (examScoreMin > paper.getScore())
            {
                examScoreMin = paper.getScore();
            }
            examScoreSum += paper.getScore();
        }
        Integer attendCount = studentExamList.size();

        report.setAttendCount(attendCount);
        report.setScoreAvg(examScoreSum / attendCount);
        report.setScoreMax(examScoreMax);
        report.setScoreMin(examScoreMin);
        studentExamList.sort((o1, o2) ->
                {
                    if (o1 == null || o2 == null)
                    {
                        return (o1 == null && o2 == null) ? 0 : (o1 == null && o2 != null ? -1 : 1);
                    } else if (o1.getScore() == null || o2.getScore() == null)
                    {
                        return (o1.getScore() == null && o2.getScore() == null) ? 0 : (o1.getScore() == null && o2.getScore() != null ? -1 : 1);
                    } else
                    {
                        return o1.getScore() > o2.getScore() ? 1 : (o1.getScore().equals(o2.getScore()) ? 0 : -1);
                    }
                }
        );
        for (QuestionVo question : examPo.getPaperVo().getQuestionList())
        {
            QuestionReport questionReport = new QuestionReport();
            report.questionReportList.add(questionReport);
            questionReport.setType(question.getType());
            double scoreSum = 0;
            double scoreMax = 0;
            double scoreMin = question.getValue();
            for (StudentExamVo paper : studentExamList)
            {
                StudentExamQuestionVo workout = paper.getWorkoutMap().get(question.getIndex());
                if (workout == null)
                {
                    //continue;
                }
                scoreSum += workout.getScore();
                if (scoreMin > workout.getScore())
                {
                    scoreMin = workout.getScore();
                }
                if (scoreMax < workout.getScore())
                {
                    scoreMax = workout.getScore();
                }
            }

            questionReport.setOutline(question.getOutline());
            questionReport.setScoreAvg(scoreSum / attendCount);
            questionReport.setScoreMax(scoreMax);
            questionReport.setScoreMin(scoreMin);

            switch (question.getType())
            {
                case SysConsts.QUESTION_TYPE_SINGLE_SELECTION:
                case SysConsts.QUESTION_TYPE_MUTI_SELECTION:
                {
                    questionReport.optionList = new ArrayList<>();
                    for (String optionText : question.getOptions())
                    {
                        OptionReport option = new OptionReport();
                        option.students = new ArrayList<>();
                        option.text = optionText;
                        questionReport.optionList.add(option);
                    }
                    for (StudentExamVo paper : studentExamList)
                    {
                        StudentExamQuestionVo workout = paper.getWorkoutMap().get(question.getIndex());
                        List<String> selectedOptionList = workout.getSelectedOptionList();
                        for (String optionIndex : selectedOptionList)
                        {
                            OptionReport option = questionReport.optionList.get(Integer.valueOf(optionIndex));
                            option.students.add(paper.getStudent().getStudentBrief());
                        }
                    }
                    for (OptionReport optionReport : questionReport.optionList)
                    {
                        optionReport.setPickNum(optionReport.students.size());
                        optionReport.setPickRate(optionReport.pickNum * 1.0 / attendCount);
                    }
                }
                break;
                case SysConsts.QUESTION_TYPE_SHORT_ANSWER:
                {
                    questionReport.workoutList = new ArrayList<>();
                    for (StudentExamVo paper : studentExamList)
                    {
                        StudentExamQuestionVo workout = paper.getWorkoutMap().get(question.getIndex());
                        WorkoutReport workoutReport = new WorkoutReport();
                        workoutReport.setMarkTeacher(workout.getTeacherVo().getBrief());
                        workoutReport.setRemark(workout.getRemark());
                        workoutReport.setScore(workout.getScore());
                        workoutReport.setStudent(paper.getStudent().getStudentBrief());
                        workoutReport.setWorkout(workout.getWorkout());
                        questionReport.workoutList.add(workoutReport);
                    }
                }
                break;
            }

        }
        return report;
    }

    public List<StudentExamQuestionVo> GetManualMarkWorkOutFormStudentExam(String studentExamId) throws Exception
    {
        StudentExamVo studentExamPo = studentExamDao.get(studentExamId);
        if (studentExamPo == null)
        {
            throw new ItemNotFoundException("未找到该学生的试卷");
        }
        if (studentExamPo.getStudentExamQuestionVos() == null || studentExamPo.getStudentExamQuestionVos().size() == 0)
        {
            throw new ItemNotFoundException("该试卷没有作答");
        }
        List<StudentExamQuestionVo> list = new ArrayList<>();
        for (StudentExamQuestionVo workout : studentExamPo.getStudentExamQuestionVos())
        {
            if (SysConsts.MANUAL_MARK_QUESTION_TYPE_SET.contains(workout.getType()))
            {
                list.add(workout);
            }
        }
        return list;
    }

    @Override
    public Map<String, Object> getStudentWorkout(String studentExamId) throws OperationException
    {
        if (StringUtil.isEmpty(studentExamId))
        {
            throw new OperationException("所选择的学生试卷不能为空");
        }
        StudentExamVo studentExamPo = studentExamDao.get(studentExamId);
        if (studentExamPo == null)
        {
            throw new OperationException("所选择的学生试卷不存在");
        }
        Set<StudentExamQuestionVo> set = studentExamPo.getStudentExamQuestionVos();


        List<StudentExamQuestionVo> workoutNeedMarkList = new ArrayList<>();

        for (StudentExamQuestionVo studentExamQuestion : set)
        {
            if (SysConsts.MANUAL_MARK_QUESTION_TYPE_SET.contains(studentExamQuestion.getType()))
            {
                workoutNeedMarkList.add(studentExamQuestion);
            }
        }

        Map<String, Object> retMap = new HashMap<>();

        retMap.put("workoutList",
                new JsonListObjectMapper<StudentExamQuestionVo>()
                        .setFields("id,index,score,remark,workout,answer,[teacher]getTeacherVo().getName(),[teacherId]getTeacherVo().getId(),type")
                        .serializeList(workoutNeedMarkList));
        retMap.put("student",
                new JsonObjectMapper<StudentVo>()
                        .setFields("[id]getStudentId(),[name]getName(),[classroom]getClassroom().getName(),[school]getSchool().getName()")
                        .serializeObject(studentExamPo.getStudent())
        );
        return retMap;
    }

    @Deprecated
    @Override
    public Map<String, Object> getMarkProgress(String ExamId) throws ItemNotFoundException
    {

        Map<String, Object> retMap = new HashMap<>();
        List<StudentExamVo> studentExamVos = studentExamDao.getAllStudentExambyExamId(ExamId);
        if (studentExamVos == null || studentExamVos.size() == 0)
        {
            throw new ItemNotFoundException("该场考试没有学生参加");
        }

        for (StudentExamVo StudentExamPo : studentExamVos)
        {
            String status = getMarkStatus(StudentExamPo);
            retMap.put(StudentExamPo.getId(), status);

        }
        return retMap;
    }

    @Override
    public List<Map<String, Object>> getStudentExamList(String examId)
    {
        List<Map<String, Object>> retMap = new ArrayList<>();

        List<StudentExamVo> list = studentExamDao.getAllStudentExambyExamId(examId);
        int index = 0;
        for (StudentExamVo paper : list)
        {
            Map<String, Object> row = new HashMap<>();
            row.put("index", index++);
            row.put("id", paper.getId());
            row.put("student_name", paper.getStudent().getName());
            row.put("student_id", paper.getStudent().getId());
            row.put("exam_status", paper.getStatus());
            row.put("mark_status", getMarkStatus(paper));

            retMap.add(row);
        }
        return retMap;

    }

    @Override
    public ExamReport getExamReport(String examId)
    {
        return examReportDao.get(examId);
    }

    @Override
    public StudentExamArchive buildAndSaveStudentExamArchive(String studentExamId)
    {
        StudentExamVo studentExamPo = studentExamDao.get(studentExamId);
        StudentExamArchive archive = new StudentExamArchive(studentExamPo);
        archive.setId(studentExamId);
        studentExamArchiveDao.insert(archive);
        return archive;

    }


    public Double getStudentExamScore(StudentExamVo studentExamVo)
    {
        Double scoreSum = 0.0;
        for (StudentExamQuestionVo workout : studentExamVo.getStudentExamQuestionVos())
        {
            scoreSum += workout.getScore();
        }
        return scoreSum;
    }

    private String getMarkStatus(StudentExamVo StudentExamPo)
    {
        String status = SysConsts.STUDENT_EXAM_MARK_PROGRESS_NOTSTARTED;
        if (StudentExamPo.getStatus() == SysConsts.STUDENT_EXAM_STATUS_NOT_STARTED || StudentExamPo.getStudentExamQuestionVos() == null || StudentExamPo.getStudentExamQuestionVos().size() == 0)
        {
            status = SysConsts.STUDENT_EXAM_MARK_PROGRESS_NOTFOUND;
        }
        boolean finished = true;
        Set<StudentExamQuestionVo> workouts = StudentExamPo.getStudentExamQuestion_Manual_mark();
        for (StudentExamQuestionVo workout : workouts)
        {
            if (workout.getScore() != null)
            {
                status = SysConsts.STUDENT_EXAM_MARK_PROGRESS_UNFINISHED;
            } else
            {
                finished = false;
            }
            if (finished)
            {
                status = SysConsts.STUDENT_EXAM_MARK_PROGRESS_COMPELETED;
            }
        }
        return status;
    }
}
