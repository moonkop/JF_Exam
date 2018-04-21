package com.njmsita.exam.authentic.dao.impl;

import com.njmsita.exam.authentic.dao.dao.RoleDao;
import com.njmsita.exam.authentic.model.TroleVo;
import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryModel;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

/**
 * 角色持久层实现类
 */
@Repository
public class RoleDaoImpl extends BaseImpl<TroleVo> implements RoleDao
{

    public void doQbc(DetachedCriteria dc, BaseQueryModel qm)
    {

    }

}
