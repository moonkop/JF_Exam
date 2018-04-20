package com.njmsita.exam.authentic.service.ebi;

import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.base.BaseEbi;


public interface TeacherEbi extends BaseEbi<TeacherVo>
{
    public TeacherVo login(String teacherId, String password, String loginIp);
}
