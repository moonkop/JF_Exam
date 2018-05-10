package com.njmsita.exam.manager.model.querymodel;

import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.model.QuestionVo;
import com.njmsita.exam.manager.model.TopicVo;

/**
 * 题目点查询条件模型
 */
public class QuestionQueryModel extends QuestionVo implements BaseQueryVO
{
    private Boolean showMe;

    public Boolean getShowMe()
    {
        return showMe;
    }

    public void setShowMe(Boolean showMe)
    {
        this.showMe = showMe;
    }
}
