package com.njmsita.exam.manager.dao.dao;

import com.njmsita.exam.manager.model.querymodel.ExamReport;

public interface ExamReportDao
{
    ExamReport get(String id);


    void insert(ExamReport report);
}
