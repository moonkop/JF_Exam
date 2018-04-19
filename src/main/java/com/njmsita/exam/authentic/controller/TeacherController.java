package com.njmsita.exam.authentic.controller;

import com.njmsita.exam.authentic.model.TeacherModel;
import com.njmsita.exam.authentic.service.ebi.TeacherEbi;
import com.njmsita.exam.authentic.service.ebi.TresourceEbi;
import com.njmsita.exam.authentic.service.ebi.TroleEbi;
import com.njmsita.exam.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@Scope("prototype")
@RequestMapping("/teacherController")
public class TeacherController extends BaseController
{
    @Autowired
    private TeacherEbi teacherEbi;

    @Autowired
    private TroleEbi troleEbi;

    @Autowired
    private TresourceEbi tresourceEbi;

    @RequestMapping("/login")
    public String login(TeacherModel login, HttpSession session){
        System.out.println(login.getTeacher_id()+""+login.getPassword());
        return "redirect:dist/pages/index_teacher";
    }
}
