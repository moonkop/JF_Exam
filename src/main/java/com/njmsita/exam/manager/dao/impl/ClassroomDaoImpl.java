package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.ClassroomDao;
import com.njmsita.exam.manager.model.ClassroomVo;
import com.njmsita.exam.manager.model.querymodel.ClassroomQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 班级持久层实现类
 */
@Repository
public class ClassroomDaoImpl extends BaseImpl<ClassroomVo> implements ClassroomDao
{

    public DetachedCriteria doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
        ClassroomQueryModel classroomQueryVo= (ClassroomQueryModel) qm;
        return dc;
    }

    public void delete(ClassroomVo classroom)
    {
        this.getHibernateTemplate().delete(classroom);
    }

    public List<ClassroomVo> getAllBySchoolId(String id)
    {
        //classroom----->school
        String hql="from ClassroomVo where schoolVo.id=?";
        return (List<ClassroomVo>) this.getHibernateTemplate().find(hql,id);
    }

    public ClassroomVo findByNameFrom(String name, String schoolId)
    {
        //classroom----->school
        String hql="from ClassroomVo where schoolVo.id=? and name=?";
        List<ClassroomVo> list=(List<ClassroomVo>) this.getHibernateTemplate().find(hql,schoolId,name);
        return list.size()>0?list.get(0):null;
    }

    public ClassroomVo getByClassroomIdFromSchool(String classroomId, String schoolId)
    {
        //classroom---->school
        String hql="from ClassroomVo where id=? and schoolVo.id=?";
        List<ClassroomVo> list= (List<ClassroomVo>) this.getHibernateTemplate().find(hql,classroomId,schoolId);
        return list.size()>0?list.get(0):null;
    }

}
