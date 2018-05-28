package com.njmsita.exam.manager.controller;

import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.authentic.model.UserModel;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.StudentExamQuestionVo;
import com.njmsita.exam.manager.model.StudentExamVo;
import com.njmsita.exam.manager.service.ebi.ExamManageEbi;
import com.njmsita.exam.manager.service.ebi.ExamStudentEbi;
import com.njmsita.exam.manager.service.ebi.SubjectEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.exception.UnLoginException;
import com.njmsita.exam.utils.format.StringUtil;
import com.njmsita.exam.utils.json.JsonListResponse;
import com.njmsita.exam.utils.json.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

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
        UserModel loginStudent = (UserModel) request.getSession().getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
        request.setAttribute("subjectList", subjectEbi.getAll());
        List<ExamVo> myExamList = examManageEbi.getStudentExamList(loginStudent.getUuid());
        request.setAttribute("myExamList", myExamList);
        return "/exam/student/list";
    }


    /**
     * 到参加考试页面
     *
     * @return
     */
    @RequestMapping("enter")
    public String toAttendExam()
    {

        return "/exam/student/enter";
    }

    /**
     * 到做题页面
     *
     * @param studentExamId
     *
     * @return
     */
    @RequestMapping("workout")
    public String workout(String studentExamId, HttpSession session) throws OperationException
    {
        if (StringUtil.isEmpty(studentExamId))
        {
            throw new OperationException("所选的该场考试的id不能为空，请不要进行非法操作！");
        }
        StudentVo studentVo = (StudentVo) session.getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
     //   Map<String, Object> map = examStudentEbi.attendExam(studentExamId);
        return "/exam/student/workout";
    }

    /**
     * 获得试卷（题目列表）
     * @param studentExam
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("getPaper.do")
    public JsonResponse getPaper(StudentExamVo studentExam, HttpSession session) throws Exception
    {
//
//        //学生作答情况
//        List<StudentExamQuestionVo> studentExamQuestionList = (List<StudentExamQuestionVo>) map.get("studentExamQuestionList");
//        //学生考试信息
//        StudentExamVo studentExam = (StudentExamVo) map.get("studentExam");
//        //TODO
        return null;
    }

    /**
     * 获得我的答案列表
     * @param studentExamVo
     * @param session
     * @return
     */
    @RequestMapping("getMyAnswer.do")
    public JsonResponse getMyAnswer(StudentExamVo studentExamVo, HttpSession session)
    {

        StudentVo studentVo = (StudentVo) session.getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
        return new JsonListResponse<>();

    }

    /**
     * 作答存档
     *
     * @param studentExamQuestionList
     * @param studentExamId
     * @param request
     *
     * @return
     *
     * @throws Exception
     */
    @RequestMapping("archive")
    public JsonResponse archive(@RequestBody List<StudentExamQuestionVo> studentExamQuestionList, String studentExamId,
                                HttpServletRequest request) throws Exception
    {
        StudentVo login = (StudentVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
        examStudentEbi.archive(login, studentExamId, studentExamQuestionList);
        return null;
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
    @RequestMapping("submitAnswer")
    public String submitAnswer(StudentExamVo studentExamVo, HttpServletRequest request) throws Exception
    {
        StudentVo login = (StudentVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
        examStudentEbi.submitAnswer(studentExamVo, login);
        //TODO 提交作答后返回的页面
        return "";
    }

    /**
     * 获取系统时间
     *
     * @return
     */
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
