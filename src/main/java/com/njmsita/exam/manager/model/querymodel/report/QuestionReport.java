package com.njmsita.exam.manager.model.querymodel.report;

import java.util.List;

public class QuestionReport
{
    public Double scoreAvg;
    public Double scoreMin;
    public Double scoreMax;
    public Double scoreRate;
    public String outline;
    public Integer type;
    public Integer index;
    public List<String> answer;
    public List<OptionReport> optionList;
    public List<WorkoutReport> workoutList;

    public Double getScoreRate()
    {
        return scoreRate;
    }

    public void setScoreRate(Double scoreRate)
    {
        this.scoreRate = scoreRate;
    }

    public Integer getIndex()
    {
        return index;
    }

    public void setIndex(Integer index)
    {
        this.index = index;
    }

    public List<String> getAnswer()
    {
        return answer;
    }

    public void setAnswer(List<String> answer)
    {
        this.answer = answer;
    }

    public Double getScoreAvg()
    {
        return scoreAvg;
    }

    public void setScoreAvg(Double scoreAvg)
    {
        this.scoreAvg = scoreAvg;
    }

    public Double getScoreMin()
    {
        return scoreMin;
    }

    public void setScoreMin(Double scoreMid)
    {
        this.scoreMin = scoreMid;
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
