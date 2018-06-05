package com.njmsita.exam.manager.model.querymodel;

import com.njmsita.exam.base.BaseListQueryVo;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.model.StudentExamVo;

public class StudentExamListQueryModel extends StudentExamQueryModel implements BaseListQueryVo
{
    public Integer offset;
    public Integer pageSize;

    @Override
    public Integer getOffset()
    {
        return offset;
    }

    public void setOffset(Integer offset)
    {
        this.offset = offset;
    }
    @Override
    public Integer getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }
}
