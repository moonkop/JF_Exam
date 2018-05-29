package com.njmsita.exam.authentic.dao.impl;

import com.njmsita.exam.authentic.dao.dao.ResourcetypeDao;
import com.njmsita.exam.authentic.model.TresourcetypeVo;
import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

/**
 * 资源类型持久层实现类
 */
@Repository
public class ResourcetypeDaoImpl extends BaseImpl<TresourcetypeVo> implements ResourcetypeDao
{
    public DetachedCriteria doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
return dc;
    }
}
