package com.njmsita.exam.authentic.service.ebo;

import com.njmsita.exam.authentic.dao.dao.ResourceDao;
import com.njmsita.exam.authentic.dao.dao.RoleDao;
import com.njmsita.exam.authentic.dao.dao.StudentDao;
import com.njmsita.exam.authentic.dao.dao.TeacherDao;
import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.authentic.model.TresourceVo;
import com.njmsita.exam.authentic.model.TroleVo;
import com.njmsita.exam.authentic.service.ebi.RoleEbi;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.utils.exception.OperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 角色业务层实现类
 */
@Service
@Transactional
public class RoleEbo implements RoleEbi
{
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ResourceDao resourceDao;

    /**
     * 废弃
     *
     * @param troleVo
     */
    public void save(TroleVo troleVo)
    {
        roleDao.save(troleVo);
    }

    public List<TroleVo> getAll()
    {
        return roleDao.getAll();
    }

    public TroleVo get(Serializable uuid)
    {
        return roleDao.get(uuid);
    }

    public List<TroleVo> getAll(BaseQueryVO qm, Integer pageNum, Integer pageSize)
    {
        return roleDao.getAll(qm, pageNum, pageSize);
    }

    public Integer getCount(BaseQueryVO qm)
    {
        return roleDao.getCount(qm);
    }

    /**
     * 废弃
     *
     * @param troleVo
     */
    public void update(TroleVo troleVo)
    {
        roleDao.update(troleVo);

    }

    public void delete(TroleVo roleVo) throws OperationException
    {
        List<TeacherVo> teachers = teacherDao.getAllByRoleId(roleVo.getId());
        List<TeacherVo> students = studentDao.getAllByRoleId(roleVo.getId());
        if (0 == teachers.size() && students.size() == 0)
        {
            roleDao.delete(roleVo);
        } else
        {
            throw new OperationException("该角色有关联的用户，不能删除");
        }

    }
    //----------------------------------------------以上为基本方法------------------------------------------
    //----------------------------------------------以上为基本方法------------------------------------------
    //----------------------------------------------以上为基本方法------------------------------------------
    //----------------------------------------------以上为基本方法------------------------------------------
    //----------------------------------------------以上为基本方法------------------------------------------
    //----------------------------------------------以上为基本方法------------------------------------------

    public TroleVo getByName(String name)
    {
        return roleDao.getByName(name);
    }

    public void save(TroleVo troleVo, String[] resourceIds) throws OperationException
    {
        if (null != roleDao.getByName(troleVo.getName()))
        {
            throw new OperationException("对不起，当前角色:" + troleVo.getName() + "已存在。请勿重复操作！");
        }
        bindingReses(troleVo, resourceIds);
        roleDao.save(troleVo);
    }

    public void update(TroleVo troleVo, String[] resourceIds) throws OperationException
    {
        if (null !=roleDao.getByName(troleVo.getName())&&
        !roleDao.getByName(troleVo.getName()).getId().equals(troleVo.getId())
                )
        {throw new OperationException("对不起，当前角色已存在。请勿重复操作！");

        }
        TroleVo troleVoRaw = roleDao.get(troleVo.getId());
        bindingReses(troleVoRaw, resourceIds);
        // roleDao.update(troleVo);
        troleVoRaw.setName(troleVo.getName());
        troleVoRaw.setRemark(troleVo.getRemark());
        troleVoRaw.setSeq(troleVo.getSeq());

    }

    private void bindingReses(TroleVo troleVo, String[] resourceIds) throws OperationException
    {
        Set<TresourceVo> resourceSet = new HashSet<>();
        for (String resourceId : resourceIds)
        {
            TresourceVo temp = resourceDao.get(resourceId);
            if (temp == null)
            {
                throw new OperationException("您所选的资源中有部分不存在，请勿非法操作");
            }
            resourceSet.add(temp);
        }
        troleVo.setReses(resourceSet);
    }
}
