package com.njmsita.exam.manager.model;

import com.njmsita.exam.authentic.model.TeacherModel;
import com.njmsita.exam.utils.format.FormatUtil;

/**
 * 日志实体模型
 */
public class LogModel
{
    private String id;
    private String ip;
    //操作类型
    private String brief;

    private Long time;

    private String timeView;

    //操作者
    private TeacherModel operator;

    public Long getTime()
    {
        return time;
    }

    public void setTime(Long time)
    {
        this.time = time;
        this.timeView= FormatUtil.formatDateTime(time);
    }

    public String getTimeView()
    {
        return timeView;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getBrief()
    {
        return brief;
    }

    public void setBrief(String brief)
    {
        this.brief = brief;
    }

    public TeacherModel getOperator()
    {
        return operator;
    }

    public void setOperator(TeacherModel operator)
    {
        this.operator = operator;
    }
}
