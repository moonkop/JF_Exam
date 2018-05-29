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

    private String id;
    //时间戳 考试开启时间
    private Long openTime;
    //考试入口开放时间 单位分钟
    private Integer openDuration;
    //时间戳 考试最终关闭时间（阅卷时间）
    private Long closeTime;
    //考试作答时间 单位分钟、
    private Integer duration;

    private String remark;
    private String name;
    //该考试对应状态下的操作
    private TeacherVo createTeacher;
    //发起考试的教师
    private SubjectVo subject;
    //所属科目  n  TO  1
    private Set<TeacherVo> markTeachers;
    private Set<ClassroomVo> classrooms;
    //阅卷教师  n  TO  m
    private Integer examStatus;
    //考试状态视图值
    @Deprecated
    private String examStatusView;
    //MongoDB中的ExamPaper
    @Transient
    private PaperVo paperVo;
    @Transient
    private Set<String> operation;

    public Set<ClassroomVo> getClassrooms()
    {
        return classrooms;
    }

    public void setClassrooms(Set<ClassroomVo> classrooms)
    {
        this.classrooms = classrooms;
    }

    public Integer getOpenDuration()
    {
        return openDuration;
    }

    public void setOpenDuration(Integer openDuration)
    {
        this.openDuration = openDuration;
    }

    public PaperVo getPaperVo()
    {
        return paperVo;
    }

    public void setPaperVo(PaperVo paperVo)
    {
        this.paperVo = paperVo;
    }

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
        return SysConsts.ExamStatusViewMap.get(this.getExamStatus());
    }


    @Basic
    @Column(name = "name")
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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
    public SubjectVo getSubject()
    {
        return subject;
    }

    public void setSubject(SubjectVo subjectVo)
    {
        this.subject = subjectVo;
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
        if (examStatus != null)
        {
            this.examStatusView = SysConsts.ExamStatusViewMap.get(examStatus);
        }
    }
}
