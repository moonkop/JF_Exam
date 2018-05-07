package com.njmsita.exam.manager.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.njmsita.exam.utils.validate.validategroup.AddGroup;
import com.njmsita.exam.utils.validate.validategroup.EditGroup;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "topic", schema = "jf_exam", catalog = "")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,property = "id")
/**
 * 知识点实体模型
 */
public class TopicVo
{
    @NotEmpty(message = "{id.notempty}",groups = {EditGroup.class})
    private String id;
    @NotEmpty(message = "{name.notempty}",groups = {AddGroup.class, EditGroup.class})
    private String name;

    //所属父知识点 n TO 1
    private TopicVo parent;
    //子知识点  1 TO n
    private Set<TopicVo> child;
    //所属科目  n TO 1
    private SubjectVo subjectVo;

    @Basic
    @Column(name = "parent_topic_id")
    public TopicVo getParent()
    {
        return parent;
    }

    public void setParent(TopicVo parent)
    {
        this.parent = parent;
    }

    public Set<TopicVo> getChild()
    {
        return child;
    }

    public void setChild(Set<TopicVo> child)
    {
        this.child = child;
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
    @Column(name = "name")
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }


    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TopicVo topicVo = (TopicVo) o;

        if (id != null ? !id.equals(topicVo.id) : topicVo.id != null) return false;
        if (name != null ? !name.equals(topicVo.name) : topicVo.name != null) return false;
        return true;
    }

    public String toString()
    {
        return "TopicVo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", parent=" + parent +
                ", child=" + child +
                ", subjectVo=" + subjectVo +
                '}';
    }
}
