package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.QuestionDao;
import com.njmsita.exam.manager.dao.dao.SubjectDao;
import com.njmsita.exam.manager.model.QuestionVo;
import com.njmsita.exam.manager.model.SubjectVo;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 题目持久层实现类
 */
@Repository
public class QuestionDaoImpl extends BaseImpl<QuestionVo> implements QuestionDao
{

    public void doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
    }

    public List<QuestionVo> getBySubject(Integer id)
    {
        String hql="from QuestionVo where subject.id=?";
        return (List<QuestionVo>) this.getHibernateTemplate().find(hql,id);
    }

    public List<QuestionVo> getByQuestionType(Byte id)
    {
        String hql="from QuestionVo where questionType.id=?";
        return (List<QuestionVo>) this.getHibernateTemplate().find(hql,id);
    }
}
