package com.njmsita.exam.authentic.dao.dao;

import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.base.BaseDao;

import java.util.List;

/**
 * 教师持久层接口
 */
public interface TeacherDao extends BaseDao<TeacherVo>
{
    /**
     * 根据教师Id和密码查询用户
     * @param teacherId     教师id
     * @param password      密码
     * @return
     */
    public TeacherVo getTeaByTeaIdAndPwd(String teacherId, String password);

    /**
     * 根据角色Id获取教师
     * @param id            角色id
     * @return              查询到的所有教师
     */
    public List<TeacherVo> getAllByRoleId(String id);
}
