package com.njmsita.exam.authentic.model;

import javax.persistence.*;

/**
 * 资源实体模型
 */
@Entity
@Table(name = "tresource", schema = "jf_exam", catalog = "")
public class TresourceVo
{
    private String id;
    private String icon;
    private String name;
    private String remark;
    private Integer seq;
    private String url;

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
    @Column(name = "ICON")
    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
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

    @Basic
    @Column(name = "URL")
    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (seq != null ? seq.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TresourceVo that = (TresourceVo) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (icon != null ? !icon.equals(that.icon) : that.icon != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (seq != null ? !seq.equals(that.seq) : that.seq != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }
}
