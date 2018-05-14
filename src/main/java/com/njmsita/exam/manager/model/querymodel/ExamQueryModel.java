package com.njmsita.exam.manager.model.querymodel;

import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.model.ClassroomVo;
import com.njmsita.exam.manager.model.ExamVo;

/**
 * 考试查询条件模型
 */
public class ExamQueryModel extends ExamVo implements BaseQueryVO
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
}
