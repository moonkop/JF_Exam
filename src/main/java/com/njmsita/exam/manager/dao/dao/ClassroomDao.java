package com.njmsita.exam.manager.dao.dao;

import com.njmsita.exam.base.BaseDao;
import com.njmsita.exam.manager.model.ClassroomVo;

import java.util.List;

/**
 * 班级持久层接口
 */
public interface ClassroomDao extends BaseDao<ClassroomVo>
{

    /**
     * 删除
     * @param classroomVo   需要删除的班级
     */
    public void delete(ClassroomVo classroomVo);

    /**
     * 根据学校id查询班级
     * @param schoolId      学校id
     * @return
     */
    public List<ClassroomVo> getAllBySchoolId(String schoolId);

    /**
     * 从指定学校中根据名称查询班级
     * @param name          班级名称
     * @param schoolId      学校id
     * @return
     */
    public ClassroomVo findByNameFrom(String name, String schoolId);

    /**
     * 根据班级id从指定学校中查找
     * @param classroomId   班级id
     * @param schoolId      学校id
     * @return
     */
    public ClassroomVo getByClassroomIdFromSchool(String classroomId, String schoolId);
}
