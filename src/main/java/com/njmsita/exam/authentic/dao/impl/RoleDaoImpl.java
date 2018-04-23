package com.njmsita.exam.authentic.dao.impl;

import com.njmsita.exam.authentic.dao.dao.RoleDao;
import com.njmsita.exam.authentic.model.TroleVo;
import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色持久层实现类
 */
@Repository
public class RoleDaoImpl extends BaseImpl<TroleVo> implements RoleDao
{

    public void doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {

    }

    public TroleVo getByName(String name)
    {
        String hql="from TroleVo where name=?";
        List<TroleVo> list= (List<TroleVo>) this.getHibernateTemplate().find(hql,name);
        return list.size()>0?list.get(0):null;
    }
}
