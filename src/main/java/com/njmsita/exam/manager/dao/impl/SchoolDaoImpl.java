package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryModel;
import com.njmsita.exam.manager.dao.dao.SchoolDao;
import com.njmsita.exam.manager.model.SchoolModel;
import com.njmsita.exam.manager.model.querymodel.SchoolQueryModel;
import org.hibernate.criterion.DetachedCriteria;

public class SchoolDaoImpl extends BaseImpl<SchoolModel> implements SchoolDao
{
    @Override
    public void doQbc(DetachedCriteria dc, BaseQueryModel qm)
    {
        SchoolQueryModel sqm= (SchoolQueryModel) qm;
    }
}
