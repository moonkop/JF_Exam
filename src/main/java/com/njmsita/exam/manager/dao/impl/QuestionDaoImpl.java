package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.QuestionDao;
import com.njmsita.exam.manager.dao.dao.SubjectDao;
import com.njmsita.exam.manager.model.QuestionVo;
import com.njmsita.exam.manager.model.SubjectVo;
import com.njmsita.exam.manager.model.querymodel.QuestionQueryModel;
import com.njmsita.exam.utils.format.StringUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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
        QuestionQueryModel qqm= (QuestionQueryModel) qm;
        if(null!=qqm.getSubject()&&qqm.getSubject().getId()!=0){
            dc.createAlias("subject","s1");
            dc.add(Restrictions.eq("s1.id",qqm.getSubject().getId()));
        }
        if(null!=qqm.getTopic()&& StringUtil.isEmpty(qqm.getTopic().getId())){
            dc.createAlias("topic","to1");
            dc.add(Restrictions.eq("to1.id",qqm.getTopic().getId()));
        }
        if(null!=qqm.getTeacher()&&StringUtil.isEmpty(qqm.getTeacher().getId())){
            dc.createAlias("teacher","te1");
            dc.add(Restrictions.eq("te1.id", qqm.getTeacher().getId()));
        }
        if(null!=qqm.getQuestionType()&&qqm.getQuestionType().getId()!=0){
            dc.createAlias("questionType","que1");
            dc.add(Restrictions.eq("que1.id", qqm.getQuestionType().getId()));
        }
    }

    public List<QuestionVo> getBySubject(Integer id)
    {
        String hql="from QuestionVo where subject.id=?";
        return (List<QuestionVo>) this.getHibernateTemplate().find(hql,id);
    }

    public List<QuestionVo> getByQuestionType(Integer id)
    {
        String hql="from QuestionVo where questionType.id=?";
        return (List<QuestionVo>) this.getHibernateTemplate().find(hql,id);
    }
}
