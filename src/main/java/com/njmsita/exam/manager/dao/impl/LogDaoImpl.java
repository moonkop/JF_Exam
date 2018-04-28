package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.LogDao;
import com.njmsita.exam.manager.model.LogVo;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

/**
 * 学校久层实现类
 */
@Repository
public class LogDaoImpl extends BaseImpl<LogVo> implements LogDao
{

    public void doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
    }


}
