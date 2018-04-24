package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.ClassroomDao;
import com.njmsita.exam.manager.dao.dao.SchoolDao;
import com.njmsita.exam.manager.model.ClassroomVo;
import com.njmsita.exam.manager.model.SchoolVo;
import com.njmsita.exam.manager.model.querymodel.ClassroomQueryVo;
import com.njmsita.exam.manager.model.querymodel.SchoolQueryVo;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 班级久层实现类
 */
@Repository
public class ClassroomDaoImpl extends BaseImpl<ClassroomVo> implements ClassroomDao
{

    public void doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
        ClassroomQueryVo classroomQueryVo= (ClassroomQueryVo) qm;
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

}
