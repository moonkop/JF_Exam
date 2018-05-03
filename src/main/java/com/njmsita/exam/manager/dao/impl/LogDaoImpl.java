package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.LogDao;
import com.njmsita.exam.manager.model.LogVo;
import com.njmsita.exam.manager.model.querymodel.LogQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 学校久层实现类
 */
@Repository
public class LogDaoImpl extends BaseImpl<LogVo> implements LogDao
{

    public void doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
        LogQueryModel lqm= (LogQueryModel) qm;
        dc.add(Restrictions.ge("time",lqm.getStartTime()));
        dc.add(Restrictions.le("time",lqm.getEndTime()+86400000-1));
    }


    public List<LogVo> getAll(LogQueryModel logQueryModel)
    {
        DetachedCriteria dc = DetachedCriteria.forClass(LogVo.class);
        doQbc(dc,logQueryModel);
        return (List<LogVo>) this.getHibernateTemplate().findByCriteria(dc);
    }
}
