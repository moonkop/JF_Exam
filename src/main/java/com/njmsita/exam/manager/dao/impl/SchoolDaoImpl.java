package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.manager.dao.dao.SchoolDao;
import com.njmsita.exam.manager.model.SchoolVo;
import com.njmsita.exam.manager.model.querymodel.SchoolQueryVo;
import com.njmsita.exam.base.BaseImpl;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

/**
 * 学校久层实现类
 */
@Repository
public class SchoolDaoImpl extends BaseImpl<SchoolVo> implements SchoolDao
{

    public void doQbc(DetachedCriteria dc, BaseQueryModel qm)
    {
        SchoolQueryVo schoolQueryVo = (SchoolQueryVo) qm;
    }

    public void save1(SchoolVo school)
    {
        this.getHibernateTemplate().save(school);
    }

    public void delete(SchoolVo school)
    {
        this.getHibernateTemplate().delete(school);
    }
}
