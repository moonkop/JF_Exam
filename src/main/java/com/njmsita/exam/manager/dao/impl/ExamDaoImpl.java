package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryModel;
import com.njmsita.exam.manager.dao.dao.ExamDao;
import com.njmsita.exam.manager.model.ExamModel;
import com.njmsita.exam.manager.model.querymodel.ExamQueryModel;
import org.hibernate.criterion.DetachedCriteria;

public class ExamDaoImpl extends BaseImpl<ExamModel> implements ExamDao
{
    @Override
    public void doQbc(DetachedCriteria dc, BaseQueryModel qm)
    {
        ExamQueryModel eqm= (ExamQueryModel) qm;
    }
}
