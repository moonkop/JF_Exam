package com.njmsita.exam.authentic.dao.dao;

import com.njmsita.exam.authentic.model.TeacherModel;
import com.njmsita.exam.base.BaseDao;
import org.springframework.stereotype.Repository;


public interface TeacherDao extends BaseDao<TeacherModel>
{
    public TeacherModel getByUsernameAndPwd(String teacher_id, String password);
}
