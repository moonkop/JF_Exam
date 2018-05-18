package com.njmsita.exam.manager.controller;

import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.authentic.model.UserModel;
import com.njmsita.exam.authentic.service.ebi.TeacherEbi;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.StudentExamQuestionVo;
import com.njmsita.exam.manager.model.StudentExamVo;
import com.njmsita.exam.manager.service.ebi.ExamEbi;
import com.njmsita.exam.manager.service.ebi.SubjectEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.format.StringUtil;
import com.njmsita.exam.utils.json.JsonResponse;
import com.njmsita.exam.utils.logutils.SystemLogAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 考试操作控制器
 */
@Controller
@RequestMapping("/exam/operation")
public class ExamOperationController
{
    @Autowired
    private SubjectEbi subjectEbi;
    @Autowired
    private ExamEbi examEbi;
    @Autowired
    private TeacherEbi teacherEbi;

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
     * 到审核页面
     * @param examVo
     * @param request
     * @return
     * @throws OperationException
     */
    @RequestMapping("toCheck")
    public String toCheck(ExamVo examVo, HttpServletRequest request) throws OperationException
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
    public JsonResponse cancel(ExamVo examVo, HttpServletRequest request) throws Exception
    {
        if(StringUtil.isEmpty(examVo.getId())){
            throw new OperationException("所选的该场考试的id不能为空，请不要进行非法操作！");
        }
        examEbi.cancel(examVo,(TeacherVo)request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME));
        return new JsonResponse("取消成功");
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

    @RequestMapping("toStudentExam")
    public String toStudentExam(ExamVo examVo,HttpServletRequest request) throws Exception
    {
        if(StringUtil.isEmpty(examVo.getId())){
            throw new OperationException("所选该场考试不能为空，请不要进行非法操作！");
        }
        examVo=examEbi.get(examVo.getId());
        if(examVo==null){
            throw new OperationException("所选该场考试不存在，请不要进行非法操作！");
        }
        TeacherVo login= (TeacherVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME);
        List<StudentExamVo> studentExamList=examEbi.getAllStudentExamByExam(examVo,login);
        request.setAttribute("studentExamList",studentExamList);
        request.setAttribute("examVo",examVo);
        return null;
    }

    @RequestMapping("toMarking")
    public String toMarking(StudentExamVo studentExamVo ,HttpServletRequest request) throws Exception
    {
        List<StudentExamQuestionVo> studentExamQuestionList=examEbi.getAllStudentexamQuestionByStudentExam(studentExamVo);
        request.setAttribute("studentExamQuestionList",studentExamQuestionList);
        return null;
    }

    @RequestMapping("savaMarked")
    @ResponseBody
    public JsonResponse savaMarked(@RequestBody List<StudentExamQuestionVo> studentExamQeustionList,
                                   String studentExamId) throws Exception
    {
        examEbi.saveMarked(studentExamQeustionList,studentExamId);
        return new JsonResponse("保存成功！");
    }

    @RequestMapping("submitMarked")
    @ResponseBody
    public JsonResponse submitMarked(ExamVo examVo,HttpServletRequest request) throws Exception
    {
        TeacherVo login= (TeacherVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME);
        examEbi.submitMarked(examVo,login);
        return null;
    }

}
