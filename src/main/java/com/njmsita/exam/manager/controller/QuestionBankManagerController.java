package com.njmsita.exam.manager.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.njmsita.exam.authentic.service.ebi.TeacherEbi;
import com.njmsita.exam.base.BaseController;
import com.njmsita.exam.manager.model.QuestionTypeVo;
import com.njmsita.exam.manager.model.QuestionVo;
import com.njmsita.exam.manager.model.SubjectVo;
import com.njmsita.exam.manager.model.TopicVo;
import com.njmsita.exam.manager.model.querymodel.QuestionQueryModel;
import com.njmsita.exam.manager.model.querymodel.QuestionTypeQueryModel;
import com.njmsita.exam.manager.model.querymodel.SubjectQueryModel;
import com.njmsita.exam.manager.model.querymodel.TopicQueryModel;
import com.njmsita.exam.manager.service.ebi.QuestionEbi;
import com.njmsita.exam.manager.service.ebi.QuestionTypeEbi;
import com.njmsita.exam.manager.service.ebi.SubjectEbi;
import com.njmsita.exam.manager.service.ebi.TopicEbi;
import com.njmsita.exam.utils.json.JsonListResponse;
import com.njmsita.exam.utils.json.JsonResponse;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.json.CustomJsonSerializer;
import com.njmsita.exam.utils.format.StringUtil;
import com.njmsita.exam.utils.idutil.IdUtil;
import com.njmsita.exam.utils.logutils.SystemLogAnnotation;
import com.njmsita.exam.utils.ping4j.FirstCharUtil;
import com.njmsita.exam.utils.validate.validategroup.AddGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题库管理控制器
 */
@Controller
@RequestMapping("/bank/manage")
public class QuestionBankManagerController extends BaseController
{
    @Autowired
    private SubjectEbi subjectEbi;
    @Autowired
    private QuestionTypeEbi questionTypeEbi;
    @Autowired
    private TopicEbi topicEbi;
    @Autowired
    private QuestionEbi questionEbi;
    @Autowired
    private TeacherEbi teacherEbi;

//======================================================================================================================

    //-------------------------------------------SubjectManager----------------------------------------------
    //-------------------------------------------SubjectManager----------------------------------------------
    //-------------------------------------------SubjectManager----------------------------------------------
    //-------------------------------------------SubjectManager----------------------------------------------
    //-------------------------------------------SubjectManager----------------------------------------------
    //-------------------------------------------SubjectManager----------------------------------------------

    /**
     * 跳转学科页面(分页)
     *
     * @param subjectQueryModel 该模型存放了学科属性
     * @param pageNum           页码
     * @param pageSize          页面大小
     * @param model
     *
     * @return JSON{ rows: 内容（list） total: 查询结果总数 }
     */
    @ResponseBody
    @RequestMapping("subject/list.do")
    public JsonResponse toSubjectList(SubjectQueryModel subjectQueryModel, Integer pageNum, Integer pageSize, Model model)
    {
        return new JsonListResponse(
                subjectEbi.getAll(subjectQueryModel, pageNum, pageSize),
                "id,name",
                subjectEbi.getCount(subjectQueryModel));

    }

    /**
     * 跳转到学科管理页面
     *
     * @return 跳转学科管理
     */
    @RequestMapping("subject")
    public String toSubjectList()
    {
        return "manage/subject/list";
    }

    @RequestMapping("subject/detail")
    public String toSubjectdetail(Integer id, HttpServletRequest request)
    {
        SubjectVo SubjectVo = subjectEbi.get(id);
        request.setAttribute("subject", SubjectVo);
        return "manage/subject/detail";
    }


    /**
     * 跳转学科添加/修改页面
     * <p>
     * （此处将添加和修改页面合并，如果前台传递ID则进行修改否则进入添加页面）
     *
     * @param subject 接受前台传递的学科id
     * @param request HttpServletRequest
     *
     * @return 跳转edit
     */
    @RequestMapping("subject/edit")
    public String edit(SubjectVo subject, HttpServletRequest request)
    {

        //判断前台是否传递学科ID
        if (subject.getId() != null)
        {
            //根据学科ID获取学科完整信息从而进行数据回显
            subject = subjectEbi.get(subject.getId());
            request.setAttribute("subject", subject);
        }
        return "/manage/subject/edit";
    }

