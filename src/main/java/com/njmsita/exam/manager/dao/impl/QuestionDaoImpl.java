package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.QuestionDao;
import com.njmsita.exam.manager.dao.dao.SubjectDao;
import com.njmsita.exam.manager.model.QuestionVo;
import com.njmsita.exam.manager.model.SubjectVo;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

/**
 * 题目持久层实现类
 */
@Repository
public class QuestionDaoImpl extends BaseImpl<QuestionVo> implements QuestionDao
{

    public void doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
    }
}
