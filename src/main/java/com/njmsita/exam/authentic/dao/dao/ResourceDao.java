package com.njmsita.exam.authentic.dao.dao;

import com.njmsita.exam.authentic.model.TresourceVo;
import com.njmsita.exam.authentic.model.TroleVo;
import com.njmsita.exam.base.BaseDao;

import java.util.List;

public interface ResourceDao extends BaseDao<TresourceVo>
{
    public List<TresourceVo> getAllByLoginId(String id);
}
