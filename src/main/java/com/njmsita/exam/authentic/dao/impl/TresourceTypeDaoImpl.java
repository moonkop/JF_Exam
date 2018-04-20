package com.njmsita.exam.authentic.dao.impl;

import com.njmsita.exam.authentic.dao.dao.TresourceTypeDao;
import com.njmsita.exam.authentic.model.TresourceTypeModel;
import com.njmsita.exam.authentic.model.querymodel.TresourceTypeQueryModel;
import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryModel;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class TresourceTypeDaoImpl extends BaseImpl<TresourceTypeModel> implements TresourceTypeDao
{

    public void doQbc(Criteria dc, BaseQueryModel qm)
    {
        TresourceTypeQueryModel rtqm= (TresourceTypeQueryModel) qm;
    }
}
