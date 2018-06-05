package com.njmsita.exam.manager.model.querymodel;

import com.njmsita.exam.base.BaseListQueryVo;
import com.njmsita.exam.manager.model.ExamVo;

public class ExamListQueryModel extends ExamQueryModel implements BaseListQueryVo
{

    Integer offset;
    Integer pageSize;

    public void setOffset(Integer offset)
    {
        this.offset = offset;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    @Override
    public Integer getOffset()
    {
        return null;
    }

    @Override
    public Integer getPageSize()
    {
        return null;
    }
}
