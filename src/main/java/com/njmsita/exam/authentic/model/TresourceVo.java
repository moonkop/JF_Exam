package com.njmsita.exam.authentic.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.njmsita.exam.utils.validate.validategroup.AddGroup;
import com.njmsita.exam.utils.validate.validategroup.EditGroup;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * 资源实体模型
 */
@Entity
@Table(name = "tresource", schema = "jf_exam", catalog = "")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,property = "id")
public class TresourceVo
{
    @NotEmpty(message = "{id.notempty}",groups = {EditGroup.class})
    private String id;
    private String icon;

    @NotEmpty(message = "{name.notempty}",groups = {AddGroup.class, EditGroup.class})
    private String name;
    private String remark;

    @NotNull(message = "{seq.notempty}",groups = {AddGroup.class, EditGroup.class})
    private Integer seq;

    @NotEmpty(message = "{url.notempty}",groups = {AddGroup.class, EditGroup.class})
    private String url;

    //所属资源类型 n TO 1
    private TresourcetypeVo resourcetype;

    //拥有的子类 1 TO n
    private Set<TresourceVo> childs;
    //所属父类  n TO 1
    private TresourceVo parent;

    public Set<TroleVo> getRoles()
    {
        return roles;
    }

    public void setRoles(Set<TroleVo> roles)
    {
        this.roles = roles;
    }

    //所对应的角色
    private Set<TroleVo> roles;


    public Set<TresourceVo> getChilds()
    {
        return childs;
    }

    public void setChilds(Set<TresourceVo> childs)
    {
        this.childs = childs;
    }

    @Basic
    @Column(name = "pid")
    public TresourceVo getParent()
    {
        return parent;
    }

    public void setParent(TresourceVo parent)
    {
        this.parent = parent;
    }

    @Basic
    @Column(name = "tresourcetype_id")
    public TresourcetypeVo getResourcetype()
    {
        return resourcetype;
    }

    public void setResourcetype(TresourcetypeVo resourcetype)
    {
        this.resourcetype = resourcetype;
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

    public String toString()
    {
        return "TresourceVo{" +
                "id='" + id + '\'' +
                ", icon='" + icon + '\'' +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", seq=" + seq +
                ", url='" + url + '\'' +
                ", resourcetype=" + resourcetype +
                ", childs=" + childs +
                ", parent=" + parent +
                ", roles=" + roles +
                '}';
    }
}
