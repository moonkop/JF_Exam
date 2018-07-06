package com.njmsita.exam.manager.model.querymodel;

import com.njmsita.exam.utils.consts.SysConsts;
import org.hibernate.sql.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


@Repository
public class StudentExamArchiveDaoImpl implements StudentExamArchiveDao
{
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public StudentExamArchive get(String id)
    {
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), StudentExamArchive.class, SysConsts.STUDENT_EXAM_ARCHIVE_COLLECTION_NAME);
    }

    @Override
    public void save(StudentExamArchive archive)
    {
        mongoTemplate.save(archive, SysConsts.STUDENT_EXAM_ARCHIVE_COLLECTION_NAME);
    }
}
