package com.njmsita.exam.manager.model.querymodel.report;

import java.util.ArrayList;
import java.util.List;

public class ExamReport
{
    public Double scoreAvg;
    public Double scoreMin;
    public Double scoreMax;
    public Integer studentCount;
    public Integer attendCount;
    private String id;

    public Integer getAttendCount()
    {
        return attendCount;
    }

    public void setAttendCount(Integer attendCount)
    {
        this.attendCount = attendCount;
    }

    public List<QuestionReport> questionReportList;

    public ExamReport()
    {
        this.questionReportList = new ArrayList<>();
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

    public void setScoreMin(Double scoreMin)
    {
        this.scoreMin = scoreMin;
    }

    public Double getScoreMax()
    {
        return scoreMax;
    }

    public void setScoreMax(Double scoreMax)
    {
        this.scoreMax = scoreMax;
    }

    public Integer getStudentCount()
    {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount)
    {
        this.studentCount = studentCount;
    }

    public List<QuestionReport> getQuestionReportList()
    {
        return questionReportList;
    }

    public void setQuestionReportList(List<QuestionReport> questionReportList)
    {
        this.questionReportList = questionReportList;
    }


    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
}


