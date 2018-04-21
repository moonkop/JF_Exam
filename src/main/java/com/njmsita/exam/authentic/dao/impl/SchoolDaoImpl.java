package com.njmsita.exam.authentic.dao.impl;

import com.njmsita.exam.authentic.dao.dao.RoleDao;
import com.njmsita.exam.authentic.dao.dao.SchoolDao;
import com.njmsita.exam.authentic.model.SchoolVo;
import com.njmsita.exam.authentic.model.TroleVo;
import com.njmsita.exam.authentic.model.querymodel.SchoolQueryVo;
import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

/**
 * 学校久层实现类
 */
@Repository
public class SchoolDaoImpl extends BaseImpl<SchoolVo> implements SchoolDao
{

    public void doQbc(DetachedCriteria dc, BaseQueryModel qm)
    {
        SchoolQueryVo schoolQueryVo = (SchoolQueryVo) qm;
    }

}
