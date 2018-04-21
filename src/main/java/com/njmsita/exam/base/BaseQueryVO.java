package com.njmsita.exam.base;

public class BaseQueryVO implements BaseQueryModel
{
    private Integer pageNum;

    private Integer maxPageNum;

    private Integer dataTotal;

    public Integer getMaxPageNum()
    {
        return maxPageNum;
    }

    public void setMaxPageNum(Integer maxPageNum)
    {
        this.maxPageNum = maxPageNum;
    }

    public Integer getDataTotal()
    {
        return dataTotal;
    }

    public void setDataTotal(Integer dataTotal)
    {
        this.dataTotal = dataTotal;
    }

    public Integer getPageNum()
    {
        return pageNum;
    }

    public void setPageNum(Integer pageNum)
    {
        this.pageNum = pageNum;
    }
}
