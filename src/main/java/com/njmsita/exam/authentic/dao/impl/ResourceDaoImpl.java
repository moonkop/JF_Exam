package com.njmsita.exam.authentic.dao.impl;

import com.njmsita.exam.authentic.dao.dao.ResourceDao;
import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.authentic.model.TresourceVo;
import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.utils.consts.SysConsts;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 资源持久层实现类
 */
@Repository
public class ResourceDaoImpl extends BaseImpl<TresourceVo> implements ResourceDao
{

    public DetachedCriteria doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
return dc;
    }

    public List<TresourceVo> getAllByLoginId(String id)
    {
        //查询逻辑：teacher--->role---->resource
        String hql = "select res from TeacherVo tv join tv.role rv join rv.reses res where tv.id=? ";

        return (List<TresourceVo>) getEvictObjects(this.getHibernateTemplate().find(hql, id));
    }

    public List<TresourceVo> getAllByLoginId_stu(String id)
    {
        //查询逻辑：student--->role---->resource
        //学生
        String hql = "select res from StudentVo tv join tv.role rv join rv.reses res where tv.id=? ";
        return (List<TresourceVo>) getEvictObjects(this.getHibernateTemplate().find(hql, id));
    }

    public List<TresourceVo> getByUrl(String url)
    {
        String hql = "from TresourceVo where url=?";
        List<TresourceVo> list = getEvictObjects((List<TresourceVo>) this.getHibernateTemplate().find(hql, url));
        return list;
    }

    public List<TresourceVo> getAllOrderBySeq()
    {
        String hql = "from TresourceVo order by seq";
        List<TresourceVo> list = getEvictObjects((List<TresourceVo>) this.getHibernateTemplate().find(hql));

        return list;
    }

    public List<TresourceVo> getMenuByRole(String roleId)
    {
        String hql = "from TresourceVo rv left outer join fetch rv.roles rov  where rv.parent.id=? and rov.id=? order by rv.seq";
        List<TresourceVo> list =(List<TresourceVo>) this.getHibernateTemplate().find(hql, SysConsts.SYSTEM_MENU_ID, roleId);
        list = getEvictObjects(list);
        return list;
    }

    public <T> List<T> getEvictObjects(List<T> list)
    {
        for (T object : list)
        {
            this.getHibernateTemplate().evict(object);
        }
        return list;
    }
}
