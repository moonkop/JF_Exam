package com.njmsita.exam.authentic.controller;

import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.authentic.service.ebi.RoleEbi;
import com.njmsita.exam.authentic.service.ebi.TeaEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Scope("prototype")
@RequestMapping("/tea")
public class TeaController
{
    @Autowired
    private TeaEbi teaEbi;

    @Autowired
    private RoleEbi roleEbi;

    @RequestMapping("login")
    public String login(TeacherVo teaVo, HttpServletRequest request, HttpSession session){
        System.out.println(teaVo.getTeacherId()+":"+teaVo.getPassword());
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
        TeacherVo loginTea=teaEbi.login(teaVo.getTeacherId(),teaVo.getPassword(),loginIp);
        if(loginTea!=null){
//            List<ResModel> resModels = resEbi.getAllByEmp(loginEmp.getUuid());
//            StringBuilder sbd=new StringBuilder();
//            for (ResModel resModel : resModels) {
//                sbd.append(resModel.getUrl());
//                sbd.append(",");
//            }
//            loginEmp.setLogReString(sbd.toString());
            session.setAttribute(SysConsts.TEA_LOGIN_TEACHER_OBJECT_NAME,loginTea);
            return "index_teacher";
        }
        request.setAttribute("msg","账号或密码不正确！！");
        return "teacher/login_teacher";
    }
}
