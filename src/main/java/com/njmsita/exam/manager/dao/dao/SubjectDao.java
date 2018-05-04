package com.njmsita.exam.manager.dao.dao;

import com.njmsita.exam.base.BaseDao;
import com.njmsita.exam.manager.model.LogVo;
import com.njmsita.exam.manager.model.SubjectVo;
import com.njmsita.exam.manager.model.querymodel.LogQueryModel;

import java.util.List;

/**
 * 学科持久层接口
 */
public interface SubjectDao extends BaseDao<SubjectVo>
{
    /**
     * 根据名称查询科目
     * @param name  科目名称
     * @return
     */
    public SubjectVo getByName(String name);
}
