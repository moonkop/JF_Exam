package com.njmsita.exam.base;

public interface BaseListQueryVo extends BaseQueryVO
{
    default Integer getOffset()
    {
        return 0;
    }

    default Integer getPageSize()
    {
        return 0;
    }
}
