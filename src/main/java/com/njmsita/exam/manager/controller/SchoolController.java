package com.njmsita.exam.manager.controller;

import com.njmsita.exam.manager.service.ebi.SchoolEbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SchoolController
{
    @Autowired
    private SchoolEbi schoolEbi;
}
