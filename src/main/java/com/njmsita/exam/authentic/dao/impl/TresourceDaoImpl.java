package com.njmsita.exam.authentic.dao.impl;

import com.njmsita.exam.authentic.dao.dao.TresourceDao;
import com.njmsita.exam.authentic.model.TresourceModel;
import com.njmsita.exam.authentic.model.querymodel.TresourceQueryModel;
import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class TresourceDaoImpl extends BaseImpl<TresourceModel> implements TresourceDao
{
    public void doQbc(DetachedCriteria dc, BaseQueryModel qm)
    {
        TresourceQueryModel rqm= (TresourceQueryModel) qm;
    }

    public List<TresourceModel> getAllByLoginTea(String id)
    {
        String hql ="select resm from TeacherModel tm join tm.role rm join rm.tresources resm where tm.id=?";
        return this.getHibernateTemplate().find(hql,id);
    }
}
