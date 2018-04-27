package com.njmsita.exam.manager.model;

import javax.persistence.*;

@Entity
@Table(name = "log", schema = "jf_exam", catalog = "")
public class LogVo
{
    private int id;
    private String operator;
    private String brief;
    private String detail;
    private String ip;
    private Long time;

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
    @Column(name = "operator")
    public String getOperator()
    {
        return operator;
    }

    public void setOperator(String operator)
    {
        this.operator = operator;
    }

    @Basic
    @Column(name = "brief")
    public String getBrief()
    {
        return brief;
    }

    public void setBrief(String brief)
    {
        this.brief = brief;
    }

    @Basic
    @Column(name = "detail")
    public String getDetail()
    {
        return detail;
    }

    public void setDetail(String detail)
    {
        this.detail = detail;
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

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        result = 31 * result + (brief != null ? brief.hashCode() : 0);
        result = 31 * result + (detail != null ? detail.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogVo logVo = (LogVo) o;

        if (id != logVo.id) return false;
        if (operator != null ? !operator.equals(logVo.operator) : logVo.operator != null) return false;
        if (brief != null ? !brief.equals(logVo.brief) : logVo.brief != null) return false;
        if (detail != null ? !detail.equals(logVo.detail) : logVo.detail != null) return false;
        if (ip != null ? !ip.equals(logVo.ip) : logVo.ip != null) return false;
        if (time != null ? !time.equals(logVo.time) : logVo.time != null) return false;

        return true;
    }
}
