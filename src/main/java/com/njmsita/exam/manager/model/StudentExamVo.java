package com.njmsita.exam.manager.model;

import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.utils.consts.SysConsts;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "student_exam", schema = "jf_exam", catalog = "")
public class StudentExamVo
{
    private String id;
    private Long startTime;
    private Long submitTime;
    private Double score;
    private String answerContent;
    private Integer status;

    //对应考试 n TO 1
    private ExamVo exam;

    //对应学生 n TO 1
    private StudentVo student;

    private Set<StudentExamQuestionVo> studentExamQuestionVos;

    public Set<StudentExamQuestionVo> getStudentExamQuestionVos()
    {
        return studentExamQuestionVos;
    }

    public void setStudentExamQuestionVos(Set<StudentExamQuestionVo> studentExamQuestionVos)
    {
        this.studentExamQuestionVos = studentExamQuestionVos;
    }

    @Transient
    public Set<StudentExamQuestionVo> getStudentExamQuestion_Manual_mark()
    {
        Set<StudentExamQuestionVo> workoutList = new HashSet<>();
        for(StudentExamQuestionVo workout:getStudentExamQuestionVos())
        {
            if (SysConsts.MANUAL_MARK_QUESTION_TYPE_SET.contains(workout.getType()))
            {
                workoutList.add(workout);

            }
        }
        return workoutList;
    }

    @Basic
    @Column(name = "exam_id")
    public ExamVo getExam()
    {
        return exam;
    }

    public void setExam(ExamVo examVo)
    {
        this.exam = examVo;
    }

    @Basic
    @Column(name = "student_id")
    public StudentVo getStudent()
    {
        return student;
    }

    public void setStudent(StudentVo studentVo)
    {
        this.student = studentVo;
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
    @Column(name = "start_time")
    public Long getStartTime()
    {
        return startTime;
    }

    public void setStartTime(Long startTime)
    {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "submit_time")
    public Long getSubmitTime()
    {
        return submitTime;
    }

    public void setSubmitTime(Long submitTime)
    {
        this.submitTime = submitTime;
    }

    @Basic
    @Column(name = "totalScore")
    public Double getScore()
    {
        return score;
    }

    public void setScore(Double score)
    {
        this.score = score;
    }

    @Basic
    @Column(name = "answer_content")
    public String getAnswerContent()
    {
        return answerContent;
    }

    public void setAnswerContent(String answerContent)
    {
        this.answerContent = answerContent;
    }

    @Basic
    @Column(name = "state")
    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer operation)
    {
        this.status = operation;
    }

    @Override
    public int hashCode()
    {

        return Objects.hash(id, startTime, submitTime, score, answerContent, status);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentExamVo that = (StudentExamVo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(submitTime, that.submitTime) &&
                Objects.equals(score, that.score) &&
                Objects.equals(answerContent, that.answerContent) &&
                Objects.equals(status, that.status);
    }
}
