package com.njmsita.exam.authentic.dao.dao;

import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.base.BaseDao;

import java.util.List;

/**
 * 学生持久层接口
 */
public interface StudentDao extends BaseDao<StudentVo>
{
    /**
     * 根据学校id获取学生
     * @param id    学校id
     * @return      学生集合
     */
    public List<StudentVo> getAllBySchoolId(String id);


    /**
     * 根据角色id获取所有学生
     * @param id    角色id
     * @return      查询到的所有学生
     */
    public List<TeacherVo> getAllByRoleId(String id);

    /**
     * 根据学校id  学生id  密码查询学生
     * @param studentId         验证学生id
     * @param password          验证密码
     * @param schoolId          学校id
     * @return
     */
    public StudentVo getTeaByStuIdAndPwdFromSchool(String studentId, String password, String schoolId);

    /**
     * 跟据班级id查询学生
     * @param id       班级id
     * @return
     */
    public List<StudentVo> getAllByClassroomId(String id);


    /**
     * 根据学生id 学校id  查找学生
     * @param studentId     学生id
     * @param schoolId      学校id
     * @return              查询到的所有学生
     */
    public StudentVo getByStudentIdToSchool(String studentId, String schoolId);

    /**
     * 批量导入学生信息
     * @param students      学生信息集合
     */
    public void bulkInput(List<StudentVo> students);
}
