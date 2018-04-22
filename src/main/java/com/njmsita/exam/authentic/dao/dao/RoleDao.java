package com.njmsita.exam.authentic.dao.dao;

import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.authentic.model.TroleVo;
import com.njmsita.exam.base.BaseDao;

import java.util.List;

/**
 * 角色持久层接口
 */
public interface RoleDao extends BaseDao<TroleVo>
{
    /**
     * 删除
     * @param roleVo    需要删除的角色
     */
    public void delete(TroleVo roleVo);

    /**
     * 根据名称查询角色
     * @param name      角色名称
     * @return          查询到的角色
     */
    public TroleVo getByName(String name);

}
