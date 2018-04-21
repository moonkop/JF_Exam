package com.njmsita.exam.authentic.dao.impl;

import com.njmsita.exam.authentic.dao.dao.StudentDao;
import com.njmsita.exam.authentic.model.StudentEntity;
import com.njmsita.exam.authentic.model.querymodel.StudentQueryModel;
import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryModel;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

/**
 * 学生持久层实现类
 */
@Repository
public class StudentDaoImpl extends BaseImpl<StudentEntity> implements StudentDao
{

    public void doQbc(DetachedCriteria dc, BaseQueryModel qm)
    {
        StudentQueryModel sqm= (StudentQueryModel) qm;
    }

}
