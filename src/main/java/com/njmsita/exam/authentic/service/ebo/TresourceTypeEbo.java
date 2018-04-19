package com.njmsita.exam.authentic.service.ebo;

import com.njmsita.exam.authentic.dao.dao.TresourceTypeDao;
import com.njmsita.exam.authentic.model.TresourceTypeModel;
import com.njmsita.exam.authentic.service.ebi.TresourceTypeEbi;
import com.njmsita.exam.base.BaseQueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
@Service
public class TresourceTypeEbo implements TresourceTypeEbi
{
    @Autowired
    private TresourceTypeDao tresourceTypeDao;

    public void save(TresourceTypeModel tresourceTypeModel)
    {
        tresourceTypeDao.save(tresourceTypeModel);
    }

    public void update(TresourceTypeModel tresourceTypeModel)
    {
        tresourceTypeDao.update(tresourceTypeModel);
    }

    public void delete(TresourceTypeModel tresourceTypeModel)
    {
        tresourceTypeDao.delete(tresourceTypeModel);
    }

    public List<TresourceTypeModel> getAll()
    {
        return tresourceTypeDao.getAll();
    }

    public TresourceTypeModel get(Serializable uuid)
    {
        return tresourceTypeDao.get(uuid);
    }

    public List<TresourceTypeModel> getAll(BaseQueryModel qm, Integer pageNum, Integer pageCount)
    {
        return tresourceTypeDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryModel qm)
    {
        return tresourceTypeDao.getCount(qm);
    }
}
