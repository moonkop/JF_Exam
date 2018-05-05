package com.njmsita.exam.manager.dao.dao;

import com.njmsita.exam.base.BaseDao;
import com.njmsita.exam.manager.model.QuestionTypeVo;
import com.njmsita.exam.manager.model.SubjectVo;

/**
 * 题型持久层接口
 */
public interface QuestionTypeDao extends BaseDao<QuestionTypeVo>
{
    /**
     * 根据名称查询题型
     * @param name  题型名称
     * @return
     */
    public QuestionTypeVo getByName(String name);
}