    /**
     * 添加学科
     *
     * @param subject 需要添加的信息
     *
     * @return 跳转学科列表页面
     */
    @RequestMapping("subject/edit.do")
    @SystemLogAnnotation(module = "学科管理", methods = "学科添加/修改")
    public String doAdd(@Validated(value = {AddGroup.class}) SubjectVo subject, BindingResult bindingResult,
                        HttpServletRequest request) throws Exception
    {
        if (bindingResult.hasErrors())
        {
            List<FieldError> list = bindingResult.getFieldErrors();
            for (FieldError fieldError : list)
            {
                //校验信息，key=属性名+Error
                request.setAttribute(fieldError.getField() + "Error", fieldError.getDefaultMessage());
            }
            request.setAttribute("subject", subject);
            return "redirect:/bank/manage/subject";
        }
        if (subject.getId() != null&&subject.getId()!=0)
        {
            subjectEbi.update(subject);
        } else
        {
            subjectEbi.save(subject);
        }
        return "redirect:/bank/manage/subject";
    }


    /**
     * 删除学科
     *
     * @param subject 需要删除的学科
     *
     * @return 跳转学科列表页面
     */
    @ResponseBody
    @RequestMapping("subject/delete.do")
    @SystemLogAnnotation(module = "学科管理", methods = "学科删除")
    public JsonResponse subjectDelete(SubjectVo subject) throws Exception
    {
        if (subject.getId() != 0&&subject.getId()!=null)
        {
            subjectEbi.delete(subject);
        }
        return new JsonResponse("删除成功");
    }
    //--------------------------------SubjectManager----------END--------------------------------------------
    //--------------------------------SubjectManager----------END--------------------------------------------
    //--------------------------------SubjectManager----------END--------------------------------------------
    //--------------------------------SubjectManager----------END--------------------------------------------
    //--------------------------------SubjectManager----------END--------------------------------------------
    //--------------------------------SubjectManager----------END--------------------------------------------

//======================================================================================================================

    //-------------------------------------------QuestionTypeManager-----------------------------------------
    //-------------------------------------------QuestionTypeManager-----------------------------------------
    //-------------------------------------------QuestionTypeManager-----------------------------------------
    //-------------------------------------------QuestionTypeManager-----------------------------------------
    //-------------------------------------------QuestionTypeManager-----------------------------------------
    //-------------------------------------------QuestionTypeManager-----------------------------------------

    /**
     * 跳转题型页面(分页)
     *
     * @param questionTypeQueryModel 该模型存放了题型属性
     * @param pageNum                页码
     * @param pageSize               页面大小
     * @param model
     *
     * @return JSON{ rows: 内容（list） total: 查询结果总数 }
     */
    //TODO 请求转发问题，没有pageNum   pageSize参数
    @RequestMapping("questionType/list.do")
    public String toQuestionTypeList(QuestionTypeQueryModel questionTypeQueryModel, Integer pageNum, Integer pageSize, Model model)
    {
        //调用BaseController的方法设置数据总量及最大页码数
        pageCount = pageSize;
        setDataTotal(questionTypeEbi.getCount(questionTypeQueryModel));

        //根据查询条件及指定页码查询
        List<QuestionTypeVo> questionTypeVoList = questionTypeEbi.getAll(questionTypeQueryModel, pageNum, pageSize);
        model.addAttribute("questionTypeVoList", questionTypeVoList);

        return "manage/questionType/list";
    }

    /**
     * 跳转到题型管理页面
     *
     * @return 跳转题型管理
     */
    @RequestMapping("questionType")
    public String toQuestionTypeList()
    {
        return "manage/questionType/list";
    }

    @RequestMapping("questionType/detail")
    public String toQuestionTypedetail(String id, HttpServletRequest request)
    {
        QuestionTypeVo questionTypeVo = questionTypeEbi.get(id);
        request.setAttribute("questionType", questionTypeVo);
        return "manage/questionType/detail";
    }


