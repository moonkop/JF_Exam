package com.njmsita.exam.authentic.service.ebi;

import com.njmsita.exam.authentic.model.TroleVo;
import com.njmsita.exam.base.BaseEbi;

/**
 * 角色业务层接口
 */
public interface RoleEbi extends BaseEbi<TroleVo>
{
    /**
     * 删除
     * @param roleVo    要删除的角色
     */
    public void delete(TroleVo roleVo);

    /**
     * 根据名称查询角色
     * @param name      角色名称
     * @return          查询到的角色
     */
    public TroleVo getByName(String name);
}
