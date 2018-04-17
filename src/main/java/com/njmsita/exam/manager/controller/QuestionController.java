package com.njmsita.exam.manager.controller;

import com.njmsita.exam.manager.service.ebi.QuestionEbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class QuestionController
{
    @Autowired
    private QuestionEbi questionEbi;
}
