package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.TopicDao;
import com.njmsita.exam.manager.model.TopicVo;
import com.njmsita.exam.utils.format.StringUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 知识点持久层实现类
 */
@Repository
public class TopicDaoImpl extends BaseImpl<TopicVo> implements TopicDao
{

    public void doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
    }

    public TopicVo getByName(String name)
    {
        String hql="from TopicVo where name=?";
        List<TopicVo> list= (List<TopicVo>) this.getHibernateTemplate().find(hql,name);
        return list.size()>0?list.get(0):null;
    }

    public List<TopicVo> getBySubject(Integer subjectId, String parent)
    {
        DetachedCriteria dc=DetachedCriteria.forClass(TopicVo.class);
        dc.createAlias("subjectVo","s2");
        dc.add(Restrictions.eq("s2.id",subjectId));
        return (List<TopicVo>) this.getHibernateTemplate().findByCriteria(dc);
    }
}
