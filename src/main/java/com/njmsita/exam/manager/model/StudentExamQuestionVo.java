package com.njmsita.exam.manager.model;

import com.njmsita.exam.authentic.model.TeacherVo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student_exam_question", schema = "jf_exam")
public class StudentExamQuestionVo
{
    private String id;
    private int index;
    private Double score;
    private String remark;
    private String answer;
    private String rightAnswer;

    //指定学生的考试 n TO 1
    private StudentExamVo studentExam;
    //阅卷教师   n  TO  1
    private TeacherVo teacherVo;
    //题型    n  TO  1
    private QuestionTypeVo questionTypeVo;

    @Basic
    @Column(name = "rightAnswer")
    public String getRightAnswer()
    {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer)
    {
        this.rightAnswer = rightAnswer;
    }

    @Basic
    @Column(name = "answer")
    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    @Basic
    @Column(name = "student_exam_id")
    public StudentExamVo getStudentExam()
    {
        return studentExam;
    }

    public void setStudentExam(StudentExamVo studentExamVo)
    {
        this.studentExam = studentExamVo;
    }

    @Basic
    @Column(name = "teacher_id")
    public TeacherVo getTeacherVo()
    {
        return teacherVo;
    }

    public void setTeacherVo(TeacherVo teacherVo)
    {
        this.teacherVo = teacherVo;
    }

    @Basic
    @Column(name = "questionType")
    public QuestionTypeVo getQuestionTypeVo()
    {
        return questionTypeVo;
    }

    public void setQuestionTypeVo(QuestionTypeVo questionTypeVo)
    {
        this.questionTypeVo = questionTypeVo;
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
    @Column(name = "indext")
    public int getIndex()
    {
        return index;
    }

    public void setIndex(int indext)
    {
        this.index = indext;
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
    @Column(name = "remark")
    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    @Override
    public int hashCode()
    {

        return Objects.hash(id, index,score, remark,answer,rightAnswer);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentExamQuestionVo that = (StudentExamQuestionVo) o;
        return index == that.index &&
                Objects.equals(id, that.id) &&
                Objects.equals(score, that.score) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(answer, that.answer) &&
                Objects.equals(score, that.score) &&
                Objects.equals(answer, that.rightAnswer) &&
                Objects.equals(remark, that.remark);
    }
}
