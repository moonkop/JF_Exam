package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryModel;
import com.njmsita.exam.manager.dao.dao.SubjectDao;
import com.njmsita.exam.manager.model.SubjectModel;
import com.njmsita.exam.manager.model.querymodel.SubjectQueryModel;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectDaoImpl extends BaseImpl<SubjectModel> implements SubjectDao
{

    public void doQbc(Criteria dc, BaseQueryModel qm)
    {
        SubjectQueryModel sqm= (SubjectQueryModel) qm;
    }
}
