package com.njmsita.exam.manager.controller;

import com.njmsita.exam.manager.service.ebi.ClassroomEbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ClassroomController
{
    @Autowired
    private ClassroomEbi classroomEbi;
}
