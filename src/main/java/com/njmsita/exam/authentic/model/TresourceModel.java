package com.njmsita.exam.authentic.model;

import java.util.Set;

/**
 * 资源实体模型
 */
public class TresourceModel
{
    private String id;
    private String name;
    private String remark;
    private Integer seq;
    private String url;
    private String icon;

    //所拥有子资源   1 TO n
    private Set<TresourceModel> childs;
    //所属父资源
    private TresourceModel parent;

    public Set<TresourceModel> getChilds()
    {
        return childs;
    }

    public void setChilds(Set<TresourceModel> childs)
    {
        this.childs = childs;
    }

    public TresourceModel getParent()
    {
        return parent;
    }

    public void setParent(TresourceModel parent)
    {
        this.parent = parent;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSeq() {
        return this.seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
