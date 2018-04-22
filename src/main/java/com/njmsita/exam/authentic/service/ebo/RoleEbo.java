package com.njmsita.exam.authentic.service.ebo;

import com.njmsita.exam.authentic.dao.dao.RoleDao;
import com.njmsita.exam.authentic.dao.dao.StudentDao;
import com.njmsita.exam.authentic.dao.dao.TeacherDao;
import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.authentic.model.TroleVo;
import com.njmsita.exam.authentic.service.ebi.RoleEbi;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.model.SchoolVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

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
        return roleDao.getAll(qm,pageNum,pageSize);
    }

    public Integer getCount(BaseQueryVO qm)
    {
        return roleDao.getCount(qm);
    }

    public void update(TroleVo troleVo)
    {
        roleDao.update(troleVo);

    }

    public void delete(TroleVo roleVo)
    {
        List<TeacherVo> teachers=teacherDao.getAllByRoleId(roleVo.getId());
        List<TeacherVo> students=studentDao.getAllByRoleId(roleVo.getId());
        if(0==teachers.size()&&students.size()==0){
            roleDao.delete(roleVo);
        }else{
            //TODO 抛出异常
            System.out.println("这个角色有关联的用户，不能删除");
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
}
