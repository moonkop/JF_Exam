package com.njmsita.exam.authentic.service.ebi;

import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.base.BaseEbi;

/**
 * 教师业务层接口
 */
public interface TeacherEbi extends BaseEbi<TeacherVo>
{
    /**
     * 登陆
     * @param teacherId     教师ID
     * @param password      密码
     * @param loginIp       登陆ip
     * @return
     */
    public TeacherVo login(String teacherId, String password, String loginIp);
}
