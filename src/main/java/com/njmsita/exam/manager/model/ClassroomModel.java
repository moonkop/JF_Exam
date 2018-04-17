package com.njmsita.exam.manager.model;


/**
 * 班级实体模型
 */
public class ClassroomModel
{
    private String id;
    private String name;

    //所属学校
    private SchoolModel sm;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public SchoolModel getSm()
    {
        return sm;
    }

    public void setSm(SchoolModel sm)
    {
        this.sm = sm;
    }
}
