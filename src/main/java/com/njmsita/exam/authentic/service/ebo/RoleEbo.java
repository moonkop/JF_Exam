package com.njmsita.exam.authentic.service.ebo;

import com.njmsita.exam.authentic.dao.dao.RoleDao;
import com.njmsita.exam.authentic.model.TroleVo;
import com.njmsita.exam.authentic.service.ebi.RoleEbi;
import com.njmsita.exam.base.BaseQueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class RoleEbo implements RoleEbi
{
    @Autowired
    private RoleDao roleDao;

    public void save(TroleVo troleVo)
    {
        roleDao.save(troleVo);
    }

    public void update(TroleVo troleVo)
    {
        roleDao.update(troleVo);
    }

    public void delete(TroleVo troleVo)
    {
        roleDao.delete(troleVo);
    }

    public List<TroleVo> getAll()
    {
        return roleDao.getAll();
    }

    public TroleVo get(Serializable uuid)
    {
        return roleDao.get(uuid);
    }

    public List<TroleVo> getAll(BaseQueryModel qm, Integer pageNum, Integer pageCount)
    {
        return roleDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryModel qm)
    {
        return roleDao.getCount(qm);
    }
}
