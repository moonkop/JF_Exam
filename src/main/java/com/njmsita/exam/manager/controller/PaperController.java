package com.njmsita.exam.manager.controller;

import com.njmsita.exam.manager.service.ebi.PaperEbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PaperController
{
    @Autowired
    private PaperEbi paperEbi;
}
