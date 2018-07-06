package com.njmsita.exam.manager.dao.dao;

import com.njmsita.exam.manager.model.querymodel.report.ExamReport;

public interface ExamReportDao
{
    ExamReport get(String id);


    void save(ExamReport report);
}
