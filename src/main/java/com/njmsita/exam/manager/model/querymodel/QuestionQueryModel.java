package com.njmsita.exam.manager.model.querymodel;

import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.model.QuestionVo;
import com.njmsita.exam.manager.model.TopicVo;

import java.util.HashSet;
import java.util.Set;

/**
 * 题目点查询条件模型
 */
public class QuestionQueryModel extends QuestionVo implements BaseQueryVO
{
    private Boolean showMe;
    private Boolean recursive;
    private Set<String> topicIds = new HashSet<>();

    public Set<String> getTopicIds()
    {
        return topicIds;
    }

    public void setTopicIds(Set<String> topicIds)
    {
        this.topicIds = topicIds;
    }

    public Boolean getRecursive()
    {
        return recursive;
    }

    public void setRecursive(Boolean recursive)
    {
        this.recursive = recursive;
    }

    public Boolean getShowMe()
    {
        return showMe;
    }

    public void setShowMe(Boolean showMe)
    {
        this.showMe = showMe;
    }
}
