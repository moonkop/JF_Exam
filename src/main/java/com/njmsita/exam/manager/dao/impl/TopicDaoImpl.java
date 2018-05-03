package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.QuestionTypeDao;
import com.njmsita.exam.manager.dao.dao.TopicDao;
import com.njmsita.exam.manager.model.QuestionTypeVo;
import com.njmsita.exam.manager.model.TopicVo;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

/**
 * 知识点持久层实现类
 */
@Repository
public class TopicDaoImpl extends BaseImpl<TopicVo> implements TopicDao
{

    public void doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
    }
}
