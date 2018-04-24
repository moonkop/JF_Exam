package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.SchoolDao;
import com.njmsita.exam.manager.model.SchoolVo;
import com.njmsita.exam.manager.model.querymodel.SchoolQueryVo;
import com.njmsita.exam.base.BaseImpl;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 学校久层实现类
 */
@Repository
public class SchoolDaoImpl extends BaseImpl<SchoolVo> implements SchoolDao
{

    public void doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
        SchoolQueryVo schoolQueryVo = (SchoolQueryVo) qm;
    }

    public void delete(SchoolVo school)
    {
        this.getHibernateTemplate().delete(school);
    }

    public SchoolVo findByName(String name)
    {
        String hql="from SchoolVo where name=?";
        List<SchoolVo> list= (List<SchoolVo>) this.getHibernateTemplate().find(hql,name);
        return list.size()>0?list.get(0):null;
    }

}
