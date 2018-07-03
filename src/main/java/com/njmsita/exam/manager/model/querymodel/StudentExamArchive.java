package com.njmsita.exam.manager.model.querymodel;

import com.njmsita.exam.manager.model.StudentExamQuestionVo;
import com.njmsita.exam.manager.model.StudentExamVo;

import java.util.ArrayList;
import java.util.List;

public class StudentExamArchive
{
    String id;
    List<WorkoutArchive> workoutList;

    public StudentExamArchive()
    {

    }

    public StudentExamArchive(StudentExamVo studentExamVo)
    {
        workoutList = new ArrayList<>();
        for (StudentExamQuestionVo workout : studentExamVo.getStudentExamQuestionVos())
        {
            WorkoutArchive workoutArchive = new WorkoutArchive(workout);
            workoutList.add(workoutArchive);
        }
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public List<WorkoutArchive> getWorkoutList()
    {
        return workoutList;
    }

    public void setWorkoutList(List<WorkoutArchive> workoutList)
    {
        this.workoutList = workoutList;
    }
}
