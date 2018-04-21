package com.njmsita.exam.authentic.dao.dao;

import com.njmsita.exam.authentic.model.SchoolVo;
import com.njmsita.exam.authentic.model.TresourceVo;
import com.njmsita.exam.base.BaseDao;

import java.util.List;

/**
 * 学校持久层接口
 */
public interface SchoolDao extends BaseDao<SchoolVo>
{
    public void save1(SchoolVo school);
}
