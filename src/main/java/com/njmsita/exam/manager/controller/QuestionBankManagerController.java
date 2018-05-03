package com.njmsita.exam.manager.controller;

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
import com.njmsita.exam.utils.format.StringUtil;
import com.njmsita.exam.utils.logutils.SystemLogAnnotation;
import com.njmsita.exam.utils.validate.validategroup.AddGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
     * @param pageNum          页码
     * @param pageSize         页面大小
     * @param model
     *
     * @return JSON{ rows: 内容（list） total: 查询结果总数 }
     */

    //TODO 请求转发问题，没有pageNum   pageSize参数
    @RequestMapping("subject/list.do")
    //@SystemLogAnnotation(module = "学科管理",methods = "列表查询")
    public String toSubjectList(SubjectQueryModel subjectQueryModel, Integer pageNum, Integer pageSize, Model model)
    {

        //调用BaseController的方法设置数据总量及最大页码数
        pageCount = pageSize;
        setDataTotal(subjectEbi.getCount(subjectQueryModel));

        //根据查询条件及指定页码查询
        List<SubjectVo> subjectVoList = subjectEbi.getAll(subjectQueryModel, pageNum, pageSize);
        model.addAttribute("subjectVoList", subjectVoList);

        return "manage/subject/list";
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
    public String toSubjectdetail(String id, HttpServletRequest request)
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
     * @param subject  接受前台传递的学科id
     * @param request HttpServletRequest
     *
     * @return 跳转edit
     */
    @RequestMapping("subject/edit")
    public String edit(SubjectVo subject, HttpServletRequest request)
    {

        //判断前台是否传递学科ID
        if (subject.getId()!=0)
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
    @SystemLogAnnotation(module = "学科管理",methods = "学科添加/修改")
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
            return "/manage/subject/edit";
        }
        if (subject.getId()!=0)
        {
            subjectEbi.save(subject);
        } else
        {
            subjectEbi.update(subject);
        }
        return "redirect:/manage/subject";
    }


    /**
     * 删除学科
     *
     * @param subject 需要删除的学科
     *
     * @return 跳转学科列表页面
     */
    @RequestMapping("subject/delete.do")
    @SystemLogAnnotation(module = "学科管理",methods = "学科删除")
    public String subjectDelete(SubjectVo subject) throws Exception
    {

        if (subject.getId()!=0)
        {

            subjectEbi.delete(subject);
        }

        return "redirect:/manage/subject";
    }
    //--------------------------------SubjectManager----------END--------------------------------------------
    //--------------------------------SubjectManager----------END--------------------------------------------
    //--------------------------------SubjectManager----------END--------------------------------------------
    //--------------------------------SubjectManager----------END--------------------------------------------
    //--------------------------------SubjectManager----------END--------------------------------------------
    //--------------------------------SubjectManager----------END--------------------------------------------

