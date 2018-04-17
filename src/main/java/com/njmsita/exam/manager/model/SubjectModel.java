package com.njmsita.exam.manager.model;

import java.util.Set;

/**
 * 科目实体模型
 */
public class SubjectModel
{
    private Integer id;
    private String name;

    //该科目下所有知识点  1 TO n
    private Set<TopicModel> topics;

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

    public Set<TopicModel> getTopics()
    {
        return topics;
    }

    public void setTopics(Set<TopicModel> topics)
    {
        this.topics = topics;
    }
}
