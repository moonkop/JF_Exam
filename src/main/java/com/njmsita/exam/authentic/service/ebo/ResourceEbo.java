package com.njmsita.exam.authentic.service.ebo;

import com.njmsita.exam.authentic.dao.dao.ResourceDao;
import com.njmsita.exam.authentic.model.TresourceVo;
import com.njmsita.exam.authentic.service.ebi.ResourceEbi;
import com.njmsita.exam.base.BaseQueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class ResourceEbo implements ResourceEbi
{
    @Autowired
    private ResourceDao resourceDao;

    public void save(TresourceVo tresourceVo)
    {
        resourceDao.save(tresourceVo);
    }

    public void update(TresourceVo tresourceVo)
    {
        resourceDao.update(tresourceVo);
    }

    public void delete(TresourceVo tresourceVo)
    {
        resourceDao.delete(tresourceVo);
    }

    public List<TresourceVo> getAll()
    {
        return resourceDao.getAll();
    }

    public TresourceVo get(Serializable uuid)
    {
        return resourceDao.get(uuid);
    }

    public List<TresourceVo> getAll(BaseQueryModel qm, Integer pageNum, Integer pageCount)
    {
        return resourceDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryModel qm)
    {
        return resourceDao.getCount(qm);
    }

    public List<TresourceVo> getAllByLogin(String id)
    {
        return resourceDao.getAllByLoginId(id);
    }
}
