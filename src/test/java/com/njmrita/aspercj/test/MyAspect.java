package com.njmrita.aspercj.test;

import com.njmsita.exam.authentic.controller.TeacherController;
import com.njmsita.exam.authentic.dao.dao.StudentDao;
import com.njmsita.exam.authentic.model.TeacherVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MyAspect
{
    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherController teacherController;
    @Test
    public void fun1(){
        TeacherVo teacherVo= new TeacherVo();
        teacherVo.setTeacherId("0001");
        teacherVo.setPassword("123");
        teacherController.resetPassword(teacherVo);

    }
}
