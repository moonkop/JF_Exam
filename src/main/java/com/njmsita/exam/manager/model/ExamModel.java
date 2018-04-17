package com.njmsita.exam.manager.model;

import com.njmsita.exam.authentic.model.TeacherModel;
import com.njmsita.exam.utils.format.FormatUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 考试实体模型
 */
public class ExamModel
{
    public static final Integer EXAM_STATUS_NO_START =1;
    public static final Integer EXAM_STATUS_IS_DURING =2;
    public static final Integer EXAM_STATUS_ON_MARK =3;
    public static final Integer EXAM_STATUS_IS_FINISH=4;
    public static final String EXAM_STATUS_NO_START_VIEW ="未开始";
    public static final String EXAM_STATUS_IS_DURING_VIEW ="进行中";
    public static final String EXAM_STATUS_ON_MARK_VIEW ="批改中";
    public static final String EXAM_STATUS_IS_FINISH_VIEW ="已结束";

    private Map<Integer,String> statusMap = new HashMap<Integer, String>();

    {
        statusMap.put(EXAM_STATUS_NO_START,EXAM_STATUS_NO_START_VIEW);
        statusMap.put(EXAM_STATUS_IS_DURING,EXAM_STATUS_IS_DURING_VIEW);
        statusMap.put(EXAM_STATUS_ON_MARK,EXAM_STATUS_ON_MARK_VIEW);
        statusMap.put(EXAM_STATUS_IS_FINISH,EXAM_STATUS_IS_FINISH_VIEW);
    }

    private String id;
    private Integer duration;
    //所含题目
    private String paper_content;
    //参加班级
    private String classroomid;

    private Long open_time;
    private Long close_time;
    private Integer exam_status;
    //视图值
    private String open_timeView;
    private String close_timeView;
    private String exam_statusView;

    //所属科目    n TO 1
    private SubjectModel subject;
    //阅卷教师    n TO 1
    private TeacherModel markTeacher;
    //发起教师    n TO 1
    private TeacherModel createTeacher;
    //所用试卷    n TO 1
    private PaperModel paper;

    public Long getOpen_time()
    {
        return open_time;
    }

    public void setOpen_time(Long open_time)
    {
        this.open_time = open_time;
        this.open_timeView= FormatUtil.formatDateTime(open_time);
    }

    public Long getClose_time()
    {
        return close_time;
    }

    public void setClose_time(Long close_time)
    {
        this.close_time = close_time;
        this.close_timeView=FormatUtil.formatDateTime(close_time)
    }

    public Integer getExam_status()
    {
        return exam_status;
    }

    public void setExam_status(Integer exam_status)
    {
        this.exam_status = exam_status;
        this.exam_statusView=statusMap.get(exam_status);
    }

    public String getOpen_timeView()
    {
        return open_timeView;
    }

    public String getClose_timeView()
    {
        return close_timeView;
    }

    public String getExam_statusView()
    {
        return exam_statusView;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Integer getDuration()
    {
        return duration;
    }

    public void setDuration(Integer duration)
    {
        this.duration = duration;
    }

    public String getPaper_content()
    {
        return paper_content;
    }

    public void setPaper_content(String paper_content)
    {
        this.paper_content = paper_content;
    }

    public String getClassroomid()
    {
        return classroomid;
    }

    public void setClassroomid(String classroomid)
    {
        this.classroomid = classroomid;
    }

    public SubjectModel getSubject()
    {
        return subject;
    }

    public void setSubject(SubjectModel subject)
    {
        this.subject = subject;
    }

    public TeacherModel getMarkTeacher()
    {
        return markTeacher;
    }

    public void setMarkTeacher(TeacherModel markTeacher)
    {
        this.markTeacher = markTeacher;
    }

    public TeacherModel getCreateTeacher()
    {
        return createTeacher;
    }

    public void setCreateTeacher(TeacherModel createTeacher)
    {
        this.createTeacher = createTeacher;
    }

    public PaperModel getPaper()
    {
        return paper;
    }

    public void setPaper(PaperModel paper)
    {
        this.paper = paper;
    }
}
