package com.njmsita.exam.manager.model.querymodel;

import com.njmsita.exam.manager.model.StudentExamQuestionVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArchiveWrapper
{

    List<StudentExamQuestionVo> workouts;
    String id;

    public ArchiveWrapper()
    {
    }

    public List<StudentExamQuestionVo> getWorkouts()
    {
        return workouts;
    }

    public void setWorkouts(List<StudentExamQuestionVo> workouts)
    {
        this.workouts = workouts;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
}
