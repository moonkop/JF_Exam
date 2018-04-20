package com.njmsita.exam.authentic.dao.dao;

import com.njmsita.exam.authentic.model.StudentEntity;
import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.base.BaseDao;

public interface TeacherDao extends BaseDao<TeacherVo>
{
    public TeacherVo getTeaByTeaIdAndPwd(String teacherId, String password);
}
