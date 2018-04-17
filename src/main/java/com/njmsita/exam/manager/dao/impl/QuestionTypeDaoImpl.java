package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryModel;
import com.njmsita.exam.manager.dao.dao.QuestionTypeDao;
import com.njmsita.exam.manager.model.QuestionTypeModel;
import com.njmsita.exam.manager.model.querymodel.QuestionTypeQueryModel;
import org.hibernate.criterion.DetachedCriteria;

public class QuestionTypeDaoImpl extends BaseImpl<QuestionTypeModel> implements QuestionTypeDao
{
    public void doQbc(DetachedCriteria dc, BaseQueryModel qm)
    {
        QuestionTypeQueryModel qtqm= (QuestionTypeQueryModel) qm;
    }
}
