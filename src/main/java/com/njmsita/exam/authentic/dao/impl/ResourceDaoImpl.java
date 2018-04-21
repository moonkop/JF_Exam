package com.njmsita.exam.authentic.dao.impl;

import com.njmsita.exam.authentic.dao.dao.ResourceDao;
import com.njmsita.exam.authentic.dao.dao.RoleDao;
import com.njmsita.exam.authentic.model.TresourceVo;
import com.njmsita.exam.authentic.model.TroleVo;
import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryModel;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 资源持久层实现类
 */
@Repository
public class ResourceDaoImpl extends BaseImpl<TresourceVo> implements ResourceDao
{

    public void doQbc(Criteria dc, BaseQueryModel qm)
    {
    }



    public List<TresourceVo> getAllByLoginId(String id)
    {
        //查询逻辑：teacher--->role---->resource
        String hql ="select res from TeacherVo tv join tv.troleVo rv join rv.reses res where tv.id=? ";
        Query query = this.getCurrentSession().createQuery(hql);
        query.setParameter(0,id);
        return query.list();
    }
}
