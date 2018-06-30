package com.njmsita.exam.manager.model.querymodel;

import java.util.List;

public class OptionReport
{
    public String text;
    public Integer PickNum;
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
        return PickNum;
    }

    public void setPickNum(Integer pickNum)
    {
        PickNum = pickNum;
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
