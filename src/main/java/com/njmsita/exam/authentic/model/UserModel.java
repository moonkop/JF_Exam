package com.njmsita.exam.authentic.model;

/**
 * 用户模型
 */
public class UserModel
{
    private String resources;
    private String uuid;

    private String realName;
    private String userRole;

    public String getRealName()
    {
        return realName;
    }

    public void setRealName(String realName)
    {
        this.realName = realName;
    }

    public String getUserRole()
    {
        return userRole;
    }

    public void setUserRole(String userRole)
    {
        this.userRole = userRole;
    }

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    public String getResources()
    {
        return resources;
    }

    public void setResources(String resources)
    {
        this.resources = resources;
    }
}
