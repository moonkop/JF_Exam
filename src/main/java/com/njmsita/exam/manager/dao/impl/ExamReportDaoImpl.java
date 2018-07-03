package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.manager.dao.dao.ExamReportDao;
import com.njmsita.exam.manager.model.querymodel.report.ExamReport;
import com.njmsita.exam.utils.consts.SysConsts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


@Repository
public class ExamReportDaoImpl implements ExamReportDao
{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public ExamReport get(String id)
    {
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), ExamReport.class,SysConsts.EXAM_REPORT_COLLECTION_NAME);
    }

    @Override
    public void insert(ExamReport report)
    {
        mongoTemplate.insert(report, SysConsts.EXAM_REPORT_COLLECTION_NAME);
    }
}
