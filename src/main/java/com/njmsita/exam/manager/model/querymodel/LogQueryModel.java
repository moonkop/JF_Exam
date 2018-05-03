package com.njmsita.exam.manager.model.querymodel;

import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.model.LogVo;
import com.njmsita.exam.manager.model.SchoolVo;

/**
 * 日志查询条件模型
 */
public class LogQueryModel extends LogVo implements BaseQueryVO
{
    private Long startTime;
    private Long endTime;

    public Long getStartTime()
    {
        return startTime;
    }

    public void setStartTime(Long startTime)
    {
        this.startTime = startTime;
    }

    public Long getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Long endTime)
    {
        this.endTime = endTime;
    }

    public String toString()
    {
        return "LogQueryModel{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
