package com.njmsita.exam.authentic.controller;

import com.njmsita.exam.authentic.model.StudentModel;
import com.njmsita.exam.authentic.service.ebi.StudentEbi;
import com.njmsita.exam.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
@RequestMapping("/student")
public class StudentController extends BaseController
{
    @Autowired
    private StudentEbi studentEbi;

    @RequestMapping("/login.action")
    public void login(String studentId,String password){
        System.out.println(studentId+":"+password);
    }
}