//======================================================================================================================

    //-------------------------------------------QuestionTypeManager----------------------------------------------
    //-------------------------------------------QuestionTypeManager----------------------------------------------
    //-------------------------------------------QuestionTypeManager----------------------------------------------
    //-------------------------------------------QuestionTypeManager----------------------------------------------
    //-------------------------------------------QuestionTypeManager----------------------------------------------
    //-------------------------------------------QuestionTypeManager----------------------------------------------

    /**
     * 跳转题型页面(分页)
     *
     * @param questionTypeQueryModel 该模型存放了题型属性
     * @param pageNum          页码
     * @param pageSize         页面大小
     * @param model
     *
     * @return JSON{ rows: 内容（list） total: 查询结果总数 }
     */

    //TODO 请求转发问题，没有pageNum   pageSize参数
    @RequestMapping("questionType/list.do")
    //@SystemLogAnnotation(module = "题型管理",methods = "列表查询")
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
     * @param questionType  接受前台传递的题型id
     * @param request HttpServletRequest
     *
     * @return 跳转edit
     */
    @RequestMapping("questionType/edit")
    public String edit(QuestionTypeVo questionType, HttpServletRequest request)
    {

        //判断前台是否传递题型ID
        if (questionType.getId()!=0)
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
    @SystemLogAnnotation(module = "题型管理",methods = "题型添加/修改")
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
        if (questionType.getId()!=0)
        {
            questionTypeEbi.save(questionType);
        } else
        {
            questionTypeEbi.update(questionType);
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
    @RequestMapping("questionType/delete.do")
    @SystemLogAnnotation(module = "题型管理",methods = "题型删除")
    public String questionTypeDelete(QuestionTypeVo questionType) throws Exception
    {

        if (questionType.getId()!=0)
        {

            questionTypeEbi.delete(questionType);
        }

        return "redirect:/manage/questionType";
    }
    //--------------------------------QuestionTypeManager----------END--------------------------------------------
    //--------------------------------QuestionTypeManager----------END--------------------------------------------
    //--------------------------------QuestionTypeManager----------END--------------------------------------------
    //--------------------------------QuestionTypeManager----------END--------------------------------------------
    //--------------------------------QuestionTypeManager----------END--------------------------------------------
    //--------------------------------QuestionTypeManager----------END--------------------------------------------

//======================================================================================================================

    //-------------------------------------------TopicManager----------------------------------------------
    //-------------------------------------------TopicManager----------------------------------------------
    //-------------------------------------------TopicManager----------------------------------------------
    //-------------------------------------------TopicManager----------------------------------------------
    //-------------------------------------------TopicManager----------------------------------------------
    //-------------------------------------------TopicManager----------------------------------------------

    /**
     * 跳转知识点页面(分页)
     *
     * @param topicQueryModel 该模型存放了知识点属性
     * @param pageNum          页码
     * @param pageSize         页面大小
     * @param model
     *
     * @return JSON{ rows: 内容（list） total: 查询结果总数 }
     */

    //TODO 请求转发问题，没有pageNum   pageSize参数
    @RequestMapping("topic/list.do")
    //@SystemLogAnnotation(module = "知识点管理",methods = "列表查询")
    public String toTopicList(TopicQueryModel topicQueryModel, Integer pageNum, Integer pageSize, Model model)
    {

        //调用BaseController的方法设置数据总量及最大页码数
        pageCount = pageSize;
        setDataTotal(topicEbi.getCount(topicQueryModel));

        //根据查询条件及指定页码查询
        List<TopicVo> topicVoList = topicEbi.getAll(topicQueryModel, pageNum, pageSize);
        model.addAttribute("TopicVoList", topicVoList);

        return "manage/topic/list";
    }

    /**
     * 跳转到知识点管理页面
     *
     * @return 跳转知识点管理
     */
    @RequestMapping("topic")
    public String toTopicList()
    {
        return "manage/topic/list";
    }

    @RequestMapping("topic/detail")
    public String toTopicdetail(String id, HttpServletRequest request)
    {
        TopicVo topicVo = topicEbi.get(id);
        request.setAttribute("topic", topicVo);
        return "manage/topic/detail";
    }


    /**
     * 跳转知识点添加/修改页面
     * <p>
     * （此处将添加和修改页面合并，如果前台传递ID则进行修改否则进入添加页面）
     *
     * @param topic  接受前台传递的知识点id
     * @param request HttpServletRequest
     *
     * @return 跳转edit
     */
    @RequestMapping("topic/edit")
    public String edit(TopicVo topic, HttpServletRequest request)
    {

        //判断前台是否传递知识点ID
        if (StringUtil.isEmpty(topic.getId()))
        {
            //根据知识点ID获取知识点完整信息从而进行数据回显
            topic = topicEbi.get(topic.getId());
            request.setAttribute("topic", topic);
        }
        return "/manage/topic/edit";
    }

    /**
     * 添加知识点
     *
     * @param topic 需要添加的信息
     *
     * @return 跳转知识点列表页面
     */
    @RequestMapping("topic/edit.do")
    @SystemLogAnnotation(module = "知识点管理",methods = "知识点添加/修改")
    public String doAdd(@Validated(value = {AddGroup.class}) TopicVo topic, BindingResult bindingResult,
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
            request.setAttribute("topic", topic);
            return "/manage/topic/edit";
        }
        if (StringUtil.isEmpty(topic.getId()))
        {
            topicEbi.save(topic);
        } else
        {
            topicEbi.update(topic);
        }
        return "redirect:/manage/topic";
    }


    /**
     * 删除知识点
     *
     * @param topic 需要删除的知识点
     *
     * @return 跳转知识点列表页面
     */
    @RequestMapping("topic/delete.do")
    @SystemLogAnnotation(module = "知识点管理",methods = "知识点删除")
    public String topicDelete(TopicVo topic) throws Exception
    {

        if (StringUtil.isEmpty(topic.getId()))
        {

            topicEbi.delete(topic);
        }

        return "redirect:/manage/topic";
    }
    //--------------------------------TopicManager----------END--------------------------------------------
    //--------------------------------TopicManager----------END--------------------------------------------
    //--------------------------------TopicManager----------END--------------------------------------------
    //--------------------------------TopicManager----------END--------------------------------------------
    //--------------------------------TopicManager----------END--------------------------------------------
    //--------------------------------TopicManager----------END--------------------------------------------

//======================================================================================================================

    //-------------------------------------------QuestionManager----------------------------------------------
    //-------------------------------------------QuestionManager----------------------------------------------
    //-------------------------------------------QuestionManager----------------------------------------------
    //-------------------------------------------QuestionManager----------------------------------------------
    //-------------------------------------------QuestionManager----------------------------------------------
    //-------------------------------------------QuestionManager----------------------------------------------

    /**
     * 跳转题目页面(分页)
     *
     * @param questionQueryModel 该模型存放了题目属性
     * @param pageNum          页码
     * @param pageSize         页面大小
     * @param model
     *
     * @return JSON{ rows: 内容（list） total: 查询结果总数 }
     */

    //TODO 请求转发问题，没有pageNum   pageSize参数
    @RequestMapping("question/list.do")
    //@SystemLogAnnotation(module = "题目管理",methods = "列表查询")
    public String toQuestionList(QuestionQueryModel questionQueryModel, Integer pageNum, Integer pageSize, Model model)
    {

        //调用BaseController的方法设置数据总量及最大页码数
        pageCount = pageSize;
        setDataTotal(questionEbi.getCount(questionQueryModel));

        //根据查询条件及指定页码查询
        List<QuestionVo> questionVoList = questionEbi.getAll(questionQueryModel, pageNum, pageSize);
        model.addAttribute("questionVoList", questionVoList);

        return "manage/question/list";
    }

    /**
     * 跳转到题目管理页面
     *
     * @return 跳转题目管理
     */
    @RequestMapping("question")
    public String toQuestionList()
    {
        return "manage/question/list";
    }

    @RequestMapping("question/detail")
    public String toQuestiondetail(String id, HttpServletRequest request)
    {
        QuestionVo questionVo = questionEbi.get(id);
        request.setAttribute("question", questionVo);
        return "manage/question/detail";
    }


    /**
     * 跳转题目添加/修改页面
     * <p>
     * （此处将添加和修改页面合并，如果前台传递ID则进行修改否则进入添加页面）
     *
     * @param question  接受前台传递的题目id
     * @param request HttpServletRequest
     *
     * @return 跳转edit
     */
    @RequestMapping("question/edit")
    public String edit(QuestionVo question, HttpServletRequest request)
    {

        //判断前台是否传递题目ID
        if (StringUtil.isEmpty(question.getId()))
        {
            //根据题目ID获取题目完整信息从而进行数据回显
            question = questionEbi.get(question.getId());
            request.setAttribute("question", question);
        }
        return "/manage/question/edit";
    }

    /**
     * 添加题目
     *
     * @param question 需要添加的信息
     *
     * @return 跳转题目列表页面
     */
    @RequestMapping("question/edit.do")
    @SystemLogAnnotation(module = "题目管理",methods = "题目添加/修改")
    public String doAdd(@Validated(value = {AddGroup.class}) QuestionVo question, BindingResult bindingResult,
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
            request.setAttribute("question", question);
            return "/manage/question/edit";
        }
        if (StringUtil.isEmpty(question.getId()))
        {
            questionEbi.save(question);
        } else
        {
            questionEbi.update(question);
        }
        return "redirect:/manage/question";
    }


    /**
     * 删除题目
     *
     * @param question 需要删除的题目
     *
     * @return 跳转题目列表页面
     */
    @RequestMapping("question/delete.do")
    @SystemLogAnnotation(module = "题目管理",methods = "题目删除")
    public String questionDelete(QuestionVo question) throws Exception
    {

        if (StringUtil.isEmpty(question.getId()))
        {

            questionEbi.delete(question);
        }

        return "redirect:/manage/question";
    }
    //--------------------------------QuestionManager----------END--------------------------------------------
    //--------------------------------QuestionManager----------END--------------------------------------------
    //--------------------------------QuestionManager----------END--------------------------------------------
    //--------------------------------QuestionManager----------END--------------------------------------------
    //--------------------------------QuestionManager----------END--------------------------------------------
    //--------------------------------QuestionManager----------END--------------------------------------------
}
