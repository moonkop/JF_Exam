package com.njmsita.exam.authentic.controller;

import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.authentic.model.TresourceVo;
import com.njmsita.exam.authentic.model.querymodel.StudentQueryModel;
import com.njmsita.exam.authentic.service.ebi.ResourceEbi;
import com.njmsita.exam.authentic.service.ebi.RoleEbi;
import com.njmsita.exam.authentic.service.ebi.StudentEbi;
import com.njmsita.exam.base.BaseController;
import com.njmsita.exam.manager.model.ClassroomVo;
import com.njmsita.exam.manager.model.SchoolVo;
import com.njmsita.exam.manager.service.ebi.ClassroomEbi;
import com.njmsita.exam.manager.service.ebi.LogEbi;
import com.njmsita.exam.manager.service.ebi.SchoolEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.FieldErrorException;
import com.njmsita.exam.utils.exception.FormatException;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.format.*;
import com.njmsita.exam.utils.json.JsonListResponse;
import com.njmsita.exam.utils.json.JsonResponse;
import com.njmsita.exam.utils.logutils.SystemLogAnnotation;
import com.njmsita.exam.utils.validate.validategroup.EditGroup;
import com.njmsita.exam.utils.validate.validategroup.StudentAddGroup;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


/**
 * 学生控制器
 */
@Controller
@Scope("prototype")
@RequestMapping("/student")
public class StudentController extends BaseController {
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
    @Autowired
    private LogEbi logEbi;

    /**
     * 跳转登陆页
     *
     * @return
     */
    @RequestMapping("login")
    public String toLogin(HttpServletRequest request) {
        //为页面提供学校信息
        List<SchoolVo> schoolList = schoolEbi.getAll();
        request.setAttribute("schoolList", schoolList);
        return "login_student";
    }

    @RequestMapping("welcome")
    public String towelcome() {
        return "/index_student";
    }

    /**
     * 学生用户登陆
     *
     * @param student 前端获取学生用户登陆数据
     * @param request HttpServletRequest
     * @param session HttpSession
     * @return
     */
    @RequestMapping("login.do")
    @ResponseBody
    public JsonResponse login(StudentVo student, String schoolId, HttpServletRequest request, HttpSession session) {

        //获取IP地址
        String loginIp = IPUtil.getIP(request);

        //验证用户名密码
        StudentVo loginStudent = studentEbi.login(student.getStudentId(), student.getPassword(), schoolId, loginIp);

        if (loginStudent == null) {
            //用户信息验证失败
            return new JsonResponse(501, "登录失败，账号或密码不正确！！");
        }
        //用户信息验证成功
        //用户名密码验证成功获取当前登录人的所有权限
        List<TresourceVo> studentResources = resourceEbi.getAllByLogin_stu(loginStudent.getId());
        StringBuilder sbd = new StringBuilder();
        //拼接用户资源信息存入登陆用户
        for (TresourceVo resource : studentResources) {
            sbd.append(resource.getUrl());
            sbd.append(",");
        }
        session.setAttribute(SysConsts.USER_RESOURCE_NAME, sbd.toString());
        session.setAttribute(SysConsts.USER_LOGIN_OBJECT_NAME, loginStudent);
        Hibernate.initialize(loginStudent.getRole());

        //获取登陆用户的菜单
        getLoginMenu(request);

        //记录登录日志
        logEbi.login(loginStudent, loginIp);
        //TODO 首次登陆强制跳转个人信息页面添加班级信息？还是直接批量导入时设置班级
        //fixme 应该是批量导入时设置班级
        return new JsonResponse("登录成功");
    }

    /**
     * 查看个人详细信息
     */
    @RequestMapping("detail")
    public String detail() {
        //前台数据从session中获取
        return "/manage/me/detail";
    }

    /**
     * 跳转个人信息编辑
     */
    @RequestMapping("edit")
    public String edit() {
        //回显数据在session中获取
        return "/manage/me/edit";
    }

