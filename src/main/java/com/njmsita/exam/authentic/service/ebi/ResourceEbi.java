package com.njmsita.exam.authentic.service.ebi;

import com.njmsita.exam.authentic.model.TresourceVo;
import com.njmsita.exam.authentic.model.TroleVo;
import com.njmsita.exam.base.BaseEbi;

import java.util.List;


/**
 * 资源业务层接口
 */
public interface ResourceEbi extends BaseEbi<TresourceVo>
{
    /**
     * 获取登陆用户的所有资源
     * @param id    登陆用户的id
     * @return
     */
    public List<TresourceVo> getAllByLogin(String id);

    /**
     * 根据角色获取菜单
     * @param id
     * @return
     */
    public List<TresourceVo> getMenuByRole(String id);
}
