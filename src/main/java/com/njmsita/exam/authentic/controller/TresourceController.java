package com.njmsita.exam.authentic.controller;

import com.njmsita.exam.authentic.service.ebi.TresourceEbi;
import com.njmsita.exam.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class TresourceController extends BaseController
{
    @Autowired
    private TresourceEbi tresourceEbi;
}
