package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryModel;
import com.njmsita.exam.manager.dao.dao.PaperDao;
import com.njmsita.exam.manager.model.PaperModel;
import com.njmsita.exam.manager.model.querymodel.PaperQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

@Repository
public class PaperDaoImpl extends BaseImpl<PaperModel> implements PaperDao
{
    public void doQbc(DetachedCriteria dc, BaseQueryModel qm)
    {
        PaperQueryModel pqm= (PaperQueryModel) qm;
    }
}
