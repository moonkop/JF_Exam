package com.njmsita.exam.authentic.model;

import com.njmsita.exam.utils.validate.validategroup.AddGroup;
import com.njmsita.exam.utils.validate.validategroup.EditGroup;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * 资源类型实体模型
 */
@Entity
@Table(name = "tresourcetype", schema = "jf_exam", catalog = "")
public class TresourcetypeVo
{
    @NotEmpty(message = "{id.notempty}",groups = {EditGroup.class})
    private String id;

    @NotEmpty(message = "{name.notempty}",groups = {AddGroup.class, EditGroup.class})
    private String name;

    @Id
    @Column(name = "ID")
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TresourcetypeVo that = (TresourcetypeVo) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    public String toString()
    {
        return "TresourcetypeVo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
