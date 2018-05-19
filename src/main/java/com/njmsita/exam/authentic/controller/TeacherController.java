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
import com.njmsita.exam.manager.service.ebi.LogEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.FormatException;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.format.*;
import com.njmsita.exam.utils.idutil.IdUtil;
import com.njmsita.exam.utils.json.JsonListResponse;
import com.njmsita.exam.utils.json.JsonResponse;
import com.njmsita.exam.utils.logutils.SystemLogAnnotation;
import com.njmsita.exam.utils.validate.validategroup.AddGroup;
import com.njmsita.exam.utils.validate.validategroup.EditGroup;
import com.njmsita.exam.utils.validate.validategroup.SelfEditGroup;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
    private LogEbi logEbi;
    @Autowired

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
        return "/index_teacher";
    }

    /**
     * 教师（管理员）用户登陆
     *
     * @param teaVo   前端获取教师用户登陆数据
     * @param request HttpServletRequest
     * @param session HttpSession
     *
     * @return
     */
    @RequestMapping("login.do")
    public String login(TeacherVo teaVo, HttpServletRequest request, HttpSession session) throws Exception
    {
        //获取IP地址
        String loginIp = IPUtil.getIP(request);

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
            session.setAttribute(SysConsts.USER_RESOURCE_NAME,sbd.toString());
            session.setAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME, loginTea);
            Hibernate.initialize(loginTea.getRole());

            //获取登陆用户的菜单
            getLoginMenu(request);

            logEbi.login(loginTea, loginIp);
            return "redirect:/teacher/welcome";
        }

        //用户信息验证失败
        request.setAttribute("msg", "账号或密码不正确！！");
        return "redirect:/teacher/login";
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
    @SystemLogAnnotation(module = "教师个人", methods = "个人信息编辑")
    public String doEdit(@Validated(value = {SelfEditGroup.class}) TeacherVo teacherQuery, BindingResult bindingResult, HttpServletRequest request, HttpSession session) throws OperationException
    {
        if (bindingResult.hasErrors())
        {
            List<FieldError> list = bindingResult.getFieldErrors();
            for (FieldError fieldError : list)
            {
                //校验信息，key=属性名+Error
                request.setAttribute(fieldError.getField() + "Error", fieldError.getDefaultMessage());
            }
            request.setAttribute("teacherQuery", teacherQuery);
            return "redirect:/teacher/edit";
        }
        TeacherVo teacherVo = (TeacherVo) session.getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME);
        if (null != teacherQuery)
        {
            teacherQuery.setId(teacherVo.getId());
            //不能直接进行物理更新
            TeacherVo newteacher = teaEbi.updateByLogic(teacherQuery, System.currentTimeMillis());
            //重新将数据保存到session用于修改成功后的回显
            session.setAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME, newteacher);
        }
        return "redirect:/teacher/detail";
    }

    /**
     * 退出登陆
     */
    @RequestMapping("logout")
    @SystemLogAnnotation(module = "教师个人", methods = "退出登陆")
    public String loginOut(HttpSession session)
    {
        System.out.println("log out");
     //   System.out.println(session.getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME));
        //将session中的已登陆用户至空
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

    /**
     * 跳转修改密码页面
     *
     * @return
     */
    @RequestMapping("setpassword")
    public String toSetPassword()
    {
        //TODO 需要提供修改密码页面

        return "/manage/me/setpassword";
    }

    /**
     * 修改密码
     *
     * @param oldPassword 原始密码
     * @param newPassword 新密码
     * @param session
     *
     * @return
     */
    @RequestMapping("setpassword.do")
    @SystemLogAnnotation(module = "教师个人", methods = "修改密码")
    public String modifyPassword(String oldPassword, String newPassword, HttpSession session) throws OperationException
    {

        TeacherVo loginTea = (TeacherVo) session.getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME);
        if (!loginTea.getPassword().equals(oldPassword))
        {
            return "";
        }
        teaEbi.modifyPassword(loginTea, oldPassword, newPassword);
        return "redirect:welcome";
    }
    //------------------------------------------TeacherManager----------------------------------------------
    //------------------------------------------TeacherManager----------------------------------------------
    //------------------------------------------TeacherManager----------------------------------------------
    //------------------------------------------TeacherManager----------------------------------------------
    //------------------------------------------TeacherManager----------------------------------------------
    //------------------------------------------TeacherManager----------------------------------------------


    /**
     * 教师主页面 即list 页面           路径为 /teacher/manage        jsp位置为 manage/teacher/list 教师修改/添加页面 即edit页面      路径为
     * /teacher/manage/edit   jsp位置为 manage/teacher/edit 教师修改/添加操作 即edit.do操作   路径为 /teacher/manage/edit.do 教师详情页面
     * 即detail页面   路径为  /teacher/manage/detail   jsp位置为 manage/teacher/detail 教师删除         即delete.do操作 路径为
     * /teacher/manage/delete.do 教师批量导入操作  即import.do操作 路径为 /teacher/manage/import.do
     */

    @RequestMapping("manage")
    public String toTeacherList()
    {
        return "/manage/teacher/list";
    }

    @RequestMapping("manage/detail")
    public String toTeacherDetail(TeacherQueryModel teacherQueryModel, ModelMap modelMap)
    {
        TeacherVo teacherVo = teaEbi.get(teacherQueryModel.getId());
        modelMap.put("teacher", teacherVo);
        return "/manage/teacher/detail";
    }

    /**
     * 跳转教师列表页面（分页）
     *
     * @param teacherQueryVo 该模型存放了教师属性  分页数据  查询条件
     * @param request
     *
     * @return
     */
    //TODO fixed  异步请求分页 在下面
    @RequestMapping("manage/list")
    public String toTeacherList(TeacherQueryModel teacherQueryVo, Integer pageNum, Integer pageSize, HttpServletRequest request)
    {

        //调用BaseController的方法设置数据总量及最大页码数
        pageCount = pageSize;
        setDataTotal(teaEbi.getCount(teacherQueryVo));

        List<TeacherVo> teacherList = teaEbi.getAll(teacherQueryVo, pageNum, pageSize);
        request.setAttribute("teacherList", teacherList);

        return "/manager/teacher/list";
    }

    @ResponseBody
    @RequestMapping("manage/list.do")
    public JsonResponse teacherList(TeacherQueryModel teacherQueryVo, Integer pageNum, Integer pageSize)
    {
        return new JsonListResponse<>(
                teaEbi.getAll(teacherQueryVo, pageNum, pageSize),
                "name,id,teacherId,[role]role.name",
                teaEbi.getCount(teacherQueryVo));

    }


    /**
     * 跳转教师添加/修改页面
     * <p>
     * （此处将添加和修改页面合并，如果前台传递ID则进行修改否则进入添加页面）
     *
     * @param teacher 接受前台传递的教师id
     * @param request HttpServletRequest
     *
     * @return 跳转edit
     */
    @RequestMapping("manage/edit")
    public String add(TeacherVo teacher, HttpServletRequest request)
    {
        List<TroleVo> troleVolist = roleEbi.getAll();
        request.setAttribute("roles", troleVolist);
        //判断前台是否传递教师ID
        if (!StringUtil.isEmpty(teacher.getId()))
        {
            //根据学校ID获取教师完整信息从而进行数据回显
            teacher = teaEbi.get(teacher.getId());
            request.setAttribute("teacher", teacher);

        }
        return "/manage/teacher/edit";
    }

    /**
     * 添加教师
     *
     * @param teacher 需要添加的信息
     *
     * @return 跳转教师列表页面
     */
    @RequestMapping("manage/edit.do")
    @SystemLogAnnotation(module = "教师管理", methods = "教师添加/修改")
    public String doAdd(@Validated(value = {AddGroup.class}) TeacherVo teacher, BindingResult bindingResult,
                        HttpServletRequest request) throws OperationException
    {
        if (bindingResult.hasErrors())
        {
            List<FieldError> list = bindingResult.getFieldErrors();
            for (FieldError fieldError : list)
            {
                //校验信息，key=属性名+Error
                request.setAttribute(fieldError.getField() + "Error", fieldError.getDefaultMessage());
            }
            request.setAttribute("teacher", teacher);
            return "/back";
        }
        String id = teacher.getId();
        if (null == teacher.getId() || "".equals(teacher.getId().trim()))
        {
            id = IdUtil.getUUID();
            teacher.setId(id);
            teaEbi.save(teacher);
        } else
        {
            teaEbi.update(teacher);
        }
        return "redirect:/teacher/manage/detail?id="+id;
    }


    /**
     * 删除教师
     *
     * @param teacher 需要删除的教师
     *
     * @return 跳转教师列表页面
     */
    @ResponseBody
    @RequestMapping("manage/delete.do")
    @SystemLogAnnotation(module = "教师管理", methods = "删除教师")
    public JsonResponse delete(TeacherVo teacher) throws OperationException
    {
        if (!StringUtil.isEmpty(teacher.getId()))
        {
            teaEbi.delete(teacher);
        }
        return new JsonResponse("删除成功");
    }

    /**
     * 批量导入
     *
     * @param teacherInfo 教师信息表
     *
     * @return
     *
     * @throws FormatException
     * @throws OperationException
     * @throws IOException
     */
    @SystemLogAnnotation(module = "教师管理", methods = "批量导入")
    @RequestMapping("manage/import.do")
    public String inputXls(MultipartFile teacherInfo) throws FormatException, OperationException, IOException
    {
        if (teacherInfo != null)
        {
            if (SysConsts.INFO_BULK_INPUT_FILE_CONTENT_TYPE.equals(teacherInfo.getContentType()))
            {

                HSSFWorkbook workbook = new HSSFWorkbook(teacherInfo.getInputStream());
                HSSFSheet sheet = workbook.getSheetAt(0);
                teaEbi.bulkInputBySheet(sheet);
            }
        }
        return "redirect:/teacher/list?pageNum=1&pageSize=10";
    }

    /**
     * 密码重置
     *
     * @param teacherVo     教师信息
     * @param
     * @param
     *
     * @return
     */
    @RequestMapping("manage/resetPassword.do")
    @SystemLogAnnotation(module = "教师管理", methods = "密码重置")
    @ResponseBody
    public JsonResponse resetPassword( TeacherVo teacherVo)
    {
        teaEbi.resetPassword(teacherVo);
        return new JsonResponse("重置成功");
    }


    //-------------------------------TeacherManager-----------END-------------------------------------------
    //-------------------------------TeacherManager-----------END-------------------------------------------
    //-------------------------------TeacherManager-----------END-------------------------------------------
    //-------------------------------TeacherManager-----------END-------------------------------------------
    //-------------------------------TeacherManager-----------END-------------------------------------------
    //-------------------------------TeacherManager-----------END-------------------------------------------

}
