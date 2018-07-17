package com.njmsita.exam.manager.controller;

import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.authentic.service.ebi.TeacherEbi;
import com.njmsita.exam.base.BaseController;
import com.njmsita.exam.manager.model.QuestionTypeVo;
import com.njmsita.exam.manager.model.QuestionVo;
import com.njmsita.exam.manager.model.SubjectVo;
import com.njmsita.exam.manager.model.TopicVo;
import com.njmsita.exam.manager.model.querymodel.QuestionQueryModel;
import com.njmsita.exam.manager.model.querymodel.QuestionTypeQueryModel;
import com.njmsita.exam.manager.model.querymodel.SubjectQueryModel;
import com.njmsita.exam.manager.service.ebi.QuestionEbi;
import com.njmsita.exam.manager.service.ebi.QuestionTypeEbi;
import com.njmsita.exam.manager.service.ebi.SubjectEbi;
import com.njmsita.exam.manager.service.ebi.TopicEbi;
import com.njmsita.exam.utils.exception.UnAuthorizedException;
import com.njmsita.exam.utils.exception.UnLoginException;
import com.njmsita.exam.utils.json.JsonListResponse;
import com.njmsita.exam.utils.json.JsonObjectResponse;
import com.njmsita.exam.utils.json.JsonResponse;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.format.StringUtil;
import com.njmsita.exam.utils.idutil.IdUtil;
import com.njmsita.exam.utils.logutils.SystemLogAnnotation;
import com.njmsita.exam.utils.ping4j.FirstCharUtil;
import com.njmsita.exam.utils.validate.validategroup.AddGroup;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 题库管理控制器
 */
