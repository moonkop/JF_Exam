package com.njmsita.exam.authentic.dao.dao;

import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.base.BaseDao;

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

}