    /**
     * 跳转题型添加/修改页面
     * <p>
     * （此处将添加和修改页面合并，如果前台传递ID则进行修改否则进入添加页面）
     *
     * @param questionType 接受前台传递的题型id
     * @param request      HttpServletRequest
     *
     * @return 跳转edit
     */
    @RequestMapping("questionType/edit")
    public String edit(QuestionTypeVo questionType, HttpServletRequest request)
    {

        //判断前台是否传递题型ID
        if (questionType.getId() != 0&&questionType.getId()!=null)
        {
            //根据题型ID获取题型完整信息从而进行数据回显
            questionType = questionTypeEbi.get(questionType.getId());
            request.setAttribute("questionType", questionType);
        }
        return "/manage/questionType/edit";
    }

    /**
     * 添加题型
     *
     * @param questionType 需要添加的信息
     *
     * @return 跳转题型列表页面
     */
    @RequestMapping("questionType/edit.do")
    @SystemLogAnnotation(module = "题型管理", methods = "题型添加/修改")
    public String doAdd(@Validated(value = {AddGroup.class}) QuestionTypeVo questionType, BindingResult bindingResult,
                        HttpServletRequest request) throws Exception
    {
        if (bindingResult.hasErrors())
        {
            List<FieldError> list = bindingResult.getFieldErrors();
            for (FieldError fieldError : list)
            {
                //校验信息，key=属性名+Error
                request.setAttribute(fieldError.getField() + "Error", fieldError.getDefaultMessage());
            }
            request.setAttribute("questionType", questionType);
            return "/manage/questionType/edit";
        }
        if (questionType.getId() !=null&&questionType.getId()!=0)
        {
            questionTypeEbi.update(questionType);
        } else
        {
            questionTypeEbi.save(questionType);
        }
        return "redirect:/manage/questionType";
    }


    /**
     * 删除题型
     *
     * @param questionType 需要删除的题型
     *
     * @return 跳转题型列表页面
     */
    @ResponseBody
    @RequestMapping("questionType/delete.do")
    @SystemLogAnnotation(module = "题型管理", methods = "题型删除")
    public JsonResponse questionTypeDelete(QuestionTypeVo questionType) throws Exception
    {
        if (questionType.getId() != 0&&questionType.getId()!=null)
        {
            questionTypeEbi.delete(questionType);
        }
        return new JsonResponse("删除成功");
    }
    //--------------------------------QuestionTypeManager----------END-------------------------------------
    //--------------------------------QuestionTypeManager----------END-------------------------------------
    //--------------------------------QuestionTypeManager----------END-------------------------------------
    //--------------------------------QuestionTypeManager----------END-------------------------------------
    //--------------------------------QuestionTypeManager----------END-------------------------------------
    //--------------------------------QuestionTypeManager----------END-------------------------------------

//======================================================================================================================

    //-------------------------------------------TopicManager----------------------------------------------
    //-------------------------------------------TopicManager----------------------------------------------
    //-------------------------------------------TopicManager----------------------------------------------
    //-------------------------------------------TopicManager----------------------------------------------
    //-------------------------------------------TopicManager----------------------------------------------
    //-------------------------------------------TopicManager----------------------------------------------

    @RequestMapping("topic")
    public String totopicTree()
    {
        return "/manage/topic/tree";
    }

    @RequestMapping("topic/list1")
    public String totopicList()
    {
        return "/manage/topic/list";
    }

    @Deprecated
    @ResponseBody
    @RequestMapping("topic/list.do")
    public JsonNode topicList(TopicQueryModel topicQueryModel, Integer pageNum, Integer pageSize)
    {
        //创建自定义序列化器 并设置过滤器
        CustomJsonSerializer serializer = new CustomJsonSerializer(TopicVo.class, "id,name,url,remark", null);
        //创建返回值对象 json类型
        ObjectNode result = CustomJsonSerializer.getDefaultMapper().createObjectNode();
        List<TopicVo> topicVoList = topicEbi.getAll(topicQueryModel, pageNum, pageSize);
        //对象转换后存放的数组
        List<ObjectNode> rows = new ArrayList<>();
        for (TopicVo topicVo : topicVoList)
        {
            //自定义过滤序列化对象
            ObjectNode node = serializer.toJson_ObjectNode(topicVo);
            //添加额外的特殊属性
            node.put("subject", topicVo.getSubjectVo().getName());
            rows.add(node);
        }
        //将数组转换为json节点 并插入返回值对象
        result.put("rows", CustomJsonSerializer.toJson_JsonNode1(rows));
        result.put("total", topicEbi.getCount(topicQueryModel));
        return result;
    }




