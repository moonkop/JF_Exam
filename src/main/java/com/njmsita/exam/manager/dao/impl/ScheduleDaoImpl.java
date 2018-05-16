package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.LogDao;
import com.njmsita.exam.manager.dao.dao.ScheduleDao;
import com.njmsita.exam.manager.model.LogVo;
import com.njmsita.exam.manager.model.ScheduleVo;
import com.njmsita.exam.manager.model.querymodel.LogQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 日志持久层实现类
 */
@Repository
public class ScheduleDaoImpl extends BaseImpl<ScheduleVo> implements ScheduleDao
{

    public void doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
    }

    public List<ScheduleVo> getByTarget(String examId)
    {
        String hql="select distinct sv from ScheduleVo sv where sv.targetVoId.id=?";
        return (List<ScheduleVo>) this.getHibernateTemplate().find(hql,examId);
    }

}
