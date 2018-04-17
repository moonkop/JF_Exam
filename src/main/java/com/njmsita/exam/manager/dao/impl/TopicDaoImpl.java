package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryModel;
import com.njmsita.exam.manager.dao.dao.TopicDao;
import com.njmsita.exam.manager.model.TopicModel;
import com.njmsita.exam.manager.model.querymodel.TopicQueryModel;
import org.hibernate.criterion.DetachedCriteria;

public class TopicDaoImpl extends BaseImpl<TopicModel> implements TopicDao
{
    @Override
    public void doQbc(DetachedCriteria dc, BaseQueryModel qm)
    {
        TopicQueryModel tqm= (TopicQueryModel) qm;
    }
}
