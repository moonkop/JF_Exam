package com.njmsita.exam.manager.dao.dao;

import com.njmsita.exam.base.BaseDao;
import com.njmsita.exam.manager.model.TopicVo;

/**
 * 知识点持久层接口
 */
public interface TopicDao extends BaseDao<TopicVo>
{
    /**
     * 根据名称查找知识点
     * @return
     * @param name
     */
    public TopicVo getByName(String name);
}
