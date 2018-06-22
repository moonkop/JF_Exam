package com.njmsita.exam.manager.controller;

import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.authentic.model.UserModel;
import com.njmsita.exam.authentic.service.ebi.TeacherEbi;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.QuestionVo;
import com.njmsita.exam.manager.model.StudentExamQuestionVo;
import com.njmsita.exam.manager.model.StudentExamVo;
import com.njmsita.exam.manager.service.ebi.ExamManageEbi;
import com.njmsita.exam.manager.service.ebi.ExamMarkEbi;
import com.njmsita.exam.manager.service.ebi.SubjectEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.ItemNotFoundException;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.format.StringUtil;
import com.njmsita.exam.utils.json.CustomJsonSerializer;
import com.njmsita.exam.utils.json.JsonListObjectMapper;
import com.njmsita.exam.utils.json.JsonListResponse;
import com.njmsita.exam.utils.json.JsonResponse;
import com.njmsita.exam.utils.logutils.SystemLogAnnotation;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import org.apache.regexp.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private ExamManageEbi examManageEbi;
    @Autowired
    private ExamMarkEbi examMarkEbi;
    @Autowired
    private TeacherEbi teacherEbi;

    /**
     * 到审核页面
     *
     * @param request
     *
     * @return
     *
     * @throws OperationException
     */
    @RequestMapping("review")
    public String review(String id, HttpServletRequest request) throws Exception
    {
        ExamVo examVo;
        if (StringUtil.isEmpty(id) || (examVo = examManageEbi.getWithPaper(id)) == null)
        {
            throw new OperationException("该考试不存在");
        }
        examManageEbi.checkPermission(SysConsts.EXAM_OPERATION_VIEW, (TeacherVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME), examVo);

        request.setAttribute("exam", examVo);
        if (examVo.getPaperVo() != null)
        {
            request.setAttribute("paper", examVo.getPaperVo());
            request.setAttribute("questionList", CustomJsonSerializer.toJsonString_static
                    (
                            new JsonListObjectMapper<QuestionVo>().
                                    setFields("id,outline,options,value,code,index,type,answer").
                                    serializeList(examVo.getPaperVo().getQuestionList())
                    ));
        }
        return "/manage/exam/review";
    }

    /**
     * 审核通过
     *
     * @param examVo
     * @param request
     *
     * @return
     *
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("pass.do")
    @SystemLogAnnotation(module = "考试管理", methods = "审核通过")
    public JsonResponse pass(ExamVo examVo, HttpServletRequest request) throws Exception
    {
        if (!StringUtil.isEmpty(examVo.getId()))
        {
            examManageEbi.setPass(examVo, (TeacherVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME));
        } else
        {
            throw new OperationException("所选择的考试不存在");
        }
        return new JsonResponse();
    }

    /**
     * 审核考试
     *
     * @param examVo（驳回必须填写备注remark属性）
     * @param request
     *
     * @return
     *
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("reject.do")
    @SystemLogAnnotation(module = "考试管理", methods = "驳回考试请求")
    public JsonResponse reject(ExamVo examVo, String comment, HttpServletRequest request) throws Exception
    {
        if (!StringUtil.isEmpty(examVo.getId()))
        {
            examManageEbi.setNoPass(examVo, (TeacherVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME));
        } else
        {
            throw new OperationException("所选择的考试不存在");
        }
        return new JsonResponse();
    }


    /**
     * 取消
     *
     * @param examVo
     * @param session
     *
     * @return
     *
     * @throws Exception
     */
    @RequestMapping("cancel.do")
    @ResponseBody
    @SystemLogAnnotation(module = "考试管理", methods = "取消考试")
    public JsonResponse cancel(ExamVo examVo, HttpSession session) throws Exception
    {
        if (StringUtil.isEmpty(examVo.getId()))
        {
            throw new OperationException("所选的该场考试的id不能为空");
        }
        TeacherVo teacherVo = (TeacherVo) session.getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
        examManageEbi.cancel(examVo, teacherVo);
        return new JsonResponse("取消成功");
    }

    /**
     * 到添加阅卷教师
     *
     * @param examVo
     * @param request
     *
     * @return
     *
     * @throws Exception
     */
    @RequestMapping("addMarkTeacher")
    public String toAddMarkTeacher(ExamVo examVo, HttpServletRequest request) throws Exception
    {
        if (StringUtil.isEmpty(examVo.getId()))
        {
            throw new OperationException("所选的该场考试的id不能为空");
        }
        ExamVo examPo = examManageEbi.get(examVo.getId());
        if (examPo == null)
        {
            throw new OperationException("所选的该场考试不能为空");
        }
        List<TeacherVo> markTeacherList = new ArrayList<>();
        markTeacherList.addAll(examPo.getMarkTeachers());
        List<TeacherVo> teacherVoList = teacherEbi.getAll();
        request.setAttribute("markTeacherList", markTeacherList);
        request.setAttribute("teacherList", teacherVoList);
        request.setAttribute("exam", examPo);
        return "manage/exam/addMarkTeacher";
    }

    /**
     * 添加阅卷教师
     *
     * @param examVo
     * @param markTeachers
     * @param request
     *
     * @return
     *
     * @throws Exception
     */
    @RequestMapping("addMarkTeacher.do")
    @SystemLogAnnotation(module = "考试管理", methods = "添加阅卷教师")
    @ResponseBody
    public JsonResponse addMarkTeacher(ExamVo examVo, @RequestParam("_markTeachers[]") String[] markTeachers,
                                       HttpServletRequest request) throws Exception
    {

        if (StringUtil.isEmpty(examVo.getId()))
        {
            throw new OperationException("所选的该场考试的id不能为空");
        }

        examManageEbi.updateMarkTeacher(examVo, markTeachers);

        return new JsonResponse("保存成功！");
    }


    /**
     * 批阅页面
     *
     * @param ExamId
     * @param request
     *
     * @return
     *
     * @throws Exception
     */
    @RequestMapping("mark")
    public String toMarking(@RequestParam(name = "id") String ExamId, HttpServletRequest request) throws Exception
    {
        TeacherVo loginTeacher = (TeacherVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
        ExamVo examVoWithPaper = examManageEbi.getWithPaper(ExamId);
        examManageEbi.checkPermission(SysConsts.EXAM_OPERATION_MARK, loginTeacher, examVoWithPaper);

        if (examVoWithPaper.getPaperVo() != null)
        {
            request.setAttribute("paper", examVoWithPaper.getPaperVo());
            request.setAttribute("questionList", CustomJsonSerializer.toJsonString_static
                    (
                            new JsonListObjectMapper<QuestionVo>().
                                    setFields("id,outline,options,value,code,index,type,answer").
                                    serializeList(examVoWithPaper.getPaperVo().getQuestionList())
                    ));
        }
        return "/exam/teacher/mark";
    }

    @RequestMapping("report")
    public String report(String examId, HttpServletRequest request)
    {

        return "/exam/report";
    }

    @ResponseBody
    @RequestMapping("getMarkProgress.do")
    public JsonResponse getMarkProgress(@RequestParam(name = "id") String ExamId, HttpServletRequest request) throws ItemNotFoundException
    {
        JsonResponse response = new JsonResponse();
        response.setPayload(examMarkEbi.getMarkProgress(ExamId));
        return response;
    }
    @ResponseBody
    @RequestMapping("getWorkout.do")
    public JsonResponse getStudentWorkout(@RequestParam(name = "id") String studentExamId, HttpServletRequest request) throws Exception
    {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setPayload(examMarkEbi.getStudentWorkout(studentExamId));
        return jsonResponse;
    }


    @RequestMapping("saveMark.do")
    @ResponseBody
    public JsonResponse savaMarked(@RequestBody List<StudentExamQuestionVo> studentExamQeustionList,
                                   String studentExamId) throws Exception
    {
        examMarkEbi.saveMarked(studentExamQeustionList, studentExamId);
        return new JsonResponse("保存成功！");
    }

    @RequestMapping("submitMark.do")
    @ResponseBody
    public JsonResponse submitMarked(ExamVo examVo, HttpServletRequest request) throws Exception
    {
        TeacherVo loginTeacher = (TeacherVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
        examMarkEbi.submitMarked(examVo, loginTeacher);
        return null;
    }

}
