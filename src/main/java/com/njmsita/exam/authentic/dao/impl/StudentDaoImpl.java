package com.njmsita.exam.authentic.dao.impl;

import com.njmsita.exam.authentic.dao.dao.StudentDao;
import com.njmsita.exam.authentic.model.StudentModel;
import com.njmsita.exam.authentic.model.querymodel.StudentQueryModel;
import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDaoImpl extends BaseImpl<StudentModel> implements StudentDao
{
    public void doQbc(DetachedCriteria dc, BaseQueryModel qm)
    {
        StudentQueryModel sqm= (StudentQueryModel) qm;
    }
}
