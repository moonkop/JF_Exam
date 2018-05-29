package com.njmsita.exam.authentic.dao.impl;

import com.njmsita.exam.authentic.dao.dao.StudentDao;
import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.authentic.model.querymodel.StudentQueryModel;
import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 学生持久层实现类
 */
@Repository
public class StudentDaoImpl extends BaseImpl<StudentVo> implements StudentDao
{

    public DetachedCriteria doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
        StudentQueryModel sqm= (StudentQueryModel) qm;
        return dc;
    }

    public List<StudentVo> getAllBySchoolId(String id)
    {
        //student---->school
        String hql="from StudentVo sv where sv.school.id=?";
        return (List<StudentVo>) this.getHibernateTemplate().find(hql,id);
    }

    public List<TeacherVo> getAllByRoleId(String id)
    {
        String hql="from StudentVo sv where sv.role.id=?";
        return (List<TeacherVo>) this.getHibernateTemplate().find(hql,id);
    }

    public StudentVo getTeaByStuIdAndPwdFromSchool(String studentId, String password, String schoolId)
    {
        String hql="from StudentVo where studentId=? and password=? and school.id=?";

        List<StudentVo> list = (List<StudentVo>) this.getHibernateTemplate().find(hql,studentId,password,schoolId);
        return list.size()>0? list.get(0) :null;
    }

    public List<StudentVo> getAllByClassroomId(String id)
    {
        //student---->school
        String hql="from StudentVo sv where sv.classroom.id=?";
        return (List<StudentVo>) this.getHibernateTemplate().find(hql,id);
    }

    public StudentVo getByStudentIdFromSchool(String studentId, String schoolId)
    {
        String hql="from StudentVo where studentId=? and school.id=?";
        List<StudentVo> list= (List<StudentVo>) this.getHibernateTemplate().find(hql,studentId,schoolId);
        return list.size()>0?list.get(0):null;
    }

    public void bulkInput(List<StudentVo> students)
    {
        for (StudentVo student : students)
        {
            this.getHibernateTemplate().save(student);
        }
    }

    public List<StudentVo> getByClassroom(String classroomId)
    {
        String hql="from StudentVo where classroom.id=?";
        return (List<StudentVo>) this.getHibernateTemplate().find(hql,classroomId);
    }

    @Override
    public void test()
    {
        System.out.println("=========================测试专用线=====================================");
    }

}
