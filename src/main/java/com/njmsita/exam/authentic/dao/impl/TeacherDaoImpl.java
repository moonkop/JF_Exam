package com.njmsita.exam.authentic.dao.impl;

import com.njmsita.exam.authentic.dao.dao.TeacherDao;
import com.njmsita.exam.authentic.model.TeacherModel;
import com.njmsita.exam.authentic.model.querymodel.TeacherQueryModel;
import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryModel;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherDaoImpl extends BaseImpl<TeacherModel> implements TeacherDao
{
    public void doQbc(Criteria dc, BaseQueryModel qm)
    {
        TeacherQueryModel tqm= (TeacherQueryModel) qm;
    }

    public TeacherModel getByUsernameAndPwd(String teacher_id, String password)
    {
        String hql="from TeacherModel where teacher_id=? and password=?";
        System.out.println("------------------------------");
        Query query = this.getCurrentSession().createQuery(hql);
        query.setParameter(0,teacher_id);
        query.setParameter(1,password);
        System.out.println("------------------------------");
        return query.list().size()>0? (TeacherModel) query.list().get(0) :null;
    }
}
