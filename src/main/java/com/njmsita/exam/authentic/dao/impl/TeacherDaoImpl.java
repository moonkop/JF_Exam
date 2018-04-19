package com.njmsita.exam.authentic.dao.impl;

import com.njmsita.exam.authentic.dao.dao.TeacherDao;
import com.njmsita.exam.authentic.model.TeacherModel;
import com.njmsita.exam.authentic.model.querymodel.TeacherQueryModel;
import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.HTML;
import java.io.Serializable;
import java.util.List;

@Repository
public class TeacherDaoImpl extends BaseImpl<TeacherModel> implements TeacherDao
{
    public void doQbc(DetachedCriteria dc, BaseQueryModel qm)
    {
        TeacherQueryModel tqm= (TeacherQueryModel) qm;
    }

    public TeacherModel getByUsernameAndPwd(String teacher_id, String password)
    {
        String hql="from TeacherModel where teacher_id=? and password=?";
        List<TeacherModel> htemp=this.getHibernateTemplate().find(hql,teacher_id,password);
        return htemp.size()>0?htemp.get(0):null;
    }
}
