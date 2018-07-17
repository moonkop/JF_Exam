package com.njmsita.exam.manager.controller;

import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.authentic.service.ebi.TeacherEbi;
import com.njmsita.exam.base.BaseController;
import com.njmsita.exam.manager.model.*;
import com.njmsita.exam.manager.model.querymodel.ExamEditWrapper;
import com.njmsita.exam.manager.model.querymodel.ExamListQueryModel;
import com.njmsita.exam.manager.service.ebi.ClassroomEbi;
import com.njmsita.exam.manager.service.ebi.ExamManageEbi;
import com.njmsita.exam.manager.service.ebi.PaperEbi;
import com.njmsita.exam.manager.service.ebi.SubjectEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.format.StringUtil;
import com.njmsita.exam.utils.json.CustomJsonSerializer;
import com.njmsita.exam.utils.json.JsonListObjectMapper;
import com.njmsita.exam.utils.json.JsonListResponse;
import com.njmsita.exam.utils.json.JsonResponse;
import com.njmsita.exam.utils.logutils.SystemLogAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 考试管理控制器
 */
@Controller
@RequestMapping("/exam/manage")
public class ExamManageController extends BaseController
{
    @Autowired
    private ExamManageEbi examManageEbi;
    @Autowired
    private SubjectEbi subjectEbi;
    @Autowired
    private PaperEbi paperEbi;
    @Autowired
    private TeacherEbi teacherEbi;
    @Autowired
    private ClassroomEbi classroomEbi;

    /**
     * 到编辑页面
     *
     * @param id
     * @param paperId
     * @param request
     *
     * @return
     *
     * @throws OperationException
     */
    @RequestMapping("edit")
    public String toEdit(String id, String paperId, HttpServletRequest request) throws Exception
    {
        request.setAttribute("papers", paperEbi.getAll());
        request.setAttribute("subjects", subjectEbi.getAll());
        request.setAttribute("classroomList", classroomEbi.getAll());
        if (!StringUtil.isEmpty(id))
        {
            ExamVo examVo = examManageEbi.getWithPaper(id);
            examManageEbi.checkPermission(SysConsts.EXAM_OPERATION_EDIT, (TeacherVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME), examVo);
            if (examVo == null)
            {
                throw new OperationException("所选的该场考试不能为空，请不要进行非法操作！");
            }
            request.setAttribute("exam", examVo);
        } else if (!StringUtil.isEmpty(paperId))
        {
            PaperVo tempPaper = paperEbi.get(paperId);
            if (tempPaper == null)
            {
                throw new OperationException("所选择的试卷不存在，请不要进行非法操作！");
            }
            request.setAttribute("paper", tempPaper);
            request.setAttribute("SelectedSubject", tempPaper.getSubject());
        }
        return "/manage/exam/edit";
    }

    @RequestMapping("detail")
    public String examDetail(String id, HttpServletRequest request) throws Exception
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
                    )
            );
        }
        return "/manage/exam/detail";
    }

    /**
     * 编辑
     *
     * @param bindingResult
     * @param request
     *
     * @return
     *
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("edit.do")
    @SystemLogAnnotation(module = "考试管理", methods = "发起/修改考试")
    public JsonResponse examDoAdd(@RequestBody ExamEditWrapper wrapper, HttpServletRequest request, BindingResult bindingResult) throws Exception
    {

        JsonResponse response = new JsonResponse();
        CheckErrorFields(bindingResult);
        TeacherVo loginTeacher = (TeacherVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
        if (StringUtil.isEmpty(wrapper.getExam().getId()))
        {
            examManageEbi.save(wrapper, loginTeacher);
        } else
        {
            examManageEbi.update(wrapper, loginTeacher);
        }
        return response;
    }

    /**
     * 删除
     *
     * @param examVo
     * @param request
     *
     * @return
     *
     * @throws Exception
     */
    @RequestMapping("delete")
    @ResponseBody
    @SystemLogAnnotation(module = "考试管理", methods = "删除考试")
    public JsonResponse delete(ExamVo examVo, HttpServletRequest request) throws Exception
    {
        if (StringUtil.isEmpty(examVo.getId()))
        {
            throw new OperationException("所选的该场考试的id不能为空");
        }
        examManageEbi.deleteCanceled(examVo, (TeacherVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME));
        return new JsonResponse("删除成功");
    }

    @RequestMapping("stop.do")
    @ResponseBody
    @SystemLogAnnotation(module = "考试管理", methods = "终止考试")
    public JsonResponse stop(@RequestParam(name = "id") String examId, HttpServletRequest request) throws Exception
    {
        if (StringUtil.isEmpty(examId))
        {
            throw new OperationException("所选的该场考试的id不能为空");
        }
        examManageEbi.stop(examId, (TeacherVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME));

        return new JsonResponse("终止成功");
    }

    /**
     * 到列表页面
     *
     * @param request
     *
     * @return
     */
    @RequestMapping("")
    public String toList(HttpServletRequest request)
    {

        request.setAttribute("subjectList", subjectEbi.getAll());
        request.setAttribute("teacherList", teacherEbi.getAll());
        return "/manage/exam/list";
    }

    /**
     * 列表
     *
     * @param examListQueryModel 查询条件（考试开始区间  发起教师  科目  考试状态）--->考试开始区间分为：startTime endTime
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("list.do")
    public JsonResponse list(ExamListQueryModel examListQueryModel, HttpServletRequest request) throws Exception
    {
        TeacherVo login = (TeacherVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
        List<ExamVo> list = examManageEbi.getAllByAdmin(login.getId(), examListQueryModel);
        return new JsonListResponse<ExamVo>(list,
                "id,name,openTime,duration,remark,operation,[teacher]getCreateTeacher().getName(),[subject]subject.name,examStatusView", examManageEbi.getCount(examListQueryModel));
    }


}
