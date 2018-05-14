package com.njmsita.exam.manager.model;

import com.njmsita.exam.authentic.model.StudentVo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student_exam", schema = "jf_exam", catalog = "")
public class StudentExamVo
{
    private String id;
    private Long startTime;
    private Long submitTime;
    private Double score;
    private String answerContent;
    private Integer operation;

    //对应考试 n TO 1
    private ExamVo examVo;

    //对应学生 n TO 1
    private StudentVo studentVo;

    @Basic
    @Column(name = "exam_id")
    public ExamVo getExamVo()
    {
        return examVo;
    }

    public void setExamVo(ExamVo examVo)
    {
        this.examVo = examVo;
    }

    @Basic
    @Column(name = "student_id")
    public StudentVo getStudentVo()
    {
        return studentVo;
    }

    public void setStudentVo(StudentVo studentVo)
    {
        this.studentVo = studentVo;
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
    @Column(name = "score")
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
    @Column(name = "operation")
    public Integer getOperation()
    {
        return operation;
    }

    public void setOperation(Integer operation)
    {
        this.operation = operation;
    }

    @Override
    public int hashCode()
    {

        return Objects.hash(id, startTime, submitTime, score, answerContent, operation);
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
                Objects.equals(operation, that.operation);
    }
}
