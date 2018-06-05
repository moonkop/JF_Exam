package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.ScheduleDao;
import com.njmsita.exam.manager.model.ScheduleVo;
import com.njmsita.exam.utils.consts.SysConsts;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 日志持久层实现类
 */
@Repository
public class ScheduleDaoImpl extends BaseImpl<ScheduleVo> implements ScheduleDao
{

    public DetachedCriteria doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
        return dc;
    }

    public List<ScheduleVo> getByTarget(String examId)
    {
        String hql="select distinct sv from ScheduleVo sv where sv.targetVoId=?";
        return (List<ScheduleVo>) this.getHibernateTemplate().find(hql,examId);
    }

    public List<ScheduleVo> getAllByExecutable()
    {
        String hql="from ScheduleVo where jobStatus=? or jobStatus=?";
        return (List<ScheduleVo>) this.getHibernateTemplate().find(hql, SysConsts.SCHEDULEVO_JOB_STATUS_ONETIME,SysConsts.SCHEDULEVO_JOB_STATUS_START);
    }

}
