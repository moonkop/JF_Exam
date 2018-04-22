package com.njmsita.exam.manager.model;

import com.njmsita.exam.base.BaseQueryVO;

import javax.persistence.*;

/**
 * 学校实体模型
 */
@Entity
@Table(name = "school", schema = "jf_exam", catalog = "")
public class SchoolVo extends BaseQueryVO
{
    private String id;
    private String name;

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

        SchoolVo schoolVo = (SchoolVo) o;

        if (id != null ? !id.equals(schoolVo.id) : schoolVo.id != null) return false;
        if (name != null ? !name.equals(schoolVo.name) : schoolVo.name != null) return false;

        return true;
    }
}
