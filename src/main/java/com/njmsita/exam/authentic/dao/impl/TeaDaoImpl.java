package com.njmsita.exam.authentic.dao.impl;

import com.njmsita.exam.authentic.dao.dao.TeacherDao;
import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.authentic.model.querymodel.TeaQueryModel;
import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryModel;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 教师持久层实现类
 */
@Repository
public class TeaDaoImpl extends BaseImpl<TeacherVo> implements TeacherDao
{

    public void doQbc(DetachedCriteria dc, BaseQueryModel qm)
    {
        TeaQueryModel sqm= (TeaQueryModel) qm;
    }

    public TeacherVo getTeaByTeaIdAndPwd(String teacherId, String password)
    {
        String hql="from TeacherVo where teacherId=? and password=?";

        List list = this.getHibernateTemplate().find(hql,teacherId,password);
        return list.size()>0? (TeacherVo) list.get(0) :null;
    }

}
