package com.njmsita.exam.manager.model;

import com.njmsita.exam.utils.validate.validategroup.AddGroup;
import com.njmsita.exam.utils.validate.validategroup.EditGroup;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "subject", schema = "jf_exam", catalog = "")
/**
 * 学科实体模型
 */
public class SubjectVo
{
    @NotNull(message = "{id.notempty}",groups = {EditGroup.class})
    private Integer id;
    @NotEmpty(message = "{name.notempty}",groups = {AddGroup.class,EditGroup.class})
    private String name;

    @Id
    @Column(name = "id")
    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
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
        int result = (int) id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubjectVo subjectVo = (SubjectVo) o;

        if (id != subjectVo.id) return false;
        if (name != null ? !name.equals(subjectVo.name) : subjectVo.name != null) return false;

        return true;
    }

    public String toString()
    {
        return "SubjectVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
