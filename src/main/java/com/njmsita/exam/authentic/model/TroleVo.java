package com.njmsita.exam.authentic.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "trole", schema = "jf_exam", catalog = "")
public class TroleVo
{
    private String id;
    private String name;
    private String remark;
    private Integer seq;

    private Set<TresourceVo> reses;

    public Set<TresourceVo> getReses()
    {
        return reses;
    }

    public void setReses(Set<TresourceVo> reses)
    {
        this.reses = reses;
    }

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

    @Basic
    @Column(name = "REMARK")
    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    @Basic
    @Column(name = "SEQ")
    public Integer getSeq()
    {
        return seq;
    }

    public void setSeq(Integer seq)
    {
        this.seq = seq;
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (seq != null ? seq.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TroleVo troleVo = (TroleVo) o;

        if (id != null ? !id.equals(troleVo.id) : troleVo.id != null) return false;
        if (name != null ? !name.equals(troleVo.name) : troleVo.name != null) return false;
        if (remark != null ? !remark.equals(troleVo.remark) : troleVo.remark != null) return false;
        if (seq != null ? !seq.equals(troleVo.seq) : troleVo.seq != null) return false;

        return true;
    }
}
