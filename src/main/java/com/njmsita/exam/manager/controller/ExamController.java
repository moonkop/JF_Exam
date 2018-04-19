package com.njmsita.exam.manager.controller;

import com.njmsita.exam.base.BaseController;
import com.njmsita.exam.manager.service.ebi.ExamEbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class ExamController extends BaseController
{
    @Autowired
    private ExamEbi examEbi;
}
