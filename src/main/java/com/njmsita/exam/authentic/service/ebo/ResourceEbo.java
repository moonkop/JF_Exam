package com.njmsita.exam.authentic.service.ebo;

import com.njmsita.exam.authentic.dao.dao.ResourceDao;
import com.njmsita.exam.authentic.model.TresourceVo;
import com.njmsita.exam.authentic.service.ebi.ResourceEbi;
import com.njmsita.exam.base.BaseQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 资源业务层实现类
 */
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


    public List<TresourceVo> getAll()
    {
        return resourceDao.getAll();
    }

    public TresourceVo get(Serializable uuid)
    {
        return resourceDao.get(uuid);
    }

    public List<TresourceVo> getAll(BaseQueryVO qm)
    {
        return resourceDao.getAll(qm);
    }

    public Integer getCount(BaseQueryVO qm)
    {
        return resourceDao.getCount(qm);
    }

    public void update(TresourceVo tresourceVo)
    {
        resourceDao.update(tresourceVo);

    }


    //------------------------------以上为基本方法--------------------------------------------
    //------------------------------以上为基本方法--------------------------------------------
    //------------------------------以上为基本方法--------------------------------------------
    //------------------------------以上为基本方法--------------------------------------------
    //------------------------------以上为基本方法--------------------------------------------


    public List<TresourceVo> getAllByLogin(String id)
    {
        return resourceDao.getAllByLoginId(id);
    }
}
