package com.njmsita.exam.authentic.controller;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.authentic.model.TresourceVo;
import com.njmsita.exam.authentic.model.TroleVo;
import com.njmsita.exam.authentic.model.querymodel.StudentQueryModel;
import com.njmsita.exam.authentic.model.querymodel.TeacherQueryModel;
import com.njmsita.exam.authentic.service.ebi.ResourceEbi;
import com.njmsita.exam.authentic.service.ebi.RoleEbi;
import com.njmsita.exam.authentic.service.ebi.StudentEbi;
import com.njmsita.exam.base.BaseController;
import com.njmsita.exam.manager.model.ClassroomVo;
import com.njmsita.exam.manager.model.SchoolVo;
import com.njmsita.exam.manager.service.ebi.ClassroomEbi;
import com.njmsita.exam.manager.service.ebi.SchoolEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.FormatException;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.idutil.IdUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


/**
 * 学生控制器
 */
@Controller
@Scope("prototype")
@RequestMapping("/student")
public class StudentController extends BaseController
{
    @Autowired
    private StudentEbi studentEbi;
    @Autowired
    private RoleEbi roleEbi;
    @Autowired
    private ResourceEbi resourceEbi;
    @Autowired
    private SchoolEbi schoolEbi;
    @Autowired
    private ClassroomEbi classroomEbi;
    private ObjectError error;


    /**
     * 跳转登陆页
     *
     * @return
     */
    @RequestMapping("login")
    public String toLogin(HttpServletRequest request)
    {
        //为页面提供学校信息
        List<SchoolVo> schoolList=schoolEbi.getAll();
        request.setAttribute("schoolList",schoolList);
        return "login_student";
    }

    @RequestMapping("welcome")
    public String towelcome()
    {
        return "index_student";
    }

    /**
     * 学生用户登陆
     *
     * @param student   前端获取学生用户登陆数据
     * @param request HttpServletRequest
     * @param session HttpSession
     * @return
     */
    @RequestMapping("login.do")
    public String login(StudentVo student, String schoolId, HttpServletRequest request, HttpSession session)
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
        StudentVo loginStudent = studentEbi.login(student.getStudentId(), student.getPassword(),schoolId, loginIp);

        //用户信息验证成功
        if (loginStudent != null)
        {

            //用户名密码验证成功获取当前登录人的所有权限
            List<TresourceVo> studentResources = resourceEbi.getAllByLogin(loginStudent.getId());
            StringBuilder sbd = new StringBuilder();
            //拼接用户资源信息存入登陆用户
            for (TresourceVo resource : studentResources)
            {
                sbd.append(resource.getUrl());
                sbd.append(",");
            }
            loginStudent.setStudentRes(sbd.toString());
            session.setAttribute(SysConsts.STUDENT_LOGIN_TEACHER_OBJECT_NAME, loginStudent);

            //TODO 首次登陆强制跳转个人信息页面添加班级信息？还是直接批量导入时设置班级
            return "redirect:/student/welcome";
        }

        //用户信息验证失败
        request.setAttribute("msg", "账号或密码不正确！！");
        return "student/login_student";
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
    public String doEdit(StudentVo studentVo, String classroomId, HttpServletRequest request, HttpSession session) throws OperationException
    {
        StudentVo studentLogin = (StudentVo) session.getAttribute(SysConsts.STUDENT_LOGIN_TEACHER_OBJECT_NAME);
        if (null != studentVo)
        {
            studentVo.setId(studentLogin.getId());
            ClassroomVo classroom=new ClassroomVo();
            classroom.setId(classroomId);
            studentVo.setClassroom(classroom);
            //不能直接进行物理更新
            StudentVo newStudent = studentEbi.updateByLogic(studentVo, System.currentTimeMillis());
            //重新将数据保存到session用于修改成功后的回显
            session.setAttribute(SysConsts.TEACHER_LOGIN_TEACHER_OBJECT_NAME, newStudent);
        }
        return "redirect:/student/detail";
    }

    /**
     * 退出登陆
     */
    @RequestMapping("logout")
    public String loginOut(HttpSession session)
    {
        //将session中的已登陆用户至空
        //TODO 有疑问？？？？？？？？
        session.setAttribute(SysConsts.STUDENT_LOGIN_TEACHER_OBJECT_NAME,null);
        return "redirect:/student/login";
    }

