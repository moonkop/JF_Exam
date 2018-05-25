package com.njmsita.exam.manager.controller;

import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.authentic.service.ebi.TeacherEbi;
import com.njmsita.exam.manager.model.*;
import com.njmsita.exam.manager.model.querymodel.ExamQueryModel;
import com.njmsita.exam.manager.service.ebi.ClassroomEbi;
import com.njmsita.exam.manager.service.ebi.ExamEbi;
import com.njmsita.exam.manager.service.ebi.PaperEbi;
import com.njmsita.exam.manager.service.ebi.SubjectEbi;
import com.njmsita.exam.manager.service.ebo.ExamEbo;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.format.StringUtil;
import com.njmsita.exam.utils.idutil.IdUtil;
import com.njmsita.exam.utils.json.CustomJsonElementFormater;
import com.njmsita.exam.utils.json.CustomJsonSerializer;
import com.njmsita.exam.utils.json.JsonListResponse;
import com.njmsita.exam.utils.json.JsonResponse;
import com.njmsita.exam.utils.logutils.SystemLogAnnotation;
import com.njmsita.exam.utils.validate.validategroup.AddGroup;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    @Autowired
    private ClassroomEbi classroomEbi;

    /**
     * 到编辑页面
     *
     * @param examVo
     * @param paperVo
     * @param request
     *
     * @return
     *
     * @throws OperationException
     */
    @RequestMapping("edit")
    public String toEdit(String id, String paperId, HttpServletRequest request) throws OperationException
    {
        request.setAttribute("papers", paperEbi.getAll());
        request.setAttribute("subjects", subjectEbi.getAll());
        request.setAttribute("teachers", teacherEbi.getAll());
        request.setAttribute("classrooms", classroomEbi.getAll());
        if (!StringUtil.isEmpty(id))
        {
            ExamVo examVo = examEbi.get(id);
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
    public String examDetail(String id, HttpServletRequest request) throws OperationException
    {
        if (StringUtil.isEmpty(id))
        {
            throw new OperationException("该考试不存在");
        }

        ExamVo examVo = examEbi.get(id);
        Hibernate.initialize(examVo.getCreateTeacher());
        if (examVo == null)
        {
            throw new OperationException("该考试不存在");
        }
        request.setAttribute("exam", examVo);
        if (examVo.getPaperVo() != null)
        {
            request.setAttribute("paper", examVo.getPaperVo());
            request.setAttribute("questionList", CustomJsonSerializer.toJsonString_static
                    (
                            new JsonListResponse<QuestionVo>(examVo.getPaperVo().getQuestionList(), "id,outline,options,value,code,index,type,answer")
                                    .getJsonList()
                    )
            );
        }

        return "/manage/exam/detail";
    }

    /**
     * 编辑
     *
     * @param examVo
     * @param bindingResult
     * @param markTeachers
     * @param paperId
     * @param classroomIds
     * @param request
     *
     * @return
     *
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("edit.do")
    @SystemLogAnnotation(module = "考试管理", methods = "发起/修改考试")
    public JsonResponse examDoAdd(@Validated(value = {AddGroup.class}) ExamVo examVo, BindingResult bindingResult,
                            @RequestParam(name = "markTeachers[]", required = false) String[] markTeachers, String paperId,
                            @RequestParam(name = "_classroomIds[]", required = false) String[] classroomIds,
                            HttpServletRequest request) throws Exception
    {
        JsonResponse response = new JsonResponse();
        if (bindingResult.hasErrors())
        {
            List<FieldError> list = bindingResult.getFieldErrors();
            for (FieldError fieldError : list)
            {
                //校验信息，key=属性名+Error
                response.put(fieldError.getField() + "Error", fieldError.getDefaultMessage());
            }
            return response.setCode(417);
        }
        TeacherVo login = (TeacherVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME);
        if (StringUtil.isEmpty(examVo.getId()))
        {
            examVo.setCreateTeacher(login);
            examVo.setId(IdUtil.getUUID());
            examEbi.save(examVo, markTeachers, paperId, classroomIds);
        } else
        {
            examEbi.update(examVo, markTeachers, paperId, classroomIds);
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
            throw new OperationException("所选的该场考试的id不能为空，请不要进行非法操作！");
        }
        examEbi.deleteCancel(examVo, (TeacherVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME));
        return new JsonResponse("删除成功");
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
        return "manage/exam/list";
    }

    /**
     * 列表
     *
     * @param examQueryModel 查询条件（考试开始区间  发起教师  科目  考试状态）--->考试开始区间分为：startTime endTime
     * @param pageNum
     * @param pageSize
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("list.do")
    public JsonResponse list(ExamQueryModel examQueryModel, Integer pageNum, Integer pageSize,
                             HttpServletRequest request) throws Exception
    {
        TeacherVo login = (TeacherVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME);
        List<ExamVo> list = examEbi.getAllByAdmin(login.getId(), examQueryModel, pageNum, pageSize);
        return new JsonListResponse<ExamVo>(list,
                "id,name,openTime,duration,remark,operation,[teacher]getCreateTeacher().getName(),[subject]subject.name,examStatusView", examEbi.getCount(examQueryModel));
    }

}
