package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.SchoolDao;
import com.njmsita.exam.manager.model.SchoolVo;
import com.njmsita.exam.manager.model.querymodel.SchoolQueryModel;
import com.njmsita.exam.base.BaseImpl;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 学校持久层实现类
 */
@Repository
public class SchoolDaoImpl extends BaseImpl<SchoolVo> implements SchoolDao
{

    public DetachedCriteria doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
        SchoolQueryModel schoolQueryVo = (SchoolQueryModel) qm;
        return dc;
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
