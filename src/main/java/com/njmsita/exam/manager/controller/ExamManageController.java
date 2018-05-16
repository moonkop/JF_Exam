package com.njmsita.exam.manager.controller;

import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.authentic.model.UserModel;
import com.njmsita.exam.authentic.service.ebi.TeacherEbi;
import com.njmsita.exam.manager.model.*;
import com.njmsita.exam.manager.model.querymodel.ExamQueryModel;
import com.njmsita.exam.manager.service.ebi.ExamEbi;
import com.njmsita.exam.manager.service.ebi.PaperEbi;
import com.njmsita.exam.manager.service.ebi.SubjectEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.format.StringUtil;
import com.njmsita.exam.utils.idutil.IdUtil;
import com.njmsita.exam.utils.json.JsonListResponse;
import com.njmsita.exam.utils.json.JsonResponse;
import com.njmsita.exam.utils.logutils.SystemLogAnnotation;
import com.njmsita.exam.utils.validate.validategroup.AddGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 考试管理控制器
 */
@Controller
@RequestMapping("/exam/manage")
public class ExamManageController
{
    @Autowired
    private ExamEbi examEbi;
    @Autowired
    private SubjectEbi subjectEbi;
    @Autowired
    private PaperEbi paperEbi;
    @Autowired
    private TeacherEbi teacherEbi;

    /**
     * 到编辑页面
     *
     * @param examVo
     * @param paperVo
     * @param request
     * @return
     * @throws OperationException
     */
    @RequestMapping("toEdit")
    public  String toEdit(ExamVo examVo,PaperVo paperVo,HttpServletRequest request) throws OperationException
    {
        request.setAttribute("paperList",paperEbi.getAll());
        request.setAttribute("subjectList",subjectEbi.getAll());
        request.setAttribute("teacherList",teacherEbi.getAll());
        if(!StringUtil.isEmpty(paperVo.getId())){
            PaperVo tempPaper=paperEbi.get(paperVo.getId());
            if(tempPaper==null){
                throw new OperationException("所选择的试卷不存在，请不要进行非法操作！");
            }
            request.setAttribute("paperVo",tempPaper);
            request.setAttribute("subjectId",tempPaper.getSubjectVo().getId());
        }else if(!StringUtil.isEmpty(examVo.getId())){
            if(StringUtil.isEmpty(examVo.getId())){
                throw new OperationException("所选的该场考试的id不能为空，请不要进行非法操作！");
            }
            ExamVo temp=examEbi.get(examVo.getId());
            if(temp==null){
                throw new OperationException("所选的该场考试不能为空，请不要进行非法操作！");
            }
            request.setAttribute("examVo",temp);
        }
        return "manage/exam/edit";
    }

    /**
     * 编辑
     * @param examVo
     * @param bindingResult
     * @param markTeachers
     * @param paperId
     * @param classroomIds
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("edit.do")
    @SystemLogAnnotation(module = "考试管理", methods = "发起/修改考试")
    public String examDoAdd(@Validated(value = {AddGroup.class}) ExamVo examVo, BindingResult bindingResult,
                            @RequestParam(name = "markTeachers") String[] markTeachers , String paperId ,
                            @RequestParam(name = "classroomIds") String[] classroomIds ,
                            HttpServletRequest request) throws Exception
    {
        if (bindingResult.hasErrors())
        {
            List<FieldError> list = bindingResult.getFieldErrors();
            for (FieldError fieldError : list)
            {
                //校验信息，key=属性名+Error
                request.setAttribute(fieldError.getField() + "Error", fieldError.getDefaultMessage());
                request.setAttribute("examVo",examVo);
            }
            return "manage/exam/edit";
        }
        TeacherVo login= (TeacherVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME);
        if (StringUtil.isEmpty(examVo.getId()))
        {
            examVo.setCreateTeacher(login);
            examVo.setId(IdUtil.getUUID());
            examEbi.save(examVo,markTeachers,paperId,classroomIds);
        } else
        {
            examEbi.update(examVo,markTeachers,paperId,classroomIds);
        }
        if(login.getRole().getId().equals(SysConsts.ADMIN_ROLE_ID)){
            return "redirect:/exam/list.do";
        }
        return "redirect:/exam/toMyExam";
    }

    /**
     * 到审核页面
     * @param examVo
     * @param request
     * @return
     * @throws OperationException
     */
    @RequestMapping("toCheck")
    public String toCheck(ExamVo examVo,HttpServletRequest request) throws OperationException
    {
        if(StringUtil.isEmpty(examVo.getId())){
            throw new OperationException("所选的该场考试的id不能为空，请不要进行非法操作！");
        }
        ExamVo temp=examEbi.get(examVo.getId());
        if(temp==null){
            throw new OperationException("所选的该场考试不能为空，请不要进行非法操作！");
        }
        request.setAttribute("examVo",temp);
        return "manage/exam/check";
    }

