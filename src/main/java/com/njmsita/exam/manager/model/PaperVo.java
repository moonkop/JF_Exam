package com.njmsita.exam.manager.model;

import com.njmsita.exam.authentic.model.TeacherVo;

import javax.persistence.*;
import java.util.Objects;

/**
 * 试卷实体模型
 */
@Entity
@Table(name = "paper", schema = "jf_exam", catalog = "")
public class PaperVo
{
    private String id;
    private String title;
    private String comment;

    //出卷教师  n  TO  1
    private TeacherVo teacher;

    //所属科目  n  TO  1
    private SubjectVo subjectVo;

    @Basic
    @Column(name = "create_teacher_id")
    public TeacherVo getTeacher()
    {
        return teacher;
    }

    public void setTeacher(TeacherVo teacher)
    {
        this.teacher = teacher;
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
    @Column(name = "title")
    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @Basic
    @Column(name = "comment")
    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }


    @Override
    public int hashCode()
    {

        return Objects.hash(id, title, comment);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperVo paperVo = (PaperVo) o;
        return  Objects.equals(id, paperVo.id) &&
                Objects.equals(title, paperVo.title) &&
                Objects.equals(comment, paperVo.comment);
    }
}