    @Deprecated
    @ResponseBody
    @RequestMapping("topic/tree.do")
    public List<ObjectNode> topicTree()
    {
        List<TopicVo> list = topicEbi.getAll();
        List<ObjectNode> rows = new ArrayList<>();
        for (TopicVo topicVo : list)
        {
            ObjectNode node = CustomJsonSerializer.getDefaultMapper().createObjectNode();
            if (topicVo.getParent() != null)
            {
                node.put("parent", topicVo.getParent().getId());
            } else
            {
                node.put("parent", "#");
            }
            node.put("id", topicVo.getId());
            node.put("text", topicVo.getName());
            rows.add(node);
        }
        return rows;
    }


    @ResponseBody
    @RequestMapping("topic/treeBySubject.do")
    public JsonResponse topicTreeBySubject(Integer subjectID)
    {
        //todo 根据学科查知识点树
        SubjectVo subjectVo = subjectEbi.get(subjectID);

        return new JsonListResponse<TopicVo>(
                topicEbi.getTopicBySubject(subjectVo),
                "id,[text]name,[parent]parent.name",
                0);

    }



    /**
     * 跳转知识点页面(分页)
     *
     * @param topicQueryModel 该模型存放了知识点属性
     * @param model
     * @param pageNum         页码
     * @param pageSize        页面大小
     *
     * @return
     */
    @RequestMapping("topic/list")

    public String totopicList(TopicQueryModel topicQueryModel, Model model, Integer pageNum, Integer pageSize)
    {

        //调用BaseController的方法设置数据总量及最大页码数
        pageCount = pageSize;
        setDataTotal(topicEbi.getCount(topicQueryModel));

        //根据查询条件和指定的页码查询
        List<TopicVo> topicList = topicEbi.getAll(topicQueryModel, pageNum, pageSize);
        model.addAttribute("topicList", topicList);

        return "manage/topic/list";
    }

    /**
     * 跳转知识点添加/修改页面
     * <p>
     * （此处将添加和修改页面合并，如果前台传递ID则进行修改否则进入添加页面）
     *
     * @param topicVo 接受前台传递的知识点id
     * @param request HttpServletRequest
     *
     * @return 跳转edit
     */
    @RequestMapping("topic/edit")
    public String topicEdit(TopicVo topicVo, HttpServletRequest request)
    {
        //判断前台是否传递知识点ID
        request.setAttribute("subject", subjectEbi.getAll());
        if (!StringUtil.isEmpty(topicVo.getId()))
        {
            //根据知识点ID获取知识点完整信息从而进行数据回显
            topicVo = topicEbi.get(topicVo.getId());
            request.setAttribute("parent", topicEbi.get(topicVo.getParent().getId()));
            request.setAttribute("topic", topicVo);
        }
        //如果待编辑知识点为空 则会传进来parent.id
        request.setAttribute("parent", topicEbi.get(topicVo.getParent().getId()));

        return "/manage/topic/edit";
    }

    /**
     * 添加知识点
     *
     * @param topicVo 需要添加的信息(必须包含学校id)
     *
     * @return 跳转知识点列表页面
     */
    @RequestMapping("topic/edit.do")
    @SystemLogAnnotation(module = "知识点管理", methods = "知识点添加/修改")
    public String topicDoAdd(@Validated(value = {AddGroup.class}) TopicVo topicVo, BindingResult bindingResult,
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
            request.setAttribute("topicVo", topicVo);
            return "/manage/topic/edit";
        }
        if (null == topicVo.getId() || "".equals(topicVo.getId().trim()))
        {
            topicVo.setId(FirstCharUtil.firstCharAndID(topicVo.getName()));
            topicEbi.save(topicVo);
        } else
        {
            topicEbi.update(topicVo);
        }
        return "redirect:/manage/bank/topic";
    }


    /**
     * 删除知识点
     *
     * @param topicVo 需要删除的知识点
     *
     * @return 跳转知识点列表页面
     */
    @ResponseBody
    @RequestMapping("topic/delete.do")
    @SystemLogAnnotation(module = "知识点管理", methods = "知识点删除")
    public JsonResponse topicDelete(TopicVo topicVo) throws OperationException
    {
        if (!StringUtil.isEmpty(topicVo.getId()))
        {
            topicEbi.delete(topicVo);
        }
        return new JsonResponse("删除成功");
    }
    //--------------------------------TopicManager----------END--------------------------------------------
    //--------------------------------TopicManager----------END--------------------------------------------
    //--------------------------------TopicManager----------END--------------------------------------------
    //--------------------------------TopicManager----------END--------------------------------------------
    //--------------------------------TopicManager----------END--------------------------------------------
    //--------------------------------TopicManager----------END--------------------------------------------

