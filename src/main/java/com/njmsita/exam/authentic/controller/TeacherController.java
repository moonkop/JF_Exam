package com.njmsita.exam.authentic.controller;

import com.njmsita.exam.authentic.model.TeacherModel;
import com.njmsita.exam.authentic.model.TresourceModel;
import com.njmsita.exam.authentic.service.ebi.TeacherEbi;
import com.njmsita.exam.authentic.service.ebi.TresourceEbi;
import com.njmsita.exam.authentic.service.ebi.TroleEbi;
import com.njmsita.exam.base.BaseController;
import com.njmsita.exam.utils.consts.SysConsts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Scope("prototype")
@RequestMapping("/teacherController")
public class TeacherController extends BaseController
{
    @Autowired
    private TeacherEbi teacherEbi;

    @Autowired
    private TroleEbi troleEbi;

    @Autowired
    private TresourceEbi tresourceEbi;

    @RequestMapping("/login")
    public String login(TeacherModel login, HttpSession session, HttpServletRequest request){

        //获取IP地址
        String loginIp=request.getHeader("x-forwarded-for");
        if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) {
            loginIp = request.getHeader("Proxy-Client-IP");
        }
        if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) {
            loginIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) {
            loginIp = request.getRemoteAddr();
        }

        TeacherModel loginTea=teacherEbi.login(login.getTeacher_id(),login.getPassword(),loginIp);
        if(loginTea!=null){
            List<TresourceModel> resModels = tresourceEbi.getAllByLoginTea(loginTea.getId());
            StringBuilder sbd=new StringBuilder();
            for (TresourceModel resModel : resModels)
            {
                sbd.append(resModel.getUrl());
                sbd.append(",");
            }
            loginTea.setLoginRes(sbd.toString());
            session.setAttribute(SysConsts.TEA_LOGIN_TEACHER_OBJECT_NAME,loginTea);
            return "redirect:dist/pages/index_teacher";
        }
        return "dist/jsp/login_teacher";

    }
}
