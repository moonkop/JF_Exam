package com.njmsita.exam.authentic.service.ebo;

import com.njmsita.exam.authentic.dao.dao.TresourceDao;
import com.njmsita.exam.authentic.model.TresourceModel;
import com.njmsita.exam.authentic.service.ebi.TresourceEbi;
import com.njmsita.exam.base.BaseQueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
@Service
public class TresourceEbo implements TresourceEbi
{
    @Autowired
    private TresourceDao tresourceDao;

    public void save(TresourceModel tresourceModel)
    {
        tresourceDao.save(tresourceModel);
    }

    public void update(TresourceModel tresourceModel)
    {
        tresourceDao.update(tresourceModel);
    }

    public void delete(TresourceModel tresourceModel)
    {
        tresourceDao.delete(tresourceModel);
    }

    public List<TresourceModel> getAll()
    {
        return tresourceDao.getAll();
    }

    public TresourceModel get(Serializable uuid)
    {
        return tresourceDao.get(uuid);
    }

    public List<TresourceModel> getAll(BaseQueryModel qm, Integer pageNum, Integer pageCount)
    {
        return tresourceDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryModel qm)
    {
        return tresourceDao.getCount(qm);
    }
}
