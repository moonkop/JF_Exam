package com.njmsita.exam.manager.controller;

import com.njmsita.exam.base.BaseController;
import com.njmsita.exam.manager.service.ebi.QuestionTypeEbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class QuestionTypeController extends BaseController
{
    @Autowired
    private QuestionTypeEbi questionTypeEbi;
}
