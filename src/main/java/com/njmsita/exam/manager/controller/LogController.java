package com.njmsita.exam.manager.controller;

import com.njmsita.exam.manager.service.ebi.LogEbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LogController
{
    @Autowired
    private LogEbi logEbi;
}
