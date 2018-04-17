package com.njmsita.exam.authentic.model;

import java.util.Set;

/**
 * 角色实体模型
 */
public class TroleModel
{
    private String id;
    private String name;
    private String remarx;
    private Integer seq;

    //所属父角色     n TO 1
    private TroleModel parent;
    //所拥有子角色    1 TO n
    private Set<TroleModel> childs;
    //所拥有资源     n TO m
    private Set<TresourceModel> tresources;


    public Set<TroleModel> getChilds()
    {
        return childs;
    }

    public void setChilds(Set<TroleModel> childs)
    {
        this.childs = childs;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getRemarx()
    {
        return remarx;
    }

    public void setRemarx(String remarx)
    {
        this.remarx = remarx;
    }

    public Integer getSeq()
    {
        return seq;
    }

    public void setSeq(Integer seq)
    {
        this.seq = seq;
    }

    public TroleModel getParent()
    {
        return parent;
    }

    public void setParent(TroleModel parent)
    {
        this.parent = parent;
    }
}
