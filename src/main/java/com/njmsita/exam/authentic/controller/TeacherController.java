package com.njmsita.exam.authentic.controller;

import com.njmsita.exam.authentic.service.ebi.TeacherEbi;
import com.njmsita.exam.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TeacherController extends BaseController
{
    @Autowired
    private TeacherEbi teacherEbi;
}
