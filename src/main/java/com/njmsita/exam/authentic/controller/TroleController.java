package com.njmsita.exam.authentic.controller;

import com.njmsita.exam.authentic.service.ebi.TroleEbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TroleController
{
    @Autowired
    private TroleEbi troleEbi;
}
