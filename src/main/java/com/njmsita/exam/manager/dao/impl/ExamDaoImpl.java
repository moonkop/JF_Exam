package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.ExamDao;
import com.njmsita.exam.manager.model.ExamVo;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 考试持久层实现类
 */
@Repository
public class ExamDaoImpl extends BaseImpl<ExamVo> implements ExamDao
{

    public void doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
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