    @RequestMapping("rolelist")
    @JsonGetter
    @ResponseBody
    //TODO 为什么会出现这个方法
    public List<TroleVo> getRoleList()
    {
        return roleEbi.getAll();
    }

    //------------------------------------------StudentManager----------------------------------------------
    //------------------------------------------StudentManager----------------------------------------------
    //------------------------------------------StudentManager----------------------------------------------
    //------------------------------------------StudentManager----------------------------------------------
    //------------------------------------------StudentManager----------------------------------------------
    //------------------------------------------StudentManager----------------------------------------------

    /**
     * 跳转学生列表页面（分页）
     * @param studentQueryModel    该模型存放了学生属性  查询条件
     * @param request
     * @return
     */
    //TODO  异步请求分页
    @RequestMapping("list")
    public String toStudentList(StudentQueryModel studentQueryModel , Integer pageNum, Integer pageSize, HttpServletRequest request){
        //获取学校列表
        List<SchoolVo> schoolList = schoolEbi.getAll();
        //获取班级列表
        List<ClassroomVo> classroomList=classroomEbi.getAll();

        request.setAttribute("schoolList",schoolList);
        request.setAttribute("classroomList",classroomList);

        //调用BaseController的方法设置数据总量及最大页码数
        pageCount=pageSize;
        setDataTotal(studentEbi.getCount(studentQueryModel));

        List<StudentVo> sutdentList = studentEbi.getAll(studentQueryModel, pageNum, pageSize);
        request.setAttribute("sutdentList",sutdentList);

        return "manager/student/list";
    }

    /**
     * 跳转学生添加/修改页面
     *
     * （此处将添加和修改页面合并，如果前台传递ID则进行修改否则进入添加页面）
     *
     * @param studentVo     接受前台传递的学生id
     * @param request       HttpServletRequest
     * @return              跳转edit
     */
    @RequestMapping("add")
    public String add(StudentVo studentVo, HttpServletRequest request){
        //判断前台是否传递学生ID
        if(null!= studentVo.getId()&&!"".equals(studentVo.getId().trim())){
            //根据学校ID获取学生完整信息从而进行数据回显
            studentVo =studentEbi.get(studentVo.getId());
            request.setAttribute("studentVo", studentVo);
        }
        return "redirect:/student/list";
    }

    /**
     * 添加学生
     * @param studentVo     需要添加的信息
     * @return              跳转学生列表页面
     */
    @RequestMapping("doAdd")
    public String doAdd(@Validated StudentVo studentVo, BindingResult bindingResult) throws OperationException
    {
        if(bindingResult.hasErrors()){
            List<ObjectError> errors=bindingResult.getAllErrors();
            List<FieldError> list =bindingResult.getFieldErrors();
            for (FieldError fieldError : list)
            {
                System.out.println(fieldError.getField()+":"+fieldError.getDefaultMessage());
            }

        }
        if(null== studentVo.getId()||"".equals(studentVo.getStudentId().trim())){
            studentVo.setId(IdUtil.getUUID());
            studentEbi.save(studentVo);
        }else{
            studentEbi.update(studentVo);
        }
        return "redirect:/student/list";
    }

    /**
     * 删除学生
     * @param  studentVo    需要删除的学生
     * @return              跳转学生列表页面
     */
    @RequestMapping("delete")
    public String delete(StudentVo studentVo) throws OperationException
    {
        studentEbi.delete(studentVo);
        return "redirect:/student/list";
    }

    @RequestMapping("inputXls")
    public String inputXls(MultipartFile studentInfo,String schoolId) throws FormatException, OperationException, IOException
    {
        if(studentInfo!=null){
            if(SysConsts.INFO_BULK_INPUT_FILE_CONTENT_TYPE.equals(studentInfo.getContentType())){

                    HSSFWorkbook workbook = new HSSFWorkbook(studentInfo.getInputStream());
                    HSSFSheet sheet=workbook.getSheetAt(0);
                    studentEbi.bulkInputBySheet(sheet,schoolId);
            }
        }
        return "redirect:/student/list?pageNum=1&pageSize=10";
    }


    //-------------------------------StudentManager-----------END-------------------------------------------
    //-------------------------------StudentManager-----------END-------------------------------------------
    //-------------------------------StudentManager-----------END-------------------------------------------
    //-------------------------------StudentManager-----------END-------------------------------------------
    //-------------------------------StudentManager-----------END-------------------------------------------
    //-------------------------------StudentManager-----------END-------------------------------------------
}
