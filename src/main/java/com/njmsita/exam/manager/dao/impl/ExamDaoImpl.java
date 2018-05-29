package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.ExamDao;
import com.njmsita.exam.manager.dao.dao.PaperMongoDao;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.querymodel.ExamQueryModel;
import com.njmsita.exam.utils.format.StringUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * 考试持久层实现类
 */
@Repository
public class ExamDaoImpl extends BaseImpl<ExamVo> implements ExamDao
{
    @Autowired
    PaperMongoDao paperMongoDao;

    public ExamVo getExamWithPaper(Serializable uuid)
    {
        ExamVo examPo= super.get(uuid);
        examPo.setPaperVo(paperMongoDao.getPaperVoByExamId(examPo.getId()));
        return examPo;
    }

    public void SetPaper(ExamVo examVo)
    {
        examVo.setPaperVo(paperMongoDao.getPaperVoByExamId(examVo.getId()));
    }

    public DetachedCriteria doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
        ExamQueryModel eqm= (ExamQueryModel) qm;
        if(eqm.getSubject()!=null
                && eqm.getSubject().getId()!=null
                && eqm.getSubject().getId()!=0){
            dc.createAlias("subjectVo","sb");
            dc.add(Restrictions.eq("sb.id",eqm.getSubject().getId()));
        }
        if(eqm.getCreateTeacher()!=null&& StringUtil.isEmpty(eqm.getCreateTeacher().getId())){
            dc.createAlias("createTeacher","cre");
            dc.add(Restrictions.eq("cre.id",eqm.getCreateTeacher().getId()));
        }
        if(eqm.getExamStatus()!=null && eqm.getExamStatus()!=0){
            dc.add(Restrictions.eq("examStatus",eqm.getExamStatus()));
        }
        if(eqm.getStartTime()!=null && eqm.getStartTime()!=0){
            dc.add(Restrictions.ge("openTime",eqm.getStartTime()));
        }
        if(eqm.getEndTime()!=null && eqm.getEndTime()!=0){
            dc.add(Restrictions.le("openTime",eqm.getEndTime()+86400000-1));
        }
        return  dc;
    }

    public List<ExamVo> getByCreateTeacher(String teacherId)
    {
        String hql="from ExamVo where createTeacher.id=?";
        return (List<ExamVo>) this.getHibernateTemplate().find(hql,teacherId);
    }

    public List<ExamVo> getByMarkTeacher(String teacherId)
    {
        //exam-->teacher
        String hql="select distinct ex from ExamVo ex join TeacherVo te where te.id=?";
        return (List<ExamVo>) this.getHibernateTemplate().find(hql,teacherId);
    }
}
