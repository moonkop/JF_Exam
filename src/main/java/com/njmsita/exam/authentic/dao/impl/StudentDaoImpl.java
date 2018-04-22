package com.njmsita.exam.authentic.dao.impl;

import com.njmsita.exam.authentic.dao.dao.StudentDao;
import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.authentic.model.querymodel.StudentQueryModel;
import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 学生持久层实现类
 */
@Repository
public class StudentDaoImpl extends BaseImpl<StudentVo> implements StudentDao
{

    public void doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
        StudentQueryModel sqm= (StudentQueryModel) qm;
    }

    public List<StudentVo> getAllBySchoolId(String id)
    {
        //student---->school
        String hql="from StudentVo sv where sv.school.id=?";
        return (List<StudentVo>) this.getHibernateTemplate().find(hql,id);
    }
}
