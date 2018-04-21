package com.njmsita.exam.authentic.service.ebi;

import com.njmsita.exam.authentic.model.TresourceVo;
import com.njmsita.exam.authentic.model.TroleVo;
import com.njmsita.exam.base.BaseEbi;

import java.util.List;


public interface ResourceEbi extends BaseEbi<TresourceVo>
{
    public List<TresourceVo> getAllByLogin(String id);
}
