package com.njmsita.exam.authentic.controller;

import com.njmsita.exam.authentic.model.StudentModel;
import com.njmsita.exam.authentic.service.ebi.StudentEbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StudentController
{
    @Autowired
    private StudentEbi studentEbi;

}
