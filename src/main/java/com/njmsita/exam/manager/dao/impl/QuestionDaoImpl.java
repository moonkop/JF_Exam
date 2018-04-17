package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryModel;
import com.njmsita.exam.manager.dao.dao.QuestionDao;
import com.njmsita.exam.manager.model.QuestionModel;
import com.njmsita.exam.manager.model.querymodel.QuestionQueryModel;
import org.hibernate.criterion.DetachedCriteria;

public class QuestionDaoImpl extends BaseImpl<QuestionModel> implements QuestionDao
{
    @Override
    public void doQbc(DetachedCriteria dc, BaseQueryModel qm)
    {
        QuestionQueryModel qqm= (QuestionQueryModel) qm;
    }
}
