package com.njmsita.exam.authentic.dao.impl;

import com.njmsita.exam.authentic.dao.dao.ResourceDao;
import com.njmsita.exam.authentic.dao.dao.ResourcetypeDao;
import com.njmsita.exam.authentic.model.TresourcetypeVo;
import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 资源持久层实现类
 */
@Repository
public class ResourcetypeDaoImpl extends BaseImpl<TresourcetypeVo> implements ResourcetypeDao
{
    public void doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {

    }
}
