package com.njmsita.exam.authentic.service.ebo;

import com.njmsita.exam.authentic.dao.dao.ResourceDao;
import com.njmsita.exam.authentic.dao.dao.ResourcetypeDao;
import com.njmsita.exam.authentic.model.TresourceVo;
import com.njmsita.exam.authentic.model.TresourcetypeVo;
import com.njmsita.exam.authentic.service.ebi.ResourceEbi;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.utils.exception.OperationException;
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
    @Autowired
    private ResourcetypeDao resourcetypeDao;

    public void save(TresourceVo tresourceVo) throws OperationException
    {
        infoValidate(tresourceVo);
        if(resourceDao.getByNameOrUrl(tresourceVo.getName(),tresourceVo.getUrl()).size()>0){
            throw new OperationException("资源名称或URL已存在，请核对后重试");
        }
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


    public List<TresourceVo> getAll(BaseQueryVO qm, Integer pageNum, Integer pageSize)
    {
        return resourceDao.getAll(qm,pageNum,pageSize);
    }

    public Integer getCount(BaseQueryVO qm)
    {
        return resourceDao.getCount(qm);
    }

    public void update(TresourceVo tresourceVo) throws OperationException
    {
        infoValidate(tresourceVo);
        if(resourceDao.getByNameOrUrl(tresourceVo.getName(),tresourceVo.getUrl()).size()>1){
            throw new OperationException("资源名称或URL已存在，请核对后重试");
        }
        resourceDao.update(tresourceVo);

    }

    public void delete(TresourceVo tresourceVo)
    {
        resourceDao.delete(tresourceVo);
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

    /**
     * 信息校验
     * @param tresourceVo       前台信息
     * @throws OperationException
     */
    private void infoValidate(TresourceVo tresourceVo) throws OperationException
    {
        if(tresourceVo.getResourcetype()==null){
            throw new OperationException("选择资源类型不能为空，请不要进行非法操作！");
        }
        if(tresourceVo.getParent()==null){
            throw new OperationException("选择父资源不能为空，请不要进行非法操作！");
        }
        if(tresourceVo.getParent().getId()==null||"".equals(tresourceVo.getParent().getId())){
            throw new OperationException("父资源不能为空，请不要进行非法操作！");
        }
        TresourceVo parent=resourceDao.get(tresourceVo.getParent().getId());
        if(parent==null){
            throw new OperationException("所选父资源不存在，请不要进行非法操作！");
        }
        if(tresourceVo.getResourcetype().getId()==null||"".equals(tresourceVo.getResourcetype().getId())){
            throw new OperationException("没有选择资源类型，请不要进行非法操作！");
        }

        TresourcetypeVo tresourcetypeVo=resourcetypeDao.get(tresourceVo.getResourcetype().getId());
        if(tresourcetypeVo==null){
            throw new OperationException("该资源类型不存在，请不要进行非法操作！");
        }
        tresourceVo.setResourcetype(tresourcetypeVo);
    }
}
