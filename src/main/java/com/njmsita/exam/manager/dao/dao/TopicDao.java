package com.njmsita.exam.manager.dao.dao;

import com.njmsita.exam.base.BaseDao;
import com.njmsita.exam.manager.model.TopicVo;

import java.util.List;

/**
 * 知识点持久层接口
 */
public interface TopicDao extends BaseDao<TopicVo>
{
    /**
     * 根据名称查找知识点
     * @return
     * @param name
     * @param subjectId
     */
    public TopicVo getByName(String name, Integer subjectId);

    /**
     * 根据学科以及父知识点获取知识点
     * @param subjectId 学科id
     * @param parent    父知识点
     * @return          知识点集合
     */
    public List<TopicVo> getBySubject(Integer subjectId, String parent);

    /**
     * 根据名称模糊查询
     * @param topicName
     * @return
     */
    public List<TopicVo> getByLikeName(String topicName);
}
