package com.njmsita.exam.manager.model.querymodel;

import com.njmsita.exam.manager.model.StudentExamQuestionVo;
import com.njmsita.exam.manager.model.querymodel.report.TeacherBrief;

public class WorkoutArchive
{
    private Integer index;
    private Double score;
    private String remark;
    private String workout;
    private TeacherBrief markTeacher;

    public WorkoutArchive()
    {
    }

    public WorkoutArchive(StudentExamQuestionVo workout)
    {
        this.index = workout.getIndex();
        this.score = workout.getScore();
        this.remark = workout.getRemark();
        this.workout = workout.getWorkout();
        if (workout.getTeacherVo() != null)
        {
            this.markTeacher = workout.getTeacherVo().getBrief();
        }
    }

    public Integer getIndex()
    {
        return index;
    }

    public void setIndex(Integer index)
    {
        this.index = index;
    }

    public Double getScore()
    {
        return score;
    }

    public void setScore(Double score)
    {
        this.score = score;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getWorkout()
    {
        return workout;
    }

    public void setWorkout(String workout)
    {
        this.workout = workout;
    }

    public TeacherBrief getMarkTeacher()
    {
        return markTeacher;
    }

    public void setMarkTeacher(TeacherBrief markTeacher)
    {
        this.markTeacher = markTeacher;
    }
}
