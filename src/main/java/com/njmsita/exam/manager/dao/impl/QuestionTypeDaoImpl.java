package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.QuestionTypeDao;
import com.njmsita.exam.manager.dao.dao.SubjectDao;
import com.njmsita.exam.manager.model.QuestionTypeVo;
import com.njmsita.exam.manager.model.SubjectVo;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 题型持久层实现类
 */
@Repository
public class QuestionTypeDaoImpl extends BaseImpl<QuestionTypeVo> implements QuestionTypeDao
{

    public void doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
    }

    public QuestionTypeVo getByName(String name)
    {
        String hql="from QuestionTypeVo where name =?";
        List<QuestionTypeVo> list= (List<QuestionTypeVo>) this.getHibernateTemplate().find(hql,name);
        return list.size()>0?list.get(0):null;
    }
}
