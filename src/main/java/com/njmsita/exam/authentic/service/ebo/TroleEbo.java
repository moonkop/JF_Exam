package com.njmsita.exam.authentic.service.ebo;

import com.njmsita.exam.authentic.dao.dao.TroleDao;
import com.njmsita.exam.authentic.model.TroleModel;
import com.njmsita.exam.authentic.service.ebi.TroleEbi;
import com.njmsita.exam.base.BaseQueryModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class TroleEbo implements TroleEbi
{
    @Autowired
    private TroleDao troleDao;

    public void save(TroleModel troleModel)
    {
        troleDao.save(troleModel);
    }

    public void update(TroleModel troleModel)
    {
        troleDao.update(troleModel);
    }

    public void delete(TroleModel troleModel)
    {
        troleDao.delete(troleModel);
    }

    public List<TroleModel> getAll()
    {
        return troleDao.getAll();
    }

    public TroleModel get(Serializable uuid)
    {
        return troleDao.get(uuid);
    }

    public List<TroleModel> getAll(BaseQueryModel qm, Integer pageNum, Integer pageCount)
    {
        return troleDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryModel qm)
    {
        return troleDao.getCount(qm);
    }
}
