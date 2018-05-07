package com.njmsita.exam.manager.service.ebi;

import com.njmsita.exam.base.BaseEbi;
import com.njmsita.exam.manager.model.SubjectVo;
import com.njmsita.exam.manager.model.TopicVo;

import java.util.List;

/**
 * 知识点业务层接口
 */
public interface TopicEbi extends BaseEbi<TopicVo>
{
    public List<TopicVo> getTopicBySubject(SubjectVo subjectVo);

    public int getTopicConutBySubject();

}