    /**
     * 个人信息编辑
     */
    @ResponseBody
    @RequestMapping("edit.do")
    @SystemLogAnnotation(module = "学生个人", methods = "个人信息编辑")
    public JsonResponse doEdit(@Validated(value = {EditGroup.class}) StudentVo studentVo, BindingResult bindingResult, String classroomId, HttpServletRequest request, HttpSession session) throws OperationException, FieldErrorException {
        JsonResponse response = new JsonResponse();
        CheckErrorFields(bindingResult);
        StudentVo studentLogin = (StudentVo) session.getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
        if (null != studentVo) {
            studentVo.setId(studentLogin.getId());
            ClassroomVo classroom = new ClassroomVo();
            classroom.setId(classroomId);
            studentVo.setClassroom(classroom);
            //不能直接进行物理更新
            StudentVo newStudent = studentEbi.updateByLogic(studentVo, System.currentTimeMillis());
            //重新将数据保存到session用于修改成功后的回显
            session.setAttribute(SysConsts.USER_LOGIN_OBJECT_NAME, newStudent);
        }
        return response;
    }


    /**
     * 跳转修改密码页面
     *
     * @return
     */
    @RequestMapping("setpassword")
    public String toSetPassword() {

        return "/manage/me/student/setpassword";
    }
    /**
     * 退出登陆
     */
    @RequestMapping("logout")
    @SystemLogAnnotation(module = "学生个人", methods = "退出登录")
    public String loginOut(HttpSession session) {
        session.invalidate();
        return "redirect:/student/login";
    }

    /**
     * 修改密码
     *
     * @param oldPassword    原始密码
     * @param newPassword    新密码
     * @param repeatpassword 再次输入的密码
     * @param session
     * @return
     */
    @RequestMapping("setpassword.do")
    @SystemLogAnnotation(module = "学生个人", methods = "修改密码")
    @ResponseBody
    public JsonResponse modifyPassword(String oldPassword, String newPassword, String repeatpassword, HttpSession session) throws OperationException {
        JsonResponse response = new JsonResponse();

        oldPassword = MD5Utils.md5(oldPassword);
        StudentVo loginStudent = (StudentVo) session.getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
        if (!loginStudent.getPassword().equals(oldPassword) || !newPassword.equals(repeatpassword)) {
            response.setCode(401);
            return response;

        }
        studentEbi.modifyPassword(loginStudent, oldPassword, newPassword);
        return response;
    }
    //------------------------------------------StudentManager----------------------------------------------
    //------------------------------------------StudentManager----------------------------------------------
    //------------------------------------------StudentManager----------------------------------------------
    //------------------------------------------StudentManager----------------------------------------------
    //------------------------------------------StudentManager----------------------------------------------
    //------------------------------------------StudentManager----------------------------------------------

    @RequestMapping("manage")
    public String toStudentList(HttpServletRequest request) {
        request.setAttribute("schools", schoolEbi.getAll());
        return "/manage/student/list";
    }

    @RequestMapping("manage/detail")
    public String toStudentDetail(String id, HttpServletRequest request) {
        StudentVo studentVo = studentEbi.get(id);
        request.setAttribute("student", studentVo);
        return "/manage/student/detail";
    }


    @ResponseBody
    @RequestMapping("manage/list.do")
    public JsonResponse StudentList(StudentQueryModel studentQueryModel, Integer pageNum, Integer pageSize) {


        return new JsonListResponse<>(
                studentEbi.getAll(studentQueryModel, pageNum, pageSize),
                "name," +
                        "id," +
                        "studentId," +
                        "[school]school.name," +
                        "[classroom]classroom.name," +
                        "[role]role.name",
                studentEbi.getCount(studentQueryModel));
    }

    /**
     * 跳转学生添加/修改页面
     * <p>
     * （此处将添加和修改页面合并，如果前台传递ID则进行修改否则进入添加页面）
     *
     * @param studentVo 接受前台传递的学生id
     * @param request   HttpServletRequest
     * @return 跳转edit
     */
    @RequestMapping("manage/edit")
    public String add(StudentVo studentVo, HttpServletRequest request)
    {
        request.setAttribute("schools", schoolEbi.getAll());
        request.setAttribute("classrooms", classroomEbi.getAll());
        request.setAttribute("roles", roleEbi.getAll());
        //判断前台是否传递学生ID
        if (!StringUtil.isEmpty(studentVo.getId())) {
            //根据学校ID获取学生完整信息从而进行数据回显
            studentVo = studentEbi.get(studentVo.getId());
            request.setAttribute("student", studentVo);

        }
        return "/manage/student/edit";
    }

