package com.njmsita.exam.authentic.dao.dao;

import com.njmsita.exam.authentic.model.TresourceModel;
import com.njmsita.exam.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TresourceDao extends BaseDao<TresourceModel>
{
    public List<TresourceModel> getAllByLoginTea(String id);
}
