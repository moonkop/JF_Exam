package com.njmsita.exam.manager.model.querymodel;

public interface StudentExamArchiveDao
{

    StudentExamArchive get(String id);

    void insert(StudentExamArchive archive);
}
