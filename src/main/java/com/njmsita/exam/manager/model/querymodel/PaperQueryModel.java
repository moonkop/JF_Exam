package com.njmsita.exam.manager.model.querymodel;

import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.model.PaperVo;
import com.njmsita.exam.manager.model.TopicVo;

/**
 * 试卷查询条件模型
 */
public class PaperQueryModel extends PaperVo implements BaseQueryVO
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
