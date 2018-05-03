package com.njmsita.exam.manager.dao.dao;

import com.njmsita.exam.base.BaseDao;
import com.njmsita.exam.manager.model.LogVo;
import com.njmsita.exam.manager.model.querymodel.LogQueryModel;

import java.util.List;

/**
 * 日志持久层接口
 */
public interface LogDao extends BaseDao<LogVo>
{
    /**
     * 获取指定区间内的日志记录
     * @param logQueryModel
     * @return
     */
    public List<LogVo> getAll(LogQueryModel logQueryModel);
}
