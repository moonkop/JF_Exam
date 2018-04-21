package com.njmsita.exam.authentic.dao.dao;

import com.njmsita.exam.authentic.model.TresourceVo;
import com.njmsita.exam.authentic.model.TroleVo;
import com.njmsita.exam.base.BaseDao;

import java.util.List;

/**
 * 资源持久层接口
 */
public interface ResourceDao extends BaseDao<TresourceVo>
{
    /**
     * 跟据登陆用户Id获取其所拥有的资源
     * @param id    用户id
     * @return
     */
    public List<TresourceVo> getAllByLoginId(String id);
}
