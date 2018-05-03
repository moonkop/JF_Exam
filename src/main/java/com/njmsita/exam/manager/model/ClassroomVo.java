package com.njmsita.exam.manager.model;

import com.njmsita.exam.utils.validate.validategroup.AddGroup;
import com.njmsita.exam.utils.validate.validategroup.EditGroup;
import com.njmsita.exam.utils.validate.validategroup.StudentAddGroup;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * 班级实体模型
 */
@Entity
@Table(name = "classroom", schema = "jf_exam", catalog = "")
public class ClassroomVo
{
    @NotEmpty(message = "{student.update.classroom.notempty}",groups = {EditGroup.class, StudentAddGroup.class})
    private String id;

    @NotEmpty(message = "{name.notempty}",groups = {AddGroup.class, EditGroup.class})
    private String name;
    //所属学校
    private SchoolVo schoolVo;


    @Basic
    @Column(name = "school_id")
    public SchoolVo getSchoolVo()
    {
        return schoolVo;
    }

    public void setSchoolVo(SchoolVo schoolVo)
    {
        this.schoolVo = schoolVo;
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

        ClassroomVo that = (ClassroomVo) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    public String toString()
    {
        return "ClassroomVo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", schoolVo=" + schoolVo +
                '}';
    }
}
