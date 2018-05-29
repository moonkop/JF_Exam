package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.SubjectDao;
import com.njmsita.exam.manager.model.SubjectVo;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 学科持久层实现类
 */
@Repository
public class SubjectDaoImpl extends BaseImpl<SubjectVo> implements SubjectDao
{

    public DetachedCriteria doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
        return dc;
    }

    public SubjectVo getByName(String name)
    {
        String hql="from SubjectVo where name=?";
        List<SubjectVo> list= (List<SubjectVo>) this.getHibernateTemplate().find(hql,name);
        return list.size()>0?list.get(0):null;
    }
}
