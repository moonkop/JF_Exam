package com.njmsita.exam.manager.model;

/**
 * 题目类型实体模型
 */
public class QuestionTypeModel
{
    private Integer id;
    private String name;
    private  Float score;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
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

    public Float getScore()
    {
        return score;
    }

    public void setScore(Float score)
    {
        this.score = score;
    }
}
