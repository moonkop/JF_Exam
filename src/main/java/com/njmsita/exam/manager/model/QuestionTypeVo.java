package com.njmsita.exam.manager.model;

import javax.persistence.*;

@Entity
@Table(name = "question_type", schema = "jf_exam", catalog = "")
public class QuestionTypeVo
{

    private byte id;
    private String name;
    private Double score;

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
        int result = (int) id;
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
