package com.njmsita.exam.manager.controller;

import com.njmsita.exam.base.BaseController;
import com.njmsita.exam.manager.dao.dao.SubjectDao;
import com.njmsita.exam.manager.service.ebi.SubjectEbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class SubjectController extends BaseController
{
    @Autowired
    private SubjectEbi subjectEbi;
}
