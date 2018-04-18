package com.njmsita.exam.manager.model;

import java.util.Set;

/**
 * 知识点实体模型
 */
public class TopicModel
{
    private String id;
    private String name;

    //所拥有的子知识点  1 TO n
    private Set<TopicModel> childs;
    //所属父知识点  n TO 1
    private TopicModel parent;
    //所属科目     n TO 1
    private SubjectModel subject;

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

    public Set<TopicModel> getChilds()
    {
        return childs;
    }

    public void setChilds(Set<TopicModel> childs)
    {
        this.childs = childs;
    }

    public TopicModel getParent()
    {
        return parent;
    }

    public void setParent(TopicModel parent)
    {
        this.parent = parent;
    }

    public SubjectModel getSubject()
    {
        return subject;
    }

    public void setSubject(SubjectModel subject)
    {
        this.subject = subject;
    }
}
