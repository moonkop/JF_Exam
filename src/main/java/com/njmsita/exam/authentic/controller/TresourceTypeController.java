package com.njmsita.exam.authentic.controller;

import com.njmsita.exam.authentic.service.ebi.TresourceTypeEbi;
import com.njmsita.exam.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class TresourceTypeController extends BaseController
{
    @Autowired
    private TresourceTypeEbi tresourceTypeEbi;
}
