package com.njmsita.exam.manager.controller;

import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.QuestionVo;
import com.njmsita.exam.manager.model.StudentExamQuestionVo;
import com.njmsita.exam.manager.model.StudentExamVo;
import com.njmsita.exam.manager.model.querymodel.ArchiveWrapper;
import com.njmsita.exam.manager.model.querymodel.StudentExamListQueryModel;
import com.njmsita.exam.manager.service.ebi.ExamManageEbi;
import com.njmsita.exam.manager.service.ebi.ExamStudentEbi;
import com.njmsita.exam.manager.service.ebi.SubjectEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.exception.UnLoginException;
import com.njmsita.exam.utils.format.StringUtil;
import com.njmsita.exam.utils.json.CustomJsonSerializer;
import com.njmsita.exam.utils.json.JsonListObjectMapper;
import com.njmsita.exam.utils.json.JsonListResponse;
import com.njmsita.exam.utils.json.JsonResponse;
import com.njmsita.exam.utils.logutils.SystemLogAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 考试流程控制器
 */
@Controller
@RequestMapping("/exam/student")
public class ExamStudentController
{
    @Autowired
    private SubjectEbi subjectEbi;
    @Autowired
    private ExamManageEbi examManageEbi;
    @Autowired
    private ExamStudentEbi examStudentEbi;


    /**
     * 到学生的考试页面 list
     *
     * @param request
     *
     * @return
     */
    @RequestMapping("")
    public String toStudentExam(HttpServletRequest request) throws UnLoginException
    {
        request.setAttribute("subjectList", subjectEbi.getAll());
        return "/exam/student/list";
    }

    @ResponseBody
    @RequestMapping("list.do")
    public JsonResponse StudentExamList(StudentExamListQueryModel queryModel, HttpSession session) throws UnLoginException
    {
        StudentVo loginStudent = (StudentVo) session.getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
        if (loginStudent == null)
        {
            throw new UnLoginException();
        }
        queryModel.setStudent((StudentVo) session.getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME));
        List<StudentExamVo> myExamList = examStudentEbi.getStudentExamList(queryModel);
        return new JsonListResponse<>(myExamList, "id,score," +
                "[examId]exam.getId()," +
                "[name]exam.getName()," +
                "[examStatusView]exam.getExamStatusView()," +
                "[operation]exam.getOperation()," +
                "[openTime]exam.getOpenTime()," +
                "[duration]exam.getDuration()," +
                "[subject]exam.getSubject().getName()", examStudentEbi.getStudentExamCount(queryModel));
    }


    /**
     * 到参加考试页面
     *
     * @return
     */
    @RequestMapping("preview")
    public String preview(String id, HttpServletRequest request, HttpSession session) throws Exception
    {
        StudentExamVo studentExamPo = examStudentEbi.get(id);
        examManageEbi.checkPermission(SysConsts.EXAM_OPERATION_PREVIEW,
                (StudentVo) session.getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME),
                studentExamPo);//todo 转换错误
        request.setAttribute("exam", studentExamPo.getExam());
        request.setAttribute("studentExam", studentExamPo);
        return "/exam/student/preview";
    }

    /**
     * 到参加考试页面
     *
     * @return
     */
    @RequestMapping("enter")
    public String toAttendExam(String id, HttpServletRequest request)
    {
        return "redirect:/exam/student/preview?id=" + id;
    }

    /**
     * 到做题页面
     *
     * @param studentExamId
     *
     * @return
     */
    @SystemLogAnnotation(module = "学生考试", methods = "进入考试")
    @RequestMapping("workout")
    public String workout(@RequestParam(name = "id") String studentExamId, HttpServletRequest request, HttpSession session) throws Exception
    {
        if (StringUtil.isEmpty(studentExamId))
        {
            throw new OperationException("所选的该场考试的id不能为空，请不要进行非法操作！");
        }
        StudentVo studentVo = (StudentVo) session.getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
        StudentExamVo studentExamPo = examStudentEbi.enterExam(studentExamId, studentVo);
        ExamVo examPo = studentExamPo.getExam();
        ExamVo examVoWithPaper = examManageEbi.getWithPaper(studentExamPo.getExam().getId());
        request.setAttribute("exam", examPo);
        request.setAttribute("studentExam", studentExamPo);
        request.setAttribute("currentTime", System.currentTimeMillis());
        if (examPo.getPaperVo() != null)
        {
            request.setAttribute("paper", examVoWithPaper.getPaperVo());
            request.setAttribute("questionList", CustomJsonSerializer.toJsonString_static
                    (
                            new JsonListObjectMapper<QuestionVo>().
                                    setFields("id,outline,options,value,code,index,type,answer").
                                    serializeList(examVoWithPaper.getPaperVo().getQuestionList())
                    ));
        }

        return "/exam/student/workout";
    }

    /**
     * 获得试卷（题目列表）
     *
     * @param studentExamId
     * @param session
     *
     * @return
     *
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("getPaper.do")
    public JsonResponse getPaper(@RequestParam(name = "id") String studentExamId, HttpSession session) throws Exception
    {
        StudentVo studentVo = (StudentVo) session.getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
        List<QuestionVo> questionVolist = examStudentEbi.getPaperQuestion(studentExamId, studentVo);
        return new JsonListResponse<QuestionVo>(questionVolist, "index,type,value,outline,options");
    }

    /**
     * 获得我的答案列表
     *
     * @param studentExamId
     * @param session
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getWorkout.do")
    public JsonResponse getWorkout(@RequestParam(name = "id") String studentExamId, HttpSession session) throws Exception
    {

        StudentVo studentVo = (StudentVo) session.getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
        List<StudentExamQuestionVo> answerList = examStudentEbi.getStudentAnswer(studentExamId, studentVo);

        return new JsonListResponse<StudentExamQuestionVo>(answerList, "index,workout,id");

    }

    /**
     * 作答存档
     *
     * @param wrapper
     * @param request
     *
     * @return
     *
     * @throws Exception
     */

    @ResponseBody
    @RequestMapping("archive.do")
    public JsonResponse archive(@RequestBody ArchiveWrapper wrapper,
                                HttpServletRequest request)
            throws Exception
    {

        StudentVo login = (StudentVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
        examStudentEbi.archive(login, wrapper.getId(), wrapper.getWorkouts());
        return new JsonResponse();
    }

    /**
     * 提交试卷作答（提交试卷作答前必须将所有试卷存档）
     *
     * @param studentExamVo
     * @param request
     *
     * @return
     *
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("submit.do")
    public JsonResponse submitAnswer(StudentExamVo studentExamVo, HttpServletRequest request) throws Exception
    {
        StudentVo login = (StudentVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
        examStudentEbi.submit(studentExamVo, login);
        //TODO 提交作答后返回的页面
        return new JsonResponse("交卷成功");
    }

    /**
     * 获取系统时间
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("systemTime")
    public JsonResponse systemTime()
    {
        Long time = System.currentTimeMillis();
        return new JsonResponse().put("time", time);
    }

    /**
     * 查看个人成绩
     */
    @RequestMapping("checkMyGrages")
    public String checkMyGrages(ExamVo examVo, HttpServletRequest request) throws Exception
    {
        StudentVo login = (StudentVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
        StudentExamVo studentExamVo = examStudentEbi.getStudentExam(examVo, login);
        request.setAttribute("studentExam", studentExamVo);
        return null;
    }
}
