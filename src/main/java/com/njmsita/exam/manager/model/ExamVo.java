package com.njmsita.exam.manager.model;

import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.utils.consts.SysConsts;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 考试实体模型
 */
@Entity
@Table(name = "exam", schema = "jf_exam", catalog = "")
public class ExamVo
{
    //状态码与状态视图对应的map
    private static Map<Integer,String> examStatuMap = new HashMap<>();
    static {
        examStatuMap.put(SysConsts.EXAM_STATUS_NO_CHECK,SysConsts.EXAM_STATUS_NO_CHECK_VIEW);
        examStatuMap.put(SysConsts.EXAM_STATUS_PASS,SysConsts.EXAM_STATUS_PASS_VIEW);
        examStatuMap.put(SysConsts.EXAM_STATUS_NO_PASS,SysConsts.EXAM_STATUS_NO_PASS_VIEW);
        examStatuMap.put(SysConsts.EXAM_STATUS_OPEN,SysConsts.EXAM_STATUS_OPEN_VIEW);
        examStatuMap.put(SysConsts.EXAM_STATUS_CLOSE,SysConsts.EXAM_STATUS_CLOSE_VIEW);
        examStatuMap.put(SysConsts.EXAM_STATUS_IN_MARK,SysConsts.EXAM_STATUS_IN_MARK_VIEW);
        examStatuMap.put(SysConsts.EXAM_STATUS_IN_CANCEL,SysConsts.EXAM_STATUS_IN_CANCEL_VIEW);
        examStatuMap.put(SysConsts.EXAM_STATUS_ENDING,SysConsts.EXAM_STATUS_ENDING_VIEW);
    }

    private String id;
    private Long closeTime;
    private Long openTime;
    private Integer duration;
    private String paperContent;
    private String classroomIds;
    private String remark;

    //该考试对应状态下的操作
    private Set<String> operation;

    //发起考试的教师
    private TeacherVo createTeacher;
    //所属科目  n  TO  1
    private SubjectVo subjectVo;
    //阅卷教师  n  TO  m
    private Set<TeacherVo> markTeachers;

    private Integer examStatus;
    //考试状态视图值
    private String examStatusView;

    public Set<String> getOperation()
    {
        return operation;
    }

    public void setOperation(Set<String> operation)
    {
        this.operation = operation;
    }

    public String getExamStatusView()
    {
        return examStatusView;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    @Basic
    @Column(name = "create_teacher_id")
    public TeacherVo getCreateTeacher()
    {
        return createTeacher;
    }

    public void setCreateTeacher(TeacherVo createTeacher)
    {
        this.createTeacher = createTeacher;
    }

    public Set<TeacherVo> getMarkTeachers()
    {
        return markTeachers;
    }

    public void setMarkTeachers(Set<TeacherVo> markTeachers)
    {
        this.markTeachers = markTeachers;
    }

    @Basic
    @Column(name = "subject_id")
    public SubjectVo getSubjectVo()
    {
        return subjectVo;
    }

    public void setSubjectVo(SubjectVo subjectVo)
    {
        this.subjectVo = subjectVo;
    }

    @Id
    @Column(name = "id")
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Basic
    @Column(name = "close_time")
    public Long getCloseTime()
    {
        return closeTime;
    }

    public void setCloseTime(Long closeTime)
    {
        this.closeTime = closeTime;
    }

    @Basic
    @Column(name = "open_time")
    public Long getOpenTime()
    {
        return openTime;
    }

    public void setOpenTime(Long openTime)
    {
        this.openTime = openTime;
    }

    @Basic
    @Column(name = "duration")
    public Integer getDuration()
    {
        return duration;
    }

    public void setDuration(Integer duration)
    {
        this.duration = duration;
    }

    @Basic
    @Column(name = "exam_status")
    public Integer getExamStatus()
    {
        return examStatus;
    }

    public void setExamStatus(Integer examStatus)
    {
        this.examStatus = examStatus;
        if(examStatus!=null){
            this.examStatusView=examStatuMap.get(examStatus);
        }
    }

    @Basic
    @Column(name = "paper_content")
    public String getPaperContent()
    {
        return paperContent;
    }

    public void setPaperContent(String paperContent)
    {
        this.paperContent = paperContent;
    }

    @Basic
    @Column(name = "classroom_ids")
    public String getClassroomIds()
    {
        return classroomIds;
    }

    public void setClassroomIds(String classroomIds)
    {
        this.classroomIds = classroomIds;
    }



    @Override
    public int hashCode()
    {

        return Objects.hash(id, closeTime, openTime, duration, examStatuMap, paperContent, classroomIds);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamVo examVo = (ExamVo) o;
        return  Objects.equals(id, examVo.id) &&
                Objects.equals(closeTime, examVo.closeTime) &&
                Objects.equals(openTime, examVo.openTime) &&
                Objects.equals(duration, examVo.duration) &&
                Objects.equals(examStatuMap, examVo.examStatuMap) &&
                Objects.equals(paperContent, examVo.paperContent) &&
                Objects.equals(classroomIds, examVo.classroomIds);
    }
}
