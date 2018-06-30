package com.njmsita.exam.manager.model.querymodel;

import java.util.List;

public class QuestionReport
{
    public Double scoreAvg;
    public Double scoreMid;
    public Double scoreMax;
    public String outline;
    public Integer type;
    public List<OptionReport> optionList;
    public List<WorkoutReport> workoutList;

    public Double getScoreAvg()
    {
        return scoreAvg;
    }

    public void setScoreAvg(Double scoreAvg)
    {
        this.scoreAvg = scoreAvg;
    }

    public Double getScoreMid()
    {
        return scoreMid;
    }

    public void setScoreMin(Double scoreMid)
    {
        this.scoreMid = scoreMid;
    }

    public Double getScoreMax()
    {
        return scoreMax;
    }

    public void setScoreMax(Double scoreMax)
    {
        this.scoreMax = scoreMax;
    }

    public String getOutline()
    {
        return outline;
    }

    public void setOutline(String outline)
    {
        this.outline = outline;
    }

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    public List<OptionReport> getOptionList()
    {
        return optionList;
    }

    public void setOptionList(List<OptionReport> optionList)
    {
        this.optionList = optionList;
    }

    public List<WorkoutReport> getWorkoutList()
    {
        return workoutList;
    }

    public void setWorkoutList(List<WorkoutReport> workoutList)
    {
        this.workoutList = workoutList;
    }
}
