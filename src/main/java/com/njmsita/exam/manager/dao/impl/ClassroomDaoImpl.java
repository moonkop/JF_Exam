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

}