    /**
     * 审核通过
     * @param examVo
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("pass")
    @SystemLogAnnotation(module = "考试管理", methods = "审核通过")
    public String pass(ExamVo examVo,HttpServletRequest request) throws Exception
    {
        if(!StringUtil.isEmpty(examVo.getId())){
            examEbi.setPass(examVo,(TeacherVo)request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME));
        }else {
            throw new OperationException("所选择的考试不存在，请不要进行非法操作！");
        }
        return "manage/exam/toList";
    }

    /**
     * 驳回
     * @param examVo（驳回必须填写备注remark属性）
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("noPass")
    @SystemLogAnnotation(module = "考试管理", methods = "驳回考试请求")
    public String noPass(ExamVo examVo,HttpServletRequest request) throws Exception
    {
        if(!StringUtil.isEmpty(examVo.getId())){
            examEbi.setNoPass(examVo,(TeacherVo)request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME));
        }else {
            throw new OperationException("所选择的考试不存在，请不要进行非法操作！");
        }
        return "manage/exam/toList";
    }

    /**
     * 查看
     * @param examVo
     * @param request
     * @return
     * @throws OperationException
     */
    @RequestMapping("view")
    public String view(ExamVo examVo,HttpServletRequest request) throws OperationException
    {
        if(StringUtil.isEmpty(examVo.getId())){
            throw new OperationException("所选的该场考试的id不能为空，请不要进行非法操作！");
        }
        ExamVo temp=examEbi.get(examVo.getId());
        if(temp==null){
            throw new OperationException("所选的该场考试不能为空，请不要进行非法操作！");
        }
        request.setAttribute("examVo",temp);
        return "manage/exam/view";
    }

