package com.njmsita.exam.authentic.dao.impl;

import com.njmsita.exam.authentic.dao.dao.ResourceDao;
import com.njmsita.exam.authentic.model.TresourceVo;
import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 资源持久层实现类
 */
@Repository
public class ResourceDaoImpl extends BaseImpl<TresourceVo> implements ResourceDao
{



    public List<TresourceVo> getAllByLoginId(String id)
    {
        //查询逻辑：teacher--->role---->resource
        String hql ="select res from TeacherVo tv join tv.troleVo rv join rv.reses res where tv.id=? ";

        return (List<TresourceVo>) this.getHibernateTemplate().find(hql,id);
    }

    public void doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {

    }
}
