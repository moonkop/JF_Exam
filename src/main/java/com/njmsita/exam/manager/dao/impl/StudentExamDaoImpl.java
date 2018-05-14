package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.ScheduleDao;
import com.njmsita.exam.manager.dao.dao.StudentExamDao;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.ScheduleVo;
import com.njmsita.exam.manager.model.StudentExamVo;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * 学生考试持久层实现类
 */
@Repository
public class StudentExamDaoImpl extends BaseImpl<StudentExamVo> implements StudentExamDao
{

    public void doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
    }

    public void deleteAllByExam(ExamVo examVo)
    {
        List<StudentExamVo> list=this.getAllByExam(examVo);
        if(list.size()>0){
            this.getHibernateTemplate().deleteAll(list);
        }
    }

    public List<StudentExamVo> getByStudent(String studentId)
    {
        String hql="from StudentExamVo where studentVo.id=?";
        return (List<StudentExamVo>) this.getHibernateTemplate().find(hql,studentId);
    }

    public StudentExamVo getByStudentAndExam(ExamVo examVo, StudentVo studentVo)
    {
        String hql="from StudentExamVo where examVo.id=? and studentVo.id=?";
        List<StudentExamVo> list= (List<StudentExamVo>) this.getHibernateTemplate().find(hql,examVo.getId(),studentVo.getId());
        return list.size()>0 ? list.get(0) : null;
    }

    private List<StudentExamVo> getAllByExam(ExamVo examVo)
    {
        String hql="from StudentExamVo where examVo.id=?";
        return (List<StudentExamVo>) this.getHibernateTemplate().find(hql,examVo.getId());
    }
}
