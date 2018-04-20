package com.njmsita.exam.authentic.dao.impl;

import com.njmsita.exam.authentic.dao.dao.RoleDao;
import com.njmsita.exam.authentic.dao.dao.TeaDao;
import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.authentic.model.TroleVo;
import com.njmsita.exam.authentic.model.querymodel.TeaQueryModel;
import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryModel;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl extends BaseImpl<TroleVo> implements RoleDao
{

    public void doQbc(Criteria dc, BaseQueryModel qm)
    {
    }

}
