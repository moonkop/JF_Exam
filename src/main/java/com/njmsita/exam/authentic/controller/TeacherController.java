package com.njmsita.exam.authentic.controller;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.authentic.model.TresourceVo;
import com.njmsita.exam.authentic.model.TroleVo;
import com.njmsita.exam.authentic.model.querymodel.TeacherQueryModel;
import com.njmsita.exam.authentic.service.ebi.ResourceEbi;
import com.njmsita.exam.authentic.service.ebi.RoleEbi;
import com.njmsita.exam.authentic.service.ebi.TeacherEbi;
import com.njmsita.exam.base.BaseController;
import com.njmsita.exam.manager.model.SchoolVo;
import com.njmsita.exam.manager.model.querymodel.SchoolQueryVo;
import com.njmsita.exam.manager.service.ebi.SchoolEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.idutil.IdUtil;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
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
public class TeacherController extends BaseController
{
    @Autowired
    private TeacherEbi teaEbi;
    @Autowired
    private RoleEbi roleEbi;
    @Autowired
    private ResourceEbi resourceEbi;
    @Autowired
    private SchoolEbi schoolEbi;

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
        return "redirect:/manage/detail";
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

    //------------------------------------------TeacherManager----------------------------------------------
    //------------------------------------------TeacherManager----------------------------------------------
    //------------------------------------------TeacherManager----------------------------------------------
    //------------------------------------------TeacherManager----------------------------------------------
    //------------------------------------------TeacherManager----------------------------------------------
    //------------------------------------------TeacherManager----------------------------------------------

    /**
     * 跳转教师列表页面（分页）
     * @param teacherQueryVo    该模型存放了教师属性  分页数据  查询条件
     * @param request
     * @return
     */
    //TODO  异步请求分页
    @RequestMapping("list")
    public String toTeacherList(TeacherQueryModel teacherQueryVo ,Integer pageNum,Integer pageSize, HttpServletRequest request){
        //获取学校列表
        List<SchoolVo> schoolList = schoolEbi.getAll();
        request.setAttribute("schoolList",schoolList);

        //调用BaseController的方法设置数据总量及最大页码数
        pageCount=pageSize;
        setDataTotal(teaEbi.getCount(teacherQueryVo));

        List<TeacherVo> teacherList = teaEbi.getAll(teacherQueryVo, pageNum, pageSize);
        request.setAttribute("teacherList",teacherList);

        return "manager/teacher/list";
    }
    /**
     * 跳转教师添加/修改页面
     *
     * （此处将添加和修改页面合并，如果前台传递ID则进行修改否则进入添加页面）
     *
     * @param teacher    接受前台传递的教师id
     * @param request   HttpServletRequest
     * @return          跳转edit
     */
    @RequestMapping("add")
    public String add(TeacherVo teacher, HttpServletRequest request){
        //判断前台是否传递教师ID
        if(null!= teacher.getId()){
            //根据学校ID获取教师完整信息从而进行数据回显
            teacher =teaEbi.get(teacher.getId());
            request.setAttribute("teacher", teacher);
        }
        return "redirect:/teacher/list";
    }

    /**
     * 添加教师
     * @param teacher    需要添加的信息
     * @return          跳转教师列表页面
     */
    @RequestMapping("doAdd")
    public String doAdd(TeacherVo teacher){
        if(null== teacher.getId()){
            teacher.setId(IdUtil.getUUID());
            teaEbi.save(teacher);
        }else{
            teaEbi.update(teacher);
        }
        return "redirect:/teacher/list";
    }


    /**
     * 删除学校
     * @param  teacher   需要删除的学校
     * @return          跳转学校列表页面
     */
    @RequestMapping("teacher/delete")
    public String delete(TeacherVo teacher){

        //TODO 此处进行异常处理  异常描述：若该删除的学校有关联的学生或班级则抛出异常
        teaEbi.delete(teacher);

        return "redirect:/teacher/list";
    }


    //-------------------------------TeacherManager-----------END-------------------------------------------
    //-------------------------------TeacherManager-----------END-------------------------------------------
    //-------------------------------TeacherManager-----------END-------------------------------------------
    //-------------------------------TeacherManager-----------END-------------------------------------------
    //-------------------------------TeacherManager-----------END-------------------------------------------
    //-------------------------------TeacherManager-----------END-------------------------------------------

}
