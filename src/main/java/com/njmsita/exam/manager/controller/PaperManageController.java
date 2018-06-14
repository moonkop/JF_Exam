package com.njmsita.exam.manager.controller;

import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.authentic.service.ebi.TeacherEbi;
import com.njmsita.exam.manager.model.PaperVo;
import com.njmsita.exam.manager.model.QuestionVo;
import com.njmsita.exam.manager.model.SubjectVo;
import com.njmsita.exam.manager.model.querymodel.PaperQueryModel;
import com.njmsita.exam.manager.service.ebi.PaperEbi;
import com.njmsita.exam.manager.service.ebi.QuestionEbi;
import com.njmsita.exam.manager.service.ebi.SubjectEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.format.StringUtil;
import com.njmsita.exam.utils.idutil.IdUtil;
import com.njmsita.exam.utils.json.CustomJsonSerializer;
import com.njmsita.exam.utils.json.JsonListResponse;
import com.njmsita.exam.utils.json.JsonObjectResponse;
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
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 试卷管理控制器
 */
@Controller
@RequestMapping("/paper")
public class PaperManageController
{
    @Autowired
    private PaperEbi paperEbi;
    @Autowired
    private SubjectEbi subjectEbi;
    @Autowired
    private TeacherEbi teacherEbi;
    @Autowired
    private QuestionEbi questionEbi;


    /**
     * 跳转到试卷管理页面
     *
     * @return 跳转试卷列表
     */
    @RequestMapping("")
    public String toPaperList(Integer pageNum, Integer pageSize, HttpServletRequest request)
    {
        List<SubjectVo> subjectList = subjectEbi.getAll();
        request.setAttribute("subjectList", subjectList);
        return "/manage/paper/list";
    }


    /**
     * 试卷列表
     *
     * @param paperQueryModel 查询条件
     * @param pageNum         页码
     * @param pageSize        页面大小
     * @param request
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("list.do")
    public JsonResponse paperList(PaperQueryModel paperQueryModel, Integer pageNum, Integer pageSize,
                                  HttpServletRequest request)
    {
        TeacherVo teacherVo = (TeacherVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
        if (!teacherVo.getRole().getId().equals(SysConsts.ADMIN_ROLE_ID))
        {
            paperQueryModel.setTeacher(teacherVo);
        }
        return new JsonListResponse<>(paperEbi.getAll(paperQueryModel, pageNum, pageSize),
                "id,title,[teacher]teacher.name,[subject]subject.name", paperEbi.getCount(paperQueryModel));
    }

    @RequestMapping("detail")
    public String paperDetail(String id, HttpServletRequest request) throws OperationException
    {
        if (StringUtil.isEmpty(id))
        {
            throw new OperationException("所选试卷不存在，请不要进行非法操作！");
        }
        PaperVo paperVo = paperEbi.get(id);
        request.setAttribute("paper", paperVo);
        request.setAttribute("questionList", CustomJsonSerializer.toJsonString_static
                (
                        new JsonListResponse<QuestionVo>(paperVo.getQuestionList(),"id,outline,options,value,code,index,type,answer")
                                .list()
                )
        );
        return "/manage/paper/detail";
    }


    @RequestMapping("add")
    public String editPaper(HttpServletRequest request) throws Exception
    {
        request.setAttribute("subjectList", subjectEbi.getAll());
        return "/manage/paper/add";
    }


    @RequestMapping("add.do")
    @SystemLogAnnotation(module = "试卷管理", methods = "试卷添加/修改")
    public String paperAdd(PaperVo paperVo, BindingResult bindingResult,
                           HttpServletRequest request, int subject_id) throws OperationException
    {

        TeacherVo teacherVo1 = new TeacherVo();
        TeacherVo teacherVo = (TeacherVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
        teacherVo1.setId(teacherVo.getId());
        teacherVo1.setName(teacherVo.getName());
        teacherVo1.setRole(null);
        paperVo.setTeacher(teacherVo1);
        paperVo.setId(IdUtil.getUUID());
        SubjectVo subjectVo = new SubjectVo();
        subjectVo.setId(subject_id);
        paperVo.setSubject(subjectVo);
        paperEbi.save(paperVo);

        return "redirect:/paper/edit?id=" + paperVo.getId();
    }


    /**
     * 自动组卷
     *
     * @param topicIds       所选知识点
     * @param questionTypeId 题型
     * @param questionNum    所需题目数量
     * @param session
     *
     * @return
     *
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("autoSelect.do")
    public JsonResponse autoSelectQuestion(@RequestParam(value = "topicIds") String[] topicIds,
                                           Integer questionTypeId, Integer questionNum, HttpSession session) throws Exception
    {
        TeacherVo teacherVo = (TeacherVo) session.getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
        List list = questionEbi.autoSelectByTopicIdsAndType(topicIds, questionTypeId, questionNum, teacherVo);
        if (list.size() > questionNum)
        {
            return new JsonListResponse<QuestionVo>(list,
                    "id,code,outline,option,answer,[score]questionType.score,[questionType]qeustionType.id", 0);
        } else
        {
            return new JsonListResponse<QuestionVo>(list,
                    "id,code,outline,option,answer,[score]questionType.score,[questionType]qeustionType.id", 0)
                    .put("msg", "所设定条件下的题目仅有" + list.size() + "条，请继续选择！");
        }
    }

    /**
     * @param paperVo
     * @param request
     *
     * @return
     *
     * @throws Exception
     */
    @RequestMapping("edit")
    public String editPaper(PaperVo paperVo, HttpServletRequest request) throws Exception
    {
        if (StringUtil.isEmpty(paperVo.getId()))
        {
            throw new OperationException("所选试卷不存在，请不要进行非法操作！");
        }
        paperVo = paperEbi.get(paperVo.getId());
        request.setAttribute("paper", paperVo);
        JsonListResponse<QuestionVo> response = new JsonListResponse<QuestionVo>(paperVo.getQuestionList(),
                "id,outline,options,value,code,index,type,answer", 0);
        request.setAttribute("questionList", CustomJsonSerializer.toJsonString_static(response.getPayload().get("rows")));
        return "/manage/paper/edit";
    }

    @ResponseBody
    @RequestMapping("edit.do")
    @SystemLogAnnotation(module = "试卷管理", methods = "试卷修改")
    public JsonResponse paperDoAdd(@RequestBody PaperVo paperVo) throws OperationException
    {
        paperEbi.update(paperVo);
        return new JsonResponse();
    }

    /**
     * 试卷删除
     *
     * @param paperVo
     *
     * @return
     *
     * @throws OperationException
     */
    @ResponseBody
    @RequestMapping("delete.do")
    @SystemLogAnnotation(module = "试卷管理", methods = "试卷删除")
    public JsonResponse paperDelete(PaperVo paperVo) throws OperationException
    {
        if (!StringUtil.isEmpty(paperVo.getId()))
        {
            paperEbi.delete(paperVo);
        }
        return new JsonResponse("删除成功");
    }

    @ResponseBody
    @RequestMapping("getPaper.do")
    public JsonResponse getPaper(String id)
    {
        return new JsonObjectResponse<PaperVo>(paperEbi.get(id), "title,comment,id,questionList");
    }
}
