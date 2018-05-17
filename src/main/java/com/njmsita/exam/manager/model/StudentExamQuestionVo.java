package com.njmsita.exam.manager.model;

import com.njmsita.exam.authentic.model.TeacherVo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student_exam_question", schema = "jf_exam", catalog = "")
public class StudentExamQuestionVo
{
    private String id;
    private int indext;
    private Double score;
    private String remark;
    private String answer;
    private String rightAnswer;

    //指定学生的考试 n TO 1
    private StudentExamVo studentExamVo;
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
    @Column(name = "studentexam_id")
    public StudentExamVo getStudentExamVo()
    {
        return studentExamVo;
    }

    public void setStudentExamVo(StudentExamVo studentExamVo)
    {
        this.studentExamVo = studentExamVo;
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
    public int getIndext()
    {
        return indext;
    }

    public void setIndext(int indext)
    {
        this.indext = indext;
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

        return Objects.hash(id, indext,score, remark);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentExamQuestionVo that = (StudentExamQuestionVo) o;
        return indext == that.indext &&
                Objects.equals(id, that.id) &&
                Objects.equals(score, that.score) &&
                Objects.equals(remark, that.remark);
    }
}
