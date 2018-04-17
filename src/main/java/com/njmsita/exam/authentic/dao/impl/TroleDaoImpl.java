package com.njmsita.exam.authentic.dao.impl;

import com.njmsita.exam.authentic.dao.dao.TroleDao;
import com.njmsita.exam.authentic.model.TroleModel;
import com.njmsita.exam.authentic.model.querymodel.TroleQueryModel;
import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryModel;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public class TroleDaoImpl extends BaseImpl<TroleModel> implements TroleDao
{
    public void doQbc(DetachedCriteria dc, BaseQueryModel qm)
    {
        TroleQueryModel rqm= (TroleQueryModel) qm;
    }
}
