package com.njmsita.exam.authentic.service.ebo;

import com.njmsita.exam.authentic.dao.dao.ResourcetypeDao;
import com.njmsita.exam.authentic.model.TresourcetypeVo;
import com.njmsita.exam.authentic.service.ebi.ResourcetypeEbi;
import com.njmsita.exam.base.BaseQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 资源类型业务层实现类
 */
@Service
@Transactional
public class ResourcetypeEbo implements ResourcetypeEbi
{
    @Autowired
    private ResourcetypeDao resourcetypeDao;

    public void save(TresourcetypeVo resourcetypeVo)
    {

        resourcetypeDao.save(resourcetypeVo);

    }


    public List<TresourcetypeVo> getAll()
    {
        return resourcetypeDao.getAll();
    }

    public TresourcetypeVo get(Serializable uuid)
    {
        return resourcetypeDao.get(uuid);
    }


    public List<TresourcetypeVo> getAll(BaseQueryVO qm, Integer pageNum, Integer pageSize)
    {
        return resourcetypeDao.getAll(qm,pageNum,pageSize);
    }

    public Integer getCount(BaseQueryVO qm)
    {
        return resourcetypeDao.getCount(qm);
    }

    public void update(TresourcetypeVo resourcetypeVo)
    {
        resourcetypeDao.update(resourcetypeVo);

    }

    public void delete(TresourcetypeVo resourcetypeVo)
    {
        resourcetypeDao.delete(resourcetypeVo);
    }


    //------------------------------以上为基本方法--------------------------------------------
    //------------------------------以上为基本方法--------------------------------------------
    //------------------------------以上为基本方法--------------------------------------------
    //------------------------------以上为基本方法--------------------------------------------
    //------------------------------以上为基本方法--------------------------------------------
}
