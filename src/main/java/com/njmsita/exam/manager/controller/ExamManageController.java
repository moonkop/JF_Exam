package com.njmsita.exam.manager.controller;

import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.authentic.service.ebi.TeacherEbi;
import com.njmsita.exam.manager.dao.dao.SubjectDao;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.PaperVo;
import com.njmsita.exam.manager.service.ebi.ExamEbi;
import com.njmsita.exam.manager.service.ebi.PaperEbi;
import com.njmsita.exam.manager.service.ebi.SubjectEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.format.StringUtil;
import com.njmsita.exam.utils.idutil.IdUtil;
import com.njmsita.exam.utils.json.JsonResponse;
import com.njmsita.exam.utils.logutils.SystemLogAnnotation;
import com.njmsita.exam.utils.validate.validategroup.AddGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 考试管理控制器
 */
@Controller
@RequestMapping("/exam")
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
        if(login.getTroleVo().getId().equals(SysConsts.ADMIN_ROLE_ID)){
            return "redirect:/exam/list.do";
        }
        return "redirect:/exam/toMyExam";
    }

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

    @RequestMapping("pass")
    public String pass(ExamVo examVo,HttpServletRequest request) throws Exception
    {
        if(!StringUtil.isEmpty(examVo.getId())){
            examEbi.setPass(examVo,(TeacherVo)request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME));
        }else {
            throw new OperationException("所选择的考试不存在，请不要进行非法操作！");
        }
        return "manage/exam/toList";
    }

    @RequestMapping("noPass")
    public String noPass(ExamVo examVo,HttpServletRequest request) throws Exception
    {
        if(!StringUtil.isEmpty(examVo.getId())){
            examEbi.setNoPass(examVo,(TeacherVo)request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME));
        }else {
            throw new OperationException("所选择的考试不存在，请不要进行非法操作！");
        }
        return "manage/exam/toList";
    }

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

    @RequestMapping("toMyExam")
    public String toMyExam(HttpServletRequest request){
        TeacherVo login= (TeacherVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME);
        List<ExamVo> createList=examEbi.getByCreateTeacher(login);
        List<ExamVo> markList=examEbi.getByMarkTeacher(login);
        request.setAttribute("subjectList",subjectEbi.getAll());
        request.setAttribute("createList",createList);
        request.setAttribute("markList",markList);
        return "manage/me/myExam";
    }

    @RequestMapping("cancel")
    @ResponseBody
    public JsonResponse cancel(ExamVo examVo,HttpServletRequest request) throws Exception
    {
        if(StringUtil.isEmpty(examVo.getId())){
            throw new OperationException("所选的该场考试的id不能为空，请不要进行非法操作！");
        }
        examEbi.cancel(examVo,(TeacherVo)request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME));
        return new JsonResponse("取消成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    public JsonResponse delete(ExamVo examVo,HttpServletRequest request) throws Exception
    {
        if(StringUtil.isEmpty(examVo.getId())){
            throw new OperationException("所选的该场考试的id不能为空，请不要进行非法操作！");
        }
        examEbi.deleteCancel(examVo,(TeacherVo)request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME));
        return new JsonResponse("删除成功");
    }

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

    @RequestMapping("addMarkTeacher")
    public String addMarkTeacher(ExamVo examVo,@RequestParam("markTeachers") String[] markTeachers,
                                 HttpServletRequest request) throws Exception
    {

        if(StringUtil.isEmpty(examVo.getId())){
            throw new OperationException("所选的该场考试的id不能为空，请不要进行非法操作！");
        }

        examEbi.updateMarkTeacher(examVo,markTeachers);

        TeacherVo login= (TeacherVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME);
        if(login.getTroleVo().getId().equals(SysConsts.ADMIN_ROLE_ID)){
            return "redirect:/exam/list.do";
        }
        return "redirect:/exam/toMyExam";
    }

    @RequestMapping("toList")
    public String toList(HttpServletRequest request){

        request.setAttribute("subjectList",subjectEbi.getAll());
        request.setAttribute("examList",examEbi.getAll());
        return null;
    }
}
