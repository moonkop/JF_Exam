package com.njmsita.exam.manager.model;

import javax.persistence.*;

@Entity
@Table(name = "subject", schema = "jf_exam", catalog = "")
public class SubjectVo
{
    private byte id;
    private String name;

    @Id
    @Column(name = "id")
    public byte getId()
    {
        return id;
    }

    public void setId(byte id)
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
