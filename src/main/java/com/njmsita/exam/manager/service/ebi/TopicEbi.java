package com.njmsita.exam.manager.service.ebi;

import com.njmsita.exam.base.BaseEbi;
import com.njmsita.exam.manager.model.TopicVo;
import com.njmsita.exam.utils.exception.OperationException;

import java.util.List;

/**
 * 知识点业务层接口
 */
public interface TopicEbi extends BaseEbi<TopicVo>
{

    /**
     * 根据学科以及父知识点获取知识点
     * @param subjectId 学科id
     * @param parent    父知识点
     * @return          知识点集合
     */
    public List<TopicVo> getBySubject(Integer subjectId, String parent) throws OperationException;
}
