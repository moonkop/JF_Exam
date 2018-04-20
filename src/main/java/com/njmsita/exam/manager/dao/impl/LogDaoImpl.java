package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryModel;
import com.njmsita.exam.manager.dao.dao.LogDao;
import com.njmsita.exam.manager.model.LogModel;
import com.njmsita.exam.manager.model.querymodel.LogQueryModel;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

@Repository
public class LogDaoImpl extends BaseImpl<LogModel> implements LogDao
{
    public void doQbc(Criteria dc, BaseQueryModel qm)
    {
        LogQueryModel lqm= (LogQueryModel) qm;
    }
}
