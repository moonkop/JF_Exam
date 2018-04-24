package com.njmsita.exam.manager.dao.dao;

import com.njmsita.exam.manager.model.SchoolVo;
import com.njmsita.exam.base.BaseDao;

/**
 * 学校持久层接口
 */
public interface SchoolDao extends BaseDao<SchoolVo>
{

    public void delete(SchoolVo school);

    public SchoolVo findByName(String name);
}
