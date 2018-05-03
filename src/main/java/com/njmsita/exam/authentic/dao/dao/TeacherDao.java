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
     * @param roleId            角色id
     * @return              查询到的所有教师
     */
    public List<TeacherVo> getAllByRoleId(String roleId);

    /**
     * 根据教师id查找教师
     * @param teacherId     教师id
     * @return              查询到的所有教师
     */
    public TeacherVo getByTeacherId(String teacherId);

    /**
     * 批量导入教师信息
     * @param teachers
     */
    public void bulkInput(List<TeacherVo> teachers);
}
