package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.LogDao;
import com.njmsita.exam.manager.dao.dao.StudentExamQuestionDao;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.LogVo;
import com.njmsita.exam.manager.model.StudentExamQuestionVo;
import com.njmsita.exam.manager.model.StudentExamVo;
import com.njmsita.exam.manager.model.querymodel.LogQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 学生作答情况持久层实现类
 */
@Repository
public class StudentExamQuestionDaoImpl extends BaseImpl<StudentExamQuestionVo> implements StudentExamQuestionDao
{

    public void doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
    }

    public List<StudentExamQuestionVo> getAllByStudentExam(StudentExamVo studentExamVo)
    {
        String hql="from StudentExamQuestionVo where studentExamVo.id=?";
        return (List<StudentExamQuestionVo>) this.getHibernateTemplate().find(hql,studentExamVo.getId());
    }

    public List<StudentExamQuestionVo> getByExam(ExamVo examVo)
    {
        //StudentExamQuestionVo  -->  studentExamVo  -->  examVo
        String hql="select distinct seqv from StudentExamQuestionVo seqv join seqv.studentExamVo sev join sev.examVo ev where ev.id=?";
        return (List<StudentExamQuestionVo>) this.getHibernateTemplate().find(hql,examVo.getId());
    }
}