//======================================================================================================================

    //----------------------------------------QuestionManager----------------------------------------------
    //----------------------------------------QuestionManager----------------------------------------------
    //----------------------------------------QuestionManager----------------------------------------------
    //----------------------------------------QuestionManager----------------------------------------------
    //----------------------------------------QuestionManager----------------------------------------------
    //----------------------------------------QuestionManager----------------------------------------------
    @ResponseBody
    @RequestMapping("question/list.do")
    public JsonNode questionList(QuestionQueryModel questionQueryModel, Integer offset, Integer pageSize,
                                 HttpServletRequest request)
    {
        //创建自定义序列化器 并设置过滤器
        CustomJsonSerializer serializer = new CustomJsonSerializer(QuestionVo.class, "id,name,code,outline,option,answer", null);
        //创建返回值对象 json类型
        ObjectNode result = CustomJsonSerializer.getDefaultMapper().createObjectNode();
        List<QuestionVo> questionVoList = questionEbi.getAll(questionQueryModel, offset, pageSize);
        //对象转换后存放的数组
        List<ObjectNode> rows = new ArrayList<>();
        for (QuestionVo questionVo : questionVoList)
        {
            //自定义过滤序列化对象
            ObjectNode node = serializer.toJson_ObjectNode(questionVo);

            //添加额外的特殊属性
            node.put("questionType", questionVo.getQuestionType().getName());
            rows.add(node);
        }
        //将数组转换为json节点 并插入返回值对象
        result.put("rows", CustomJsonSerializer.toJson_JsonNode1(rows));
        result.put("total", questionEbi.getCount(questionQueryModel));
        return result;
    }

    @RequestMapping("question/topicList")
    @ResponseBody
    public JsonResponse getTopic(Integer subjectId,String parent)throws Exception{
        return new JsonListResponse<>(topicEbi.getBySubject(subjectId,parent),
                "id,name,[parent]parent.id",0);
    }

    @RequestMapping("question/edit.do")
    @ResponseBody
    public JsonResponse doAdd(@Validated(value = {AddGroup.class}) QuestionVo questionVo, BindingResult bindingResult){
        JsonResponse jsonResponse =new JsonResponse();
        if (bindingResult.hasErrors())
        {
            List<FieldError> list = bindingResult.getFieldErrors();
            for (FieldError fieldError : list)
            {
                //校验信息，key=属性名+Error
                jsonResponse.put(fieldError.getField() + "Error", fieldError.getDefaultMessage());
            }









            //操作是否成功
            jsonResponse.setCode(500);
        }
        return jsonResponse;
    }


    /**
     * 跳转到题目管理页面
     *
     * @return 跳转题目管理
     */
    @RequestMapping("question")
    public String toQuestionList(HttpServletRequest request)
    {
        //学科
        List<SubjectVo> subjectVoList = subjectEbi.getAll();
        request.setAttribute("subjectVoList", subjectVoList);
        return "manage/question/list";
    }


    //--------------------------------QuestionManager----------END--------------------------------------------
    //--------------------------------QuestionManager----------END--------------------------------------------
    //--------------------------------QuestionManager----------END--------------------------------------------
    //--------------------------------QuestionManager----------END--------------------------------------------
    //--------------------------------QuestionManager----------END--------------------------------------------
    //--------------------------------QuestionManager----------END--------------------------------------------
}
