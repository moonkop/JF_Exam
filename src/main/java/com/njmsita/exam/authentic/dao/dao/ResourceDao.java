package com.njmsita.exam.authentic.dao.dao;

import com.njmsita.exam.authentic.model.TresourceVo;
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

    /**
     * 根据名称或url进行查找
     * @param name      名称
     * @param url       路径
     * @return
     */
    public TresourceVo getByNameOrUrl(String name, String url);
}
