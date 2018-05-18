package com.njmsita.exam.manager.controller;

import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.authentic.model.UserModel;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.StudentExamQuestionVo;
import com.njmsita.exam.manager.model.StudentExamVo;
import com.njmsita.exam.manager.service.ebi.ExamEbi;
import com.njmsita.exam.manager.service.ebi.SubjectEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.format.StringUtil;
import com.njmsita.exam.utils.json.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 考试流程控制器
 */
@Controller
@RequestMapping("/exam/flow")
public class ExamFlowController
{
    @Autowired
    private SubjectEbi subjectEbi;
    @Autowired
    private ExamEbi examEbi;

    /**
     * 到学生的考试页面
     * @param request
     * @return
     */
    @RequestMapping("toStudentExam")
    public String toStudentExam(HttpServletRequest request){
        UserModel login= (UserModel) request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME);
        request.setAttribute("subjectList",subjectEbi.getAll());
        List<ExamVo> myExamList=examEbi.getMyExamList(login.getUuid());
        request.setAttribute("myExamList",myExamList);
        return "manage/me/studentExam";
    }

    /**
     * 到参加考试页面
     * @return
     */
    @RequestMapping("toAttendExam")
    public String toAttendExam()
    {
        return "examing/attend";
    }

    /**
     * 参加考试
     * @param examVo
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("attendExam")
    public JsonResponse attendExam(ExamVo examVo, HttpServletRequest request) throws Exception{
        if(StringUtil.isEmpty(examVo.getId())){
            throw new OperationException("所选的该场考试的id不能为空，请不要进行非法操作！");
        }
        StudentVo studentVo= (StudentVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME);
        Map<String,Object> map=examEbi.attendExam(examVo,studentVo);

        //学生作答情况
        List<StudentExamQuestionVo> studentExamQuestionList= (List<StudentExamQuestionVo>) map.get("studentExamQuestionList");
        //学生考试信息
        StudentExamVo studentExam= (StudentExamVo) map.get("studentExam");

        //TODO
        return null;
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

    /**
     * 查看个人成绩
     */
    @RequestMapping("checkMyGrages")
    public String checkMyGrages(ExamVo examVo,HttpServletRequest request) throws Exception
    {
        StudentVo login= (StudentVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME);
        StudentExamVo studentExamVo=examEbi.getStudentExam(examVo,login);
        request.setAttribute("studentExam",studentExamVo);
        return null;
    }
}
