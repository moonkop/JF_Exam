package com.njmsita.exam.manager.dao.dao;

import com.njmsita.exam.base.BaseDao;
import com.njmsita.exam.manager.model.ClassroomVo;
import com.njmsita.exam.manager.model.SchoolVo;

/**
 * 班级持久层接口
 */
public interface ClassroomDao extends BaseDao<ClassroomVo>
{

    public void delete(ClassroomVo classroomVo);
}
