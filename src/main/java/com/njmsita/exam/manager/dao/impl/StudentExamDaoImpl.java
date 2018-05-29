package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.StudentExamDao;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.StudentExamVo;
import com.njmsita.exam.manager.model.querymodel.StudentExamListQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 学生考试持久层实现类
 */
@Repository
public class StudentExamDaoImpl extends BaseImpl<StudentExamVo> implements StudentExamDao
{

    public DetachedCriteria doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
        StudentExamListQueryModel queryModel = (StudentExamListQueryModel) qm;

        dc.createAlias("student", "stu");
        if (queryModel.getStudent() != null && queryModel.getStudent().getId() != null)
        {
            dc.add(Restrictions.eq("stu.id", queryModel.getStudent().getId()));
        }
        if (queryModel.getExam() != null && queryModel.getExam().getId() != null)
        {
            dc.createAlias("exam", "ex");
            dc.add(Restrictions.eq("ex.id", queryModel.getExam().getId()));
        }

        return dc;
    }


    public void deleteAllByExam(ExamVo examVo)
    {
        List<StudentExamVo> list = this.getAllByExam(examVo);
        if (list.size() > 0)
        {
            this.getHibernateTemplate().deleteAll(list);
        }
    }
//
//    public List<StudentExamVo> getByStudent(StudentExamListQueryModel queryModel)
//    {
//        String hql="from StudentExamVo where studentVo.id=?";
//        return (List<StudentExamVo>) this.getHibernateTemplate().find(hql,studentId);
//    }

    public StudentExamVo getByStudentAndExam(ExamVo examVo, StudentVo studentVo)
    {
        String hql = "from StudentExamVo where exam.id=? and student.id=?";
        List<StudentExamVo> list = (List<StudentExamVo>) this.getHibernateTemplate().find(hql, examVo.getId(), studentVo.getId());
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<StudentExamVo> getbyExam(ExamVo examVo)
    {
        String hql = "from StudentExamVo where exam.id=?";
        return (List<StudentExamVo>) this.getHibernateTemplate().find(hql, examVo.getId());
    }

    private List<StudentExamVo> getAllByExam(ExamVo examVo)
    {
        String hql = "from StudentExamVo where exam.id=?";
        return (List<StudentExamVo>) this.getHibernateTemplate().find(hql, examVo.getId());
    }
}
