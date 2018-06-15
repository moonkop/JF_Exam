package com.njmsita.exam.manager.model.querymodel;

import com.njmsita.exam.manager.model.ExamVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExamEditWrapper
{
    String[] markerTeachers;
    List<String> _classroomIds;
    String paperId;
    Boolean immediately;
    ExamVo exam;

    public ExamVo getExam()
    {
        return exam;
    }

    public void setExam(ExamVo exam)
    {
        this.exam = exam;
    }

    public Boolean getImmediately()
    {
        return immediately;
    }

    public void setImmediately(Boolean immediately)
    {
        this.immediately = immediately;
    }

    public String[] getMarkerTeachers()
    {
        return markerTeachers;
    }

    public void setMarkerTeachers(String[] markerTeachers)
    {
        this.markerTeachers = markerTeachers;
    }

    public List<String> get_classroomIds()
    {
        return _classroomIds;
    }

    public void set_classroomIds(List<String> _classroomIds)
    {
        this._classroomIds = _classroomIds;
    }

    public String getPaperId()
    {
        return paperId;
    }

    public void setPaperId(String paperId)
    {
        this.paperId = paperId;
    }
}
