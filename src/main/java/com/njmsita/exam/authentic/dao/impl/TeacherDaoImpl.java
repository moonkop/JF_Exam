package com.njmsita.exam.authentic.dao.impl;

import com.njmsita.exam.authentic.dao.dao.TeacherDao;
import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.authentic.model.querymodel.TeacherQueryModel;
import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.utils.format.StringUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 教师持久层实现类
 */
@Repository
public class TeacherDaoImpl extends BaseImpl<TeacherVo> implements TeacherDao
{

    public DetachedCriteria doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
        TeacherQueryModel sqm= (TeacherQueryModel) qm;
        if(sqm.getRole()!=null&& !StringUtil.isEmpty(sqm.getRole().getId())){
            dc.createAlias("role","r");
            dc.add(Restrictions.eq("r.id",sqm.getRole().getId()));
        }

        return dc;

    }

    public TeacherVo getTeaByTeaIdAndPwd(String teacherId, String password)
    {
        String hql="from TeacherVo where teacherId=? and password=?";

        List list = this.getHibernateTemplate().find(hql,teacherId,password);
        return list.size()>0? (TeacherVo) list.get(0) :null;
    }

    public List<TeacherVo> getAllByRoleId(String id)
    {
        //teacher---->role
        String hql="from TeacherVo tv where tv.role.id=?";
        return (List<TeacherVo>) this.getHibernateTemplate().find(hql,id);
    }

    public TeacherVo getByTeacherId(String teacherId)
    {
        String hql="from TeacherVo where teacherId=?";
        List<TeacherVo> list= (List<TeacherVo>) this.getHibernateTemplate().find(hql,teacherId);
        return list.size()>0?list.get(0):null;
    }

    public void bulkInput(List<TeacherVo> teachers)
    {

        for (TeacherVo teacher : teachers)
        {
            this.getHibernateTemplate().save(teacher);
        }

    }

}
