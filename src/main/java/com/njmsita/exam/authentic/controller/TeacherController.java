package com.njmsita.exam.authentic.controller;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.authentic.model.TresourceVo;
import com.njmsita.exam.authentic.model.TroleVo;
import com.njmsita.exam.authentic.service.ebi.ResourceEbi;
import com.njmsita.exam.authentic.service.ebi.RoleEbi;
import com.njmsita.exam.authentic.service.ebi.TeacherEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 教师控制器
 */
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

    /**
     * 跳转登陆页
     *
     * @return
     */
    @RequestMapping("login")
    public String toLogin()
    {
        return "login_teacher";
    }

    @RequestMapping("welcome")
    public String towelcome()
    {
        return "index_teacher";
    }

    /**
     * 教师（管理员）用户登陆
     *
     * @param teaVo   前端获取教师用户登陆数据
     * @param request HttpServletRequest
     * @param session HttpSession
     * @return
     */
    @RequestMapping("login.do")
    public String login(TeacherVo teaVo, HttpServletRequest request, HttpSession session)
    {

        //获取IP地址
        String loginIp = request.getHeader("x-forwarded-for");
        if (loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp))
        {
            loginIp = request.getHeader("Proxy-Client-IP");
        }
        if (loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp))
        {
            loginIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if (loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp))
        {
            loginIp = request.getRemoteAddr();
        }

        //验证用户名密码
        TeacherVo loginTea = teaEbi.login(teaVo.getTeacherId(), teaVo.getPassword(), loginIp);

        //用户信息验证成功
        if (loginTea != null)
        {

            //用户名密码验证成功获取当前登录人的所有权限
            List<TresourceVo> teacherResources = resourceEbi.getAllByLogin(loginTea.getId());
            StringBuilder sbd = new StringBuilder();
            //拼接用户资源信息存入登陆用户
            for (TresourceVo resource : teacherResources)
            {
                sbd.append(resource.getUrl());
                sbd.append(",");
            }
            loginTea.setTeacherRes(sbd.toString());
            session.setAttribute(SysConsts.TEACHER_LOGIN_TEACHER_OBJECT_NAME, loginTea);
            Hibernate.initialize(loginTea.getTroleVo());
            return "redirect:/teacher/welcome";
        }

        //用户信息验证失败
        request.setAttribute("msg", "账号或密码不正确！！");
        return "teacher/login_teacher";
    }

    /**
     * 查看个人详细信息
     */
    @RequestMapping("detail")
    public String detail()
    {
        //前台数据从session中获取
        return "/manage/me/detail";
    }

    /**
     * 跳转个人信息编辑
     */
    @RequestMapping("edit")
    public String edit()
    {
        //回显数据在session中获取
        return "/manage/me/edit";
    }

    /**
     * 个人信息编辑
     */
    @RequestMapping("edit.do")
    public String doEdit(TeacherVo teacherQuery, HttpServletRequest request, HttpSession session)
    {
        TeacherVo teacherVo = (TeacherVo) session.getAttribute(SysConsts.TEACHER_LOGIN_TEACHER_OBJECT_NAME);
        if (null != teacherQuery)
        {
            teacherQuery.setId(teacherVo.getId());
            //不能直接进行物理更新
            TeacherVo newteacher = teaEbi.updateByLogic(teacherQuery, System.currentTimeMillis());
            //重新将数据保存到session用于修改成功后的回显
            session.setAttribute(SysConsts.TEACHER_LOGIN_TEACHER_OBJECT_NAME, newteacher);
        }
        return "redirect:/teacher/detail";
    }

    /**
     * 退出登陆
     */
    @RequestMapping("logout")
    public String loginOut(HttpSession session)
    {
        System.out.println("log out");
        System.out.println(session.getAttribute(SysConsts.TEACHER_LOGIN_TEACHER_OBJECT_NAME));
        //将session中的已登陆用户至空
        //TODO 有疑问？？？？？？？？
        session.invalidate();
        return "redirect:/teacher/login";
    }

    @RequestMapping("rolelist")
    @JsonGetter
    @ResponseBody
    public List<TroleVo> getRoleList()
    {
        return roleEbi.getAll();
    }
}
