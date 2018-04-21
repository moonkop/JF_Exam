package com.njmsita.exam.authentic.dao.impl;

import com.njmsita.exam.authentic.dao.dao.TeacherDao;
import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.authentic.model.querymodel.TeaQueryModel;
import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryModel;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherDaoImpl extends BaseImpl<TeacherVo> implements TeacherDao
{

    public void doQbc(Criteria dc, BaseQueryModel qm)
    {
        TeaQueryModel sqm= (TeaQueryModel) qm;
    }

    public TeacherVo getTeaByTeaIdAndPwd(String teacherId, String password)
    {
        String hql="from TeacherVo where teacherId=? and password=?";
        Query query = this.getCurrentSession().createQuery(hql);
        query.setParameter(0,teacherId);
        query.setParameter(1,password);
        List list = query.list();
        return list.size()>0? (TeacherVo) list.get(0) :null;
    }
}
