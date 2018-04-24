package com.njmsita.exam.manager.dao.dao;

import com.njmsita.exam.manager.model.SchoolVo;
import com.njmsita.exam.base.BaseDao;

/**
 * 学校持久层接口
 */
public interface SchoolDao extends BaseDao<SchoolVo>
{

    /**
     * 删除
     * @param school    需要删除的学校
     */
    public void delete(SchoolVo school);

    /**
     * 根据名称查找
     * @param name      学校名称
     * @return
     */
    public SchoolVo findByName(String name);
}
