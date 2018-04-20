package com.njmsita.exam.authentic.controller;

import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.authentic.service.ebi.TeaEbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Scope("prototype")
@RequestMapping("/tea")
public class TeaController
{
    @Autowired
    private TeaEbi teaEbi;

    @RequestMapping("login")
    public String login(TeacherVo teaVo){
        System.out.println(teaVo.getTeacherId()+":"+teaVo.getPassword());

        return "index_teacher";
    }
}
