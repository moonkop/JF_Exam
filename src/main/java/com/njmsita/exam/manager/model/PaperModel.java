package com.njmsita.exam.manager.model;

import com.njmsita.exam.authentic.model.TeacherModel;

/**
 * 试卷实体模型
 */
public class PaperModel
{
    private String id;
    private String title;
    private String comment;

    //出卷教师 n TO 1
    private TeacherModel createTeacher;

    //TODO 没有配所属科目

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public TeacherModel getCreateTeacher()
    {
        return createTeacher;
    }

    public void setCreateTeacher(TeacherModel createTeacher)
    {
        this.createTeacher = createTeacher;
    }
}
