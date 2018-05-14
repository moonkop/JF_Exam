package com.njmsita.exam.manager.model;

import com.njmsita.exam.utils.validate.validategroup.AddGroup;
import com.njmsita.exam.utils.validate.validategroup.EditGroup;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "question_type", schema = "jf_exam", catalog = "")
/**
 * 题型实体模型
 */
public class QuestionTypeVo
{

    @NotNull(message = "{id.notempty}",groups = {EditGroup.class})
    private Integer id;
    @NotEmpty(message = "{name.notempty}",groups = {AddGroup.class, EditGroup.class})
    private String name;
    @NotNull(message = "{score.notempty}",groups = {AddGroup.class, EditGroup.class})
    private Double score;

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

    @Basic
    @Column(name = "score")
    public Double getScore()
    {
        return score;
    }

    public void setScore(Double score)
    {
        this.score = score;
    }

    public int hashCode()
    {
        if(id==null){
            id=0;
        }
        int result =  id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        return result;
    }

    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionTypeVo that = (QuestionTypeVo) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;

        return true;
    }

    public String toString()
    {
        return "QuestionTypeVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
