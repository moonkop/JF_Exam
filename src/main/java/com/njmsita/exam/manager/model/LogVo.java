package com.njmsita.exam.manager.model;

import javax.persistence.*;

@Entity
@Table(name = "log", schema = "jf_exam", catalog = "")
public class LogVo
{
    private int id;
    private String userId;
    private String module;
    private String method;
    private String ip;
    private Long time;
    private String commite;
    private Long responseTime;

    @Id
    @Column(name = "id")
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Basic
    @Column(name = "userId")
    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    @Basic
    @Column(name = "module")
    public String getModule()
    {
        return module;
    }

    public void setModule(String module)
    {
        this.module = module;
    }

    @Basic
    @Column(name = "method")
    public String getMethod()
    {
        return method;
    }

    public void setMethod(String method)
    {
        this.method = method;
    }

    @Basic
    @Column(name = "ip")
    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    @Basic
    @Column(name = "time")
    public Long getTime()
    {
        return time;
    }

    public void setTime(Long time)
    {
        this.time = time;
    }

    @Basic
    @Column(name = "commite")
    public String getCommite()
    {
        return commite;
    }

    public void setCommite(String commite)
    {
        this.commite = commite;
    }

    @Basic
    @Column(name = "response_time")
    public Long getResponseTime()
    {
        return responseTime;
    }

    public void setResponseTime(Long responseTime)
    {
        this.responseTime = responseTime;
    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (module != null ? module.hashCode() : 0);
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (commite != null ? commite.hashCode() : 0);
        result = 31 * result + (responseTime != null ? responseTime.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogVo logVo = (LogVo) o;

        if (id != logVo.id) return false;
        if (userId != null ? !userId.equals(logVo.userId) : logVo.userId != null) return false;
        if (module != null ? !module.equals(logVo.module) : logVo.module != null) return false;
        if (method != null ? !method.equals(logVo.method) : logVo.method != null) return false;
        if (ip != null ? !ip.equals(logVo.ip) : logVo.ip != null) return false;
        if (time != null ? !time.equals(logVo.time) : logVo.time != null) return false;
        if (commite != null ? !commite.equals(logVo.commite) : logVo.commite != null) return false;
        if (responseTime != null ? !responseTime.equals(logVo.responseTime) : logVo.responseTime != null) return false;

        return true;
    }
}