@Controller
@RequestMapping("/manage/bank")
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

    @Autowired
    private MongoTemplate mongoTemplate;


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
        return "/manage/bank/subject/list";
    }

    @RequestMapping("subject/detail")
    public String toSubjectdetail(Integer id, HttpServletRequest request)
    {
        SubjectVo SubjectVo = subjectEbi.get(id);
        request.setAttribute("subject", SubjectVo);
        return "/manage/bank/subject/detail";
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
        return "/manage/bank/subject/edit";
    }

    /**
     * 添加学科
     *
     * @param subject 需要添加的信息
     *
     * @return 跳转学科列表页面
     */
    @ResponseBody
    @RequestMapping("subject/edit.do")
    @SystemLogAnnotation(module = "学科管理", methods = "学科添加/修改")
    public JsonResponse doAdd(@Validated(value = {AddGroup.class}) SubjectVo subject, BindingResult bindingResult,
                        HttpServletRequest request) throws Exception
    {
        JsonResponse response = new JsonResponse();
        CheckErrorFields(bindingResult);
        if (subject.getId() != null && subject.getId() != 0)
        {
            subjectEbi.update(subject);
        } else
        {
            //todo 添加科目的同时添加该科目的根知识点
            subjectEbi.save(subject);
        }
        return response;
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
        if (subject.getId() != 0 && subject.getId() != null)
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
    @ResponseBody
    @RequestMapping("questionType/list.do")
    public JsonListResponse toQuestionTypeList(QuestionTypeQueryModel questionTypeQueryModel, Integer pageNum, Integer pageSize, Model model)
    {

        return new JsonListResponse(questionTypeEbi.getAll(questionTypeQueryModel, pageNum, pageSize),
                "id,name,score", questionTypeEbi.getCount(questionTypeQueryModel));

    }

    /**
     * 跳转到题型管理页面
     *
     * @return 跳转题型管理
     */
    @RequestMapping("questionType")
    public String toQuestionTypeList()
    {
        return "/manage/bank/questionType/list";
    }

    @RequestMapping("questionType/detail")
    public String toQuestionTypedetail(Integer id, HttpServletRequest request)
    {
        QuestionTypeVo questionTypeVo = questionTypeEbi.get(id);
        request.setAttribute("questionType", questionTypeVo);
        return "/manage/bank/questionType/detail";
    }

    /**
     * 跳转题型添加/修改页面
     * <p>
     * （此处将添加和修改页面合并，如果前台传递ID则进行修改否则进入添加页面）
     *
     * @param id      接受前台传递的题型id
     * @param request HttpServletRequest
     *
     * @return 跳转edit
     */
    @RequestMapping("questionType/edit")
    public String edit(Integer id, HttpServletRequest request)
    {

        //判断前台是否传递题型ID
        if (id != null && id != 0)
        {
            //根据题型ID获取题型完整信息从而进行数据回显
            QuestionTypeVo questionType = questionTypeEbi.get(id);
            request.setAttribute("questionType", questionType);
        }
        return "/manage/bank/questionType/edit";
    }

    /**
     * 添加题型
     *
     * @param questionType 需要添加的信息
     *
     * @return 跳转题型列表页面
     */
    @ResponseBody
    @RequestMapping("questionType/edit.do")
    @SystemLogAnnotation(module = "题型管理", methods = "题型添加/修改")
    public JsonResponse doAdd(@Validated(value = {AddGroup.class}) QuestionTypeVo questionType, BindingResult bindingResult,
                        HttpServletRequest request) throws Exception
    {
        JsonResponse response = new JsonResponse();
        CheckErrorFields(bindingResult);
        if (questionType.getId() != null && questionType.getId() != 0)
        {
            questionTypeEbi.update(questionType);
        } else
        {
            questionTypeEbi.save(questionType);
        }
        return response;
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
        if (questionType.getId() != 0 && questionType.getId() != null)
        {
            questionTypeEbi.delete(questionType);
        }
        return new JsonResponse("删除成功");
    }

    @ResponseBody
    @RequestMapping("getQuestionType.do")
    public JsonResponse getQuestionType()
    {
        return new JsonListResponse<QuestionTypeVo>(questionTypeEbi.getAll(), "name,id", 0);
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
    public String totopicTree(Integer subjectID, HttpServletRequest request)
    {
        List<SubjectVo> subjects = subjectEbi.getAll();
        request.setAttribute("subjects", subjects);
        request.setAttribute("subjectSelected", subjectID);
        return "/manage/bank/topic/tree";
    }

    @ResponseBody
    @RequestMapping("topic/treeBySubject.do")
    public JsonResponse topicTreeBySubject(Integer subjectID) throws OperationException
    {
        return new JsonListResponse<TopicVo>(
                topicEbi.getBySubject(subjectID, ""),
                "id,[text]name,[parent]parent.id",
                0, true).addNullValue("parent", "#").serialize();
    }

    @ResponseBody
    @RequestMapping("topic/move.do")
    public JsonResponse topicMove(String id, String parent) throws OperationException
    {
        TopicVo topicVo = topicEbi.get(id);
        TopicVo parentVo = topicEbi.get(parent);
        topicVo.setParent(parentVo);
        topicEbi.update(topicVo);
        return new JsonResponse();
    }

    @ResponseBody
    @RequestMapping("topic/rename.do")
    public JsonResponse topicRename(String id, String text) throws OperationException
    {
        TopicVo topicVo = topicEbi.get(id);
        topicVo.setName(text);
        topicEbi.update(topicVo);
        return new JsonResponse();
    }

    @ResponseBody
    @RequestMapping("topic/create.do")
    public JsonResponse topicCreate(String parent, String name) throws OperationException
    {
        TopicVo topicVo = new TopicVo();
        TopicVo parentVo = topicEbi.get(parent);
        topicVo.setName(name);
        topicVo.setId(FirstCharUtil.firstCharAndID(topicVo.getName()));
        topicVo.setParent(parentVo);
        topicVo.setSubjectVo(parentVo.getSubjectVo());
        topicEbi.save(topicVo);
        return new JsonResponse();
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

    /**
     * 查看题库
     *
     * @param questionQueryModel
     * @param offset
     * @param pageSize
     * @param request
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("question/list.do")
    public JsonResponse questionList(QuestionQueryModel questionQueryModel, @RequestParam(value = "_topicIds[]", required = false) String[] topicIds, Integer offset, Integer pageSize,
                                     HttpServletRequest request) throws UnLoginException
    {
        List<QuestionVo> list = null;
        TeacherVo currentTeacher = (TeacherVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
        if (currentTeacher == null)
        {
            throw new UnLoginException();
        }
        if (questionQueryModel.getQuestionType().getId() == 0)
        {
            questionQueryModel.setQuestionType(null);
        }
        if (questionQueryModel.getShowMe() == null)
        {
            questionQueryModel.setShowMe(false);
        }
        if (questionQueryModel.getTeacher().getId() == "")
        {
            questionQueryModel.setTeacher(null);
        }
        if (topicIds != null)
        {
            if (questionQueryModel.getRecursive() == true)
            {
                for (String topicID : topicIds)
                {
                    questionQueryModel.getTopicIds().addAll(topicEbi.getAllChildren(topicID));
                }
            } else
            {
                for (String topicID : topicIds)
                {
                    questionQueryModel.getTopicIds().add(topicID);
                }
            }
        }

        if (questionQueryModel.getShowMe())
        {
            questionQueryModel.setTeacher(currentTeacher);
            list = questionEbi.getAllByTeacher(questionQueryModel, offset, pageSize, currentTeacher);
        } else
        {
            //showMe==false
            if (currentTeacher.getRole().getId().equals(SysConsts.ADMIN_ROLE_ID))
            {
                list = questionEbi.getAll(questionQueryModel, offset, pageSize);
            } else
            {
                list = questionEbi.getAllByTeacher(questionQueryModel, offset, pageSize, currentTeacher);
            }
        }
        return new JsonListResponse<>(list, "id,code,outline,[options]option,answer,[type]questionType.id,[createTeacher]teacher.id", 0);
    }


    @ResponseBody
    @RequestMapping("question/detail.do")
    public JsonResponse questionDetail(String id)
    {

        return new JsonObjectResponse<QuestionVo>(questionEbi.get(id),
                "id," +
                        "code," +
                        "outline," +
                        "[options]option," +
                        "answer," +
                        "[type]questionType.id," +
                        "[value]questionType.score," +
                        "[createTeacher]teacher.name," +
                        "[createTeacherId]teacher.id," +
                        "createTime," +
                        "[subject]subject.name," +
                        "[topic]topic.name," +
                        "[topicid]topic.id," +
                        "isPrivate," +
                        "useTime");
    }

    /**
     * 获取科目的所有知识点
     *
     * @param subjectId
     * @param parent
     *
     * @return
     *
     * @throws Exception
     */
    @RequestMapping("question/topicList")
    @ResponseBody
    public JsonResponse getTopic(Integer subjectId, String parent) throws Exception
    {
        return new JsonListResponse<>(topicEbi.getBySubject(subjectId, parent),
                "id,name,[parent]parent.id", 0);
    }


    @ResponseBody
    @RequestMapping("question/saveAsMine.do")
    @SystemLogAnnotation(module = "题目管理", methods = "保存为我的题目")
    public JsonResponse saveAsMine(QuestionVo question, @RequestParam(value = "_options[]", required = false) String[] options, HttpSession session) throws OperationException
    {
        TeacherVo teacherVo = (TeacherVo) session.getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
        question.setOptionList(options);
        questionEbi.saveAsMine(question, teacherVo);
        return new JsonResponse();
    }

    @ResponseBody
    @RequestMapping("question/edit.do")
    @SystemLogAnnotation(module = "题目管理", methods = "题目编辑")
    public JsonResponse edit(QuestionVo question, @RequestParam(value = "_options[]", required = false) String[] options, HttpSession session) throws OperationException
    {
        TeacherVo teacherVo = (TeacherVo) session.getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
        question.setOptionList(options);
        questionEbi.update(question, teacherVo);
        return new JsonResponse("修改成功");
    }



    /**
     * 题目删除
     *
     * @param questionVo
     *
     * @return
     *
     * @throws OperationException
     */
    @ResponseBody
    @RequestMapping("question/delete.do")
    @SystemLogAnnotation(module = "题目管理", methods = "题目删除")
    public JsonResponse questionDelete(QuestionVo questionVo, HttpSession session) throws OperationException, UnLoginException, UnAuthorizedException
    {
        TeacherVo currentTeacher = (TeacherVo) session.getAttribute(SysConsts.USER_LOGIN_OBJECT_NAME);
        if (currentTeacher == null)
        {
            throw new UnLoginException();
        }
        if (StringUtil.isEmpty(questionVo.getId()))
        {
            throw new OperationException("输入参数不合法");
        }
        questionEbi.delete(questionVo, currentTeacher);
        return new JsonResponse("删除成功");
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
        List<TeacherVo> teacherVos = teacherEbi.getAll();

        request.setAttribute("subjects", subjectVoList);
        request.setAttribute("teachers", teacherVos);
        return "/manage/bank/question/list";
    }

    @RequestMapping("question/import.do")
    @SystemLogAnnotation(module = "题目管理", methods = "批量导入")
    public String inputXls(MultipartFile questionFile, Integer subjectId) throws Exception
    {
        if (questionFile != null)
        {
            if (SysConsts.INFO_BULK_INPUT_FILE_CONTENT_TYPE.equals(questionFile.getContentType()))
            {
                HSSFWorkbook workbook = new HSSFWorkbook(questionFile.getInputStream());
                HSSFSheet sheet = workbook.getSheetAt(0);
                questionEbi.bulkInputBySheet(sheet, subjectId);
            }
        }
        return "redirect:/manage/bank/question";
    }

    //--------------------------------QuestionManager----------END--------------------------------------------
    //--------------------------------QuestionManager----------END--------------------------------------------
    //--------------------------------QuestionManager----------END--------------------------------------------
    //--------------------------------QuestionManager----------END--------------------------------------------
    //--------------------------------QuestionManager----------END--------------------------------------------
    //--------------------------------QuestionManager----------END--------------------------------------------
}