    /**
     * 添加学生
     *
     * @param studentVo 需要添加的信息
     * @return 跳转学生列表页面
     */
    @RequestMapping("manage/edit.do")
    @SystemLogAnnotation(module = "学生管理", methods = "学生添加/修改")
    @ResponseBody
    public JsonResponse doAdd(@Validated(value = {StudentAddGroup.class}) StudentVo studentVo, BindingResult bindingResult, String schoolID, String classroomID,
                              HttpServletRequest request) throws OperationException, FieldErrorException {
        CheckErrorFields(bindingResult);
        if (null == studentVo.getId() || "".equals(studentVo.getStudentId().trim()) || "".equals(studentVo.getId())) {
            SchoolVo schoolVo = new SchoolVo();
            schoolVo.setId(schoolID);
            studentVo.setSchool(schoolVo);
            ClassroomVo classroomVo = new ClassroomVo();
            if (classroomID != null && classroomID != "") {
                classroomVo.setId(classroomID);
                studentVo.setClassroom(classroomVo);
            }
            studentEbi.save(studentVo);
        } else {
            SchoolVo schoolVo = new SchoolVo();
            schoolVo.setId(schoolID);
            studentVo.setSchool(schoolVo);
            ClassroomVo classroomVo = new ClassroomVo();
            if (classroomID != null && classroomID != "") {
                classroomVo.setId(classroomID);
                studentVo.setClassroom(classroomVo);
            }

            studentEbi.update(studentVo);
        }
        return new JsonResponse("操作成功");
    }

    /**
     * 删除学生
     *
     * @param studentVo 需要删除的学生
     * @return 跳转学生列表页面
     */
    @ResponseBody
    @RequestMapping("manage/delete.do")
    @SystemLogAnnotation(module = "学生管理", methods = "删除学生")
    public JsonResponse delete(StudentVo studentVo) throws OperationException {
        if (!StringUtil.isEmpty(studentVo.getId())) {
            studentEbi.delete(studentVo);
        }
        return new JsonResponse("删除成功");
    }

    /**
     * 批量导入学生信息
     *
     * @param studentInfo
     * @param schoolId
     * @return
     * @throws FormatException
     * @throws OperationException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("import.do")
    @SystemLogAnnotation(module = "学生管理", methods = "批量导入")
    public JsonResponse inputXls(MultipartFile studentInfo, String schoolId, String classroomId) throws FormatException, OperationException, IOException {

        if (studentInfo != null) {
            if (SysConsts.INFO_BULK_INPUT_FILE_CONTENT_TYPE.equals(studentInfo.getContentType())) {

                HSSFWorkbook workbook = new HSSFWorkbook(studentInfo.getInputStream());
                HSSFSheet sheet = workbook.getSheetAt(0);
                studentEbi.bulkInputBySheet(sheet, schoolId, classroomId);
            }
        }
        return new JsonResponse();
    }

    /**
     * 密码重置
     *
     * @param id     学生id
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("manage/resetPassword.do")
    @SystemLogAnnotation(module = "学生管理", methods = "密码重置")
    public JsonResponse resetPassword(String id, HttpServletRequest request) throws FieldErrorException
    {
        JsonResponse response = new JsonResponse();
        studentEbi.resetPassword(id);
        response.setMessage("密码重置成功！默认密码为学生学号！");
        return response;
    }

    //-------------------------------StudentManager-----------END-------------------------------------------
    //-------------------------------StudentManager-----------END-------------------------------------------
    //-------------------------------StudentManager-----------END-------------------------------------------
    //-------------------------------StudentManager-----------END-------------------------------------------
    //-------------------------------StudentManager-----------END-------------------------------------------
    //-------------------------------StudentManager-----------END-------------------------------------------
}
