package com.njmsita.exam.manager.model.querymodel.report;

import java.util.List;

public class OptionReport
{
    public String text;
    public Integer pickNum;
    public Double pickRate;
    public List<StudentBrief> students;

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public Integer getPickNum()
    {
        return pickNum;
    }

    public void setPickNum(Integer pickNum)
    {
        this.pickNum = pickNum;
    }

    public Double getPickRate()
    {
        return pickRate;
    }

    public void setPickRate(Double pickRate)
    {
        this.pickRate = pickRate;
    }

    public List<StudentBrief> getStudents()
    {
        return students;
    }

    public void setStudents(List<StudentBrief> students)
    {
        this.students = students;
    }
}
