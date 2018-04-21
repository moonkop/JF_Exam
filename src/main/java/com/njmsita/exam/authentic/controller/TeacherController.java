package com.njmsita.exam.authentic.controller;

import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.authentic.model.TresourceVo;
import com.njmsita.exam.authentic.service.ebi.ResourceEbi;
import com.njmsita.exam.authentic.service.ebi.RoleEbi;
import com.njmsita.exam.authentic.service.ebi.TeacherEbi;
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
@RequestMapping("/teacher")
public class TeacherController
{
    @Autowired
    private TeacherEbi teaEbi;

    @Autowired
    private RoleEbi roleEbi;

    @Autowired
    private ResourceEbi resourceEbi;

    @RequestMapping("toLogin")
    public String toLogin(){
        return "login_teacher";
    }

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

        //验证用户名密码
        TeacherVo loginTea=teaEbi.login(teaVo.getTeacherId(),teaVo.getPassword(),loginIp);
        if(loginTea!=null){
            //用户名密码验证成功获取当前登录人的所有权限
            List<TresourceVo> teacherResources = resourceEbi.getAllByLogin(loginTea.getId());
            StringBuilder sbd=new StringBuilder();
            for (TresourceVo resource : teacherResources)
            {
                sbd.append(resource.getUrl());
                sbd.append(",");
            }
            loginTea.setTeacherRes(sbd.toString());
            session.setAttribute(SysConsts.TEACHER_LOGIN_TEACHER_OBJECT_NAME,loginTea);
            return "index_teacher";
        }
        request.setAttribute("msg","账号或密码不正确！！");
        return "teacher/login_teacher";
    }
}
