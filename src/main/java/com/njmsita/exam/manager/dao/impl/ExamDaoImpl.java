package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.ExamDao;
import com.njmsita.exam.manager.dao.dao.LogDao;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.LogVo;
import com.njmsita.exam.manager.model.querymodel.LogQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 考试持久层实现类
 */
@Repository
public class ExamDaoImpl extends BaseImpl<ExamVo> implements ExamDao
{

    public void doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
    }
}
