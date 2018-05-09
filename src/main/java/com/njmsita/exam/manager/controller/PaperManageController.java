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
import com.njmsita.exam.utils.json.JsonListResponse;
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
    @RequestMapping("toList")
    public String toPaperList(Integer pageNum, Integer pageSize,HttpServletRequest request)
    {
        List<SubjectVo> subjectList=subjectEbi.getAll();
        List<PaperVo> paperList=paperEbi.getAll(new PaperQueryModel(),pageNum,pageSize);
        request.setAttribute("subjectList",subjectList);
        request.setAttribute("paperList",paperList);
        return "/paper/list";
    }

    @ResponseBody
    @RequestMapping("list.do")
    public JsonResponse paperList(PaperQueryModel paperQueryModel, Integer pageNum, Integer pageSize,
                                  HttpServletRequest request)
    {
        TeacherVo teacherVo= (TeacherVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME);
        if(!teacherVo.getTroleVo().getId().equals("0")){
            paperQueryModel.setTeacher(teacherVo);
        }
        return new JsonListResponse<>(paperEbi.getAll(paperQueryModel,pageNum,pageSize),
                "id,title,[teacher]teacher.id,[subject]subject.name",0);
    }

    @RequestMapping("toEdit")
    public String editPaper(PaperVo paperVo,HttpServletRequest request) throws Exception{
        request.setAttribute("subjectList",subjectEbi.getAll());
        if(!StringUtil.isEmpty(paperVo.getId())){
            paperVo=paperEbi.get(paperVo.getId());
            if(paperVo==null){
                throw new OperationException("所选试卷不存在，请不要进行非法操作！");
            }
            request.setAttribute("paperVo",paperVo);
        }
        return "/paper/eidt";
    }

    @ResponseBody
    @RequestMapping("autoSelect")
    public JsonResponse autoSelectQuestion(@RequestParam(value = "topicIds[]") String[] topicIds,
           Integer questionTypeId, Integer questionNum, HttpSession session)throws Exception
    {
        TeacherVo teacherVo= (TeacherVo) session.getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME);
        List list=questionEbi.autoSelectByTopicIdsAndType(topicIds,questionTypeId,questionNum,teacherVo);
        if(list.size()>questionNum){
            return new JsonListResponse<QuestionVo>(list,
                    "id,code,outline,option,answer,[score]questionType.score",0);
        }else {
            return new JsonListResponse<QuestionVo>(list,
                    "id,code,outline,option,answer,[score]questionType.score",0)
                    .put("msg","所设定条件下的题目仅有"+list.size()+"条，请继续选择！");
        }
    }

    @RequestMapping("paper/edit.do")
    @SystemLogAnnotation(module = "试卷管理", methods = "试卷添加/修改")
    public String paperDoAdd(@Validated(value = {AddGroup.class}) PaperVo paperVo, BindingResult bindingResult,
                             HttpServletRequest request) throws OperationException
    {
        if (bindingResult.hasErrors())
        {
            List<FieldError> list = bindingResult.getFieldErrors();
            for (FieldError fieldError : list)
            {
                //校验信息，key=属性名+Error
                request.setAttribute(fieldError.getField() + "Error", fieldError.getDefaultMessage());
            }
            request.setAttribute("paperVo", paperVo);
            return "/paper/edit";
        }
        if (StringUtil.isEmpty(paperVo.getId()))
        {
            TeacherVo teacherVo= (TeacherVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME);
            paperVo.setTeacher(teacherVo);
            paperVo.setId(IdUtil.getUUID());
            paperEbi.save(paperVo);
        } else
        {

            paperEbi.update(paperVo);
        }
        return "redirect:/paper/toList";
    }
}
