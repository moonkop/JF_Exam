package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.SchoolDao;
import com.njmsita.exam.manager.dao.dao.SubjectDao;
import com.njmsita.exam.manager.model.SchoolVo;
import com.njmsita.exam.manager.model.SubjectVo;
import com.njmsita.exam.manager.model.querymodel.SchoolQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 学科持久层实现类
 */
@Repository
public class SubjectDaoImpl extends BaseImpl<SubjectVo> implements SubjectDao
{

    public void doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
    }
}
