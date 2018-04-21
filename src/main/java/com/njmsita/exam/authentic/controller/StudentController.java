package com.njmsita.exam.authentic.controller;

import com.njmsita.exam.authentic.model.StudentEntity;
import com.njmsita.exam.authentic.service.ebi.StudentEbi;
import com.njmsita.exam.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * 学生控制器
 */
@Controller
@Scope("prototype")
@RequestMapping("/stu")
public class StudentController extends BaseController
{
    @Autowired
    private StudentEbi studentEbi;

    @RequestMapping("/login")
    public String  login(StudentEntity stuLogin){
        System.out.println(stuLogin.getStudentId()+":"+stuLogin.getPassword());
        List<StudentEntity> stuList = studentEbi.getAll();
        for (StudentEntity studentEntity : stuList)
        {
            System.out.println();
        }
        return null;
    }
}
