package com.njmsita.exam.authentic.model;

import java.util.List;
import java.util.Set;

/**
 * 资源类型实体
 */
public class TresourceTypeModel
{
    private String id;
    private String name;

    //类型下的所有资源 n TO 1
    private Set<TresourceModel> resourceList;

    public Set<TresourceModel> getResourceList()
    {
        return resourceList;
    }

    public void setResourceList(Set<TresourceModel> resourceList)
    {
        this.resourceList = resourceList;
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

}