    /**
     * 取消
     * @param examVo
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("cancel")
    @ResponseBody
    @SystemLogAnnotation(module = "考试管理", methods = "取消考试")
    public JsonResponse cancel(ExamVo examVo,HttpServletRequest request) throws Exception
    {
        if(StringUtil.isEmpty(examVo.getId())){
            throw new OperationException("所选的该场考试的id不能为空，请不要进行非法操作！");
        }
        examEbi.cancel(examVo,(TeacherVo)request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME));
        return new JsonResponse("取消成功");
    }

    /**
     * 删除
     * @param examVo
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("delete")
    @ResponseBody
    @SystemLogAnnotation(module = "考试管理", methods = "删除考试")
    public JsonResponse delete(ExamVo examVo,HttpServletRequest request) throws Exception
    {
        if(StringUtil.isEmpty(examVo.getId())){
            throw new OperationException("所选的该场考试的id不能为空，请不要进行非法操作！");
        }
        examEbi.deleteCancel(examVo,(TeacherVo)request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME));
        return new JsonResponse("删除成功");
    }

    /**
     * 到添加阅卷教师
     * @param examVo
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("toAddMarkTeacher")
    public String toAddMarkTeacher(ExamVo examVo,HttpServletRequest request) throws Exception
    {
        if(StringUtil.isEmpty(examVo.getId())){
            throw new OperationException("所选的该场考试的id不能为空，请不要进行非法操作！");
        }
        ExamVo temp=examEbi.get(examVo.getId());
        if(temp==null){
            throw new OperationException("所选的该场考试不能为空，请不要进行非法操作！");
        }
        List<TeacherVo> markTeacherList=new ArrayList<>();
        request.setAttribute("markTeacherList",markTeacherList.addAll(temp.getMarkTeachers()));
        request.setAttribute("teacherList",teacherEbi.getAll());
        return "manage/exam/addMarkTeacher";
    }

    /**
     * 添加阅卷教师
     * @param examVo
     * @param markTeachers
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("addMarkTeacher")
    @SystemLogAnnotation(module = "考试管理", methods = "添加阅卷教师")
    public String addMarkTeacher(ExamVo examVo,@RequestParam("markTeachers") String[] markTeachers,
                                 HttpServletRequest request) throws Exception
    {

        if(StringUtil.isEmpty(examVo.getId())){
            throw new OperationException("所选的该场考试的id不能为空，请不要进行非法操作！");
        }

        examEbi.updateMarkTeacher(examVo,markTeachers);

        TeacherVo login= (TeacherVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME);
        if(login.getRole().getId().equals(SysConsts.ADMIN_ROLE_ID)){
            return "redirect:/exam/list.do";
        }
        return "redirect:/exam/toMyExam";
    }

    /**
     * 到列表页面
     * @param request
     * @return
     */
    @RequestMapping("toList")
    public String toList(HttpServletRequest request){

        request.setAttribute("subjectList",subjectEbi.getAll());
        request.setAttribute("teacherList",teacherEbi.getAll());
        request.setAttribute("examList",examEbi.getAll());
        return "manage/exam/list";
    }

    /**
     * 列表
     * @param examQueryModel    查询条件（考试开始区间  发起教师  科目  考试状态）--->考试开始区间分为：startTime endTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("list.do")
    public JsonResponse list(ExamQueryModel examQueryModel, Integer pageNum, Integer pageSize,
                             HttpServletRequest request) throws Exception
    {
        TeacherVo login= (TeacherVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME);
        return new JsonListResponse<ExamVo>(examEbi.getAllByAdmin(login.getId(),examQueryModel,pageNum,pageSize),
                "id,name,opentime,duration,remark,operation,[teacher]createTeacher.name,[subject]subjectVo.name,examStatusView",0);
    }

    /**
     * 到老师的考试页面
     * @param request
     * @return
     */
    @RequestMapping("toTeacherExam")
    public String toTeacherExam(HttpServletRequest request){
        UserModel login= (UserModel) request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME);
        request.setAttribute("subjectList",subjectEbi.getAll());
        List<ExamVo> createList=examEbi.getByCreateTeacher(login.getUuid());
        List<ExamVo> markList=examEbi.getByMarkTeacher(login.getUuid());
        request.setAttribute("createList",createList);
        request.setAttribute("markList",markList);
        return "manage/me/teacherExam";
    }

    /**
     * 作答存档
     *
     * @param studentExamQuestionList
     * @param studentExamId
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("archive")
    public JsonResponse archive(@RequestBody List<StudentExamQuestionVo> studentExamQuestionList, String studentExamId,
                                HttpServletRequest request) throws Exception
    {
        StudentVo login= (StudentVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME);
        examEbi.archive(login,studentExamId,studentExamQuestionList);
        return null;
    }

    /**
     * 提交试卷作答（提交试卷作答前必须将所有试卷存档）
     *
     * @param studentExamVo
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("submitAnswer")
    public String submitAnswer(StudentExamVo studentExamVo,HttpServletRequest request) throws Exception
    {
        StudentVo login= (StudentVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME);
        examEbi.submitAnswer(studentExamVo,login);
        //TODO 提交作答后返回的页面
        return "";
    }

    /**
     * 获取系统时间
     *
     * @return
     */
    @RequestMapping("systemTime")
    public JsonResponse systemTime(){
        Long time=System.currentTimeMillis();
        return new JsonResponse().put("time",time);
    }
}
