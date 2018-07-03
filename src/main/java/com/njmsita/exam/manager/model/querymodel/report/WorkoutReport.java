package com.njmsita.exam.manager.model.querymodel.report;

public class WorkoutReport
{
    public String workout;
    public String remark;
    public Double score;
    public TeacherBrief markTeacher;
    public StudentBrief student;

    public Double getScore()
    {
        return score;
    }

    public void setScore(Double score)
    {
        this.score = score;
    }

    public String getWorkout()
    {
        return workout;
    }

    public void setWorkout(String workout)
    {
        this.workout = workout;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }


    public TeacherBrief getMarkTeacher()
    {
        return markTeacher;
    }

    public void setMarkTeacher(TeacherBrief markTeacher)
    {
        this.markTeacher = markTeacher;
    }

    public StudentBrief getStudent()
    {
        return student;
    }

    public void setStudent(StudentBrief student)
    {
        this.student = student;
    }
}
