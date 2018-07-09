package com.njmsita.exam.manager.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.njmsita.exam.authentic.model.TresourceVo;
import com.njmsita.exam.authentic.model.TroleVo;
import com.njmsita.exam.authentic.model.querymodel.TroleQueryModel;
import com.njmsita.exam.authentic.service.ebi.ResourceEbi;
import com.njmsita.exam.authentic.service.ebi.ResourcetypeEbi;
import com.njmsita.exam.authentic.service.ebi.RoleEbi;
import com.njmsita.exam.manager.model.ClassroomVo;
import com.njmsita.exam.manager.model.SchoolVo;
import com.njmsita.exam.manager.model.querymodel.ClassroomQueryModel;
import com.njmsita.exam.manager.model.querymodel.LogQueryModel;
import com.njmsita.exam.manager.model.querymodel.SchoolQueryModel;
import com.njmsita.exam.manager.service.ebi.ClassroomEbi;
import com.njmsita.exam.manager.service.ebi.LogEbi;
import com.njmsita.exam.manager.service.ebi.SchoolEbi;
import com.njmsita.exam.base.BaseController;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.consts.ValidatedErrorUtil;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.json.CustomJsonSerializer;
import com.njmsita.exam.utils.json.JsonListResponse;
import com.njmsita.exam.utils.json.JsonResponse;
import com.njmsita.exam.utils.format.StringUtil;
import com.njmsita.exam.utils.logutils.SystemLogAnnotation;
import com.njmsita.exam.utils.validate.validategroup.AddGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * 系统管理控制器
 */
@Controller
@RequestMapping("/manage")
public class SystemManagerController extends BaseController
{
    @Autowired
    private SchoolEbi schoolEbi;
    @Autowired
    private RoleEbi roleEbi;
    @Autowired
    private ClassroomEbi classroomEbi;
    @Autowired
    private ResourceEbi resourceEbi;
    @Autowired
    private LogEbi logEbi;
    @Autowired
    private ResourcetypeEbi resourcetypeEbi;
//======================================================================================================================

    //-------------------------------------------SchoolManager----------------------------------------------
    //-------------------------------------------SchoolManager----------------------------------------------
    //-------------------------------------------SchoolManager----------------------------------------------
    //-------------------------------------------SchoolManager----------------------------------------------
    //-------------------------------------------SchoolManager----------------------------------------------
    //-------------------------------------------SchoolManager----------------------------------------------


    /**
     * 跳转到学校管理页面
     *
     * @return 跳转学校管理
     */
    @RequestMapping("school")
    public String toSchoolList()
    {
        return "/manage/school/list";
    }

    @ResponseBody
    @RequestMapping("school/list.do")
    public JsonResponse schoolList(SchoolQueryModel schoolQueryModel, Integer pageNum, Integer pageSize)
    {

        return new JsonListResponse<>(schoolEbi.getAll(schoolQueryModel, pageNum, pageSize),
                "id,name",
                schoolEbi.getCount(schoolQueryModel));

    }

    @RequestMapping("school/detail")
    public String toSchoodetail(String id, HttpServletRequest request)
    {
        SchoolVo schoolVo = schoolEbi.get(id);
        request.setAttribute("school", schoolVo);
        return "/manage/school/detail";
    }

    /**
     * 跳转学校添加/修改页面
     * <p>
     * （此处将添加和修改页面合并，如果前台传递ID则进行修改否则进入添加页面）
     *
     * @param school  接受前台传递的学校id
     * @param request HttpServletRequest
     * @return 跳转edit
     */
    @RequestMapping("school/edit")
    public String edit(SchoolVo school, HttpServletRequest request)
    {

        //判断前台是否传递学校ID
        if (null != school.getId() && !"".equals(school.getId().trim()))
        {
            //根据学校ID获取学校完整信息从而进行数据回显
            school = schoolEbi.get(school.getId());
            request.setAttribute("school", school);
        }
        // return "redirect:/manage/roleVo/list";
        return "/manage/school/edit";
    }

    /**
     * 添加学校
     *
     * @param school 需要添加的信息
     * @return 跳转学校列表页面
     */
    @RequestMapping("school/edit.do")
    @SystemLogAnnotation(module = "学校管理", methods = "学校添加/修改")
    @ResponseBody
    public JsonResponse doAdd(@Validated(value = {AddGroup.class}) SchoolVo school, BindingResult bindingResult,
                        HttpServletRequest request) throws OperationException
    {
        JsonResponse jsonResponse = new JsonResponse();
        if (bindingResult.hasErrors())
        {
            jsonResponse.setCode(400);
            jsonResponse.setPayload(ValidatedErrorUtil.getErrorMessage(bindingResult));
            return jsonResponse;
        }
        if (null == school.getId() || "".equals(school.getId())) //fixed empty
        {
            schoolEbi.save(school);
        } else
        {
            schoolEbi.update(school);
        }
        jsonResponse.setMessage("操作成功！");
        return jsonResponse;
    }
    /**
     * 删除学校
     *
     * @param school 需要删除的学校
     *
     * @return 跳转学校列表页面
     */
    @ResponseBody
    @RequestMapping("school/delete.do")
    @SystemLogAnnotation(module = "学校管理", methods = "学校删除")
    public JsonResponse schoolDelete(SchoolVo school) throws OperationException
    {
        if (!StringUtil.isEmpty(school.getId()))
        {
            schoolEbi.delete(school);
        }
        return new JsonResponse("删除成功");
    }
    //--------------------------------SchoolManager----------END--------------------------------------------
    //--------------------------------SchoolManager----------END--------------------------------------------
    //--------------------------------SchoolManager----------END--------------------------------------------
    //--------------------------------SchoolManager----------END--------------------------------------------
    //--------------------------------SchoolManager----------END--------------------------------------------

    //--------------------------------SchoolManager----------END--------------------------------------------

//======================================================================================================================
    //-----------------------------------------------RoleManager--------------------------------------------
    //-----------------------------------------------RoleManager--------------------------------------------
    //-----------------------------------------------RoleManager--------------------------------------------
    //-----------------------------------------------RoleManager--------------------------------------------
    //-----------------------------------------------RoleManager--------------------------------------------

    //-----------------------------------------------RoleManager--------------------------------------------

    @RequestMapping("role")
    public String toRoleList()
    {
        return "/manage/role/list";
    }

    @ResponseBody
    @RequestMapping("role/list.do")
    @SystemLogAnnotation(module = "角色管理", methods = "角色列表查询")
    public JsonResponse roleList(TroleQueryModel troleQueryModel, Integer pageNum, Integer pageSize)
    {
        return new JsonListResponse<>(
                roleEbi.getAll(troleQueryModel, pageNum, pageSize),
                "id,name",
                roleEbi.getCount(troleQueryModel));
    }

    @RequestMapping("role/detail")
    public String toRoleDetail(TroleVo troleVo, HttpServletRequest request)
    {
        request.setAttribute("role", roleEbi.get(troleVo.getId()));
        return "/manage/role/detail";
    }

    @ResponseBody
    @RequestMapping("roleResource/tree.do")
    @SystemLogAnnotation(module = "角色管理", methods = "角色资源查看")
    public List<ObjectNode> roleResourceList(TroleVo troleVo, boolean edit)
    {
        troleVo = roleEbi.get(troleVo.getId());
        List<ObjectNode> rows = new ArrayList<>();
        Set<TresourceVo> roleReses = troleVo.getReses();
        List<TresourceVo> allResources = resourceEbi.getAll();


        for (TresourceVo tresourceVo : allResources)
        {
            ObjectNode node = CustomJsonSerializer.getDefaultMapper().createObjectNode();

            boolean haveResource = roleReses.contains(tresourceVo);
            //如果是编辑 则传回所有资源 如果不是 则传回拥有的资源
            //如果不是编辑 且角色资源没有拥有这个资源 则不传回这个资源
            if (!edit && !haveResource)
            {
                continue;
            }
            if (edit && haveResource)
            {
                node.put("state", CustomJsonSerializer.getDefaultMapper().createObjectNode().put("selected", "true"));
            }
            if (tresourceVo.getParent() != null)
            {
                node.put("parent", tresourceVo.getParent().getId());
            } else
            {
                node.put("parent", "#");
            }
            node.put("id", tresourceVo.getId())
                    .put("text", tresourceVo.getName() + "    " + tresourceVo.getUrl());
            rows.add(node);
        }


        return rows;

    }


    /**
     * 跳转角色添加/修改页面
     * @param roleVo  接受前台传递的角色id
     * @param request HttpServletRequest
     * @return 跳转edit
     */
    @RequestMapping("role/edit")
    public String roleEdit(TroleVo roleVo, HttpServletRequest request)
    {
        //获取所有资源
        Set<TresourceVo> allReses = new HashSet<>(resourceEbi.getAll());
        request.setAttribute("allReses", allReses);

        //判断前台是否传递角色ID
        if (null != roleVo.getId() && !"".equals(roleVo.getId().trim()))
        {
            //根据角色ID获取角色完整信息从而进行数据回显
            roleVo = roleEbi.get(roleVo.getId());
            request.setAttribute("role", roleVo);
        }
        return "/manage/role/edit";
    }

    /**
     * 添加角色
     *
     * @param roleVo 需要添加的信息
     * @return 跳转角色列表页面
     */
    @ResponseBody
    @RequestMapping("role/edit.do")
    @SystemLogAnnotation(module = "角色管理", methods = "角色添加/修改")
    public JsonResponse roleDoAdd(@Validated(value = {AddGroup.class}) TroleVo roleVo, BindingResult bindingResult,
                                  @RequestParam(value = "resourceIds[]") String[] resourceIds) throws OperationException
    {
        JsonResponse response = new JsonResponse();
        if (GetJsonErrorFields(bindingResult, response))
        {
            return response.setCode(417);
        }
        if (null == roleVo.getId() || "".equals(roleVo.getId().trim()))
        {
            roleEbi.save(roleVo, resourceIds);
        } else
        {
            roleEbi.update(roleVo, resourceIds);
        }
        return response;
    }

    /**
     * 删除角色
     *
     * @param roleVo 需要删除的角色
     * @return 跳转角色列表页面
     */
    @RequestMapping("role/delete.do")
    @SystemLogAnnotation(module = "角色管理", methods = "角色删除")
    public String roleDelete(TroleVo roleVo) throws OperationException
    {

        if (null != roleVo.getId() && !"".equals(roleVo.getId().trim()))
        {
            roleEbi.delete(roleVo);
        }

        return "redirect:/manage/role";
    }

    //-----------------------------------RoleManager-----------END------------------------------------------
    //-----------------------------------RoleManager-----------END------------------------------------------
    //-----------------------------------RoleManager-----------END------------------------------------------
    //-----------------------------------RoleManager-----------END------------------------------------------
    //-----------------------------------RoleManager-----------END------------------------------------------
    //-----------------------------------RoleManager-----------END------------------------------------------

//======================================================================================================================

    //-----------------------------------------------ClassroomManager--------------------------------------------
    //-----------------------------------------------ClassroomManager--------------------------------------------
    //-----------------------------------------------ClassroomManager--------------------------------------------
    //-----------------------------------------------ClassroomManager--------------------------------------------
    //-----------------------------------------------ClassroomManager--------------------------------------------
    //-----------------------------------------------ClassroomManager--------------------------------------------

    @RequestMapping("classroom")
    public String toClassroomList()
    {
        return "/manage/classroom/list";
    }


    @ResponseBody
    @RequestMapping("classroom/list.do")
    public JsonResponse classroomList(ClassroomQueryModel classroomQueryModel, Integer pageNum, Integer pageSize)
    {
        return new JsonListResponse<>(
                classroomEbi.getAll(classroomQueryModel, pageNum, pageSize),
                "id,name,[school]schoolVo.name",
                classroomEbi.getCount(classroomQueryModel));

    }


    @RequestMapping("classroom/detail")
    public String toClassroomDetail(String id, HttpServletRequest request)
    {
        ClassroomVo classroomVo = classroomEbi.get(id);
        request.setAttribute("classroom", classroomVo);
        return "/manage/classroom/detail";
    }


    /**
     * 跳转班级添加/修改页面
     * <p>
     * （此处将添加和修改页面合并，如果前台传递ID则进行修改否则进入添加页面）
     *
     * @param classroomVo 接受前台传递的班级id
     * @param request     HttpServletRequest
     * @return 跳转edit
     */
    @RequestMapping("classroom/edit")
    public String classroomEdit(ClassroomVo classroomVo, HttpServletRequest request)
    {
        request.setAttribute("schools", schoolEbi.getAll());
        //判断前台是否传递班级ID
        if (null != classroomVo.getId() && !"".equals(classroomVo.getId().trim()))
        {
            //根据班级ID获取班级完整信息从而进行数据回显
            classroomVo = classroomEbi.get(classroomVo.getId());
            request.setAttribute("classroom", classroomVo);
        }
        return "/manage/classroom/edit";
    }

    /**
     * 添加班级
     *
     * @param classroomVo 需要添加的信息(必须包含学校id)
     * @return 跳转班级列表页面
     */
    @RequestMapping("classroom/edit.do")
    @ResponseBody
    @SystemLogAnnotation(module = "班级管理", methods = "班级添加/编辑")
    public JsonResponse classroomDoAdd(@Validated(value = {AddGroup.class}) ClassroomVo classroomVo, BindingResult bindingResult,
                                 HttpServletRequest request) throws OperationException
    {

        JsonResponse jsonResponse = new JsonResponse();
        if (bindingResult.hasErrors())
        {
            jsonResponse.setCode(400);
            jsonResponse.setPayload(ValidatedErrorUtil.getErrorMessage(bindingResult));
            return jsonResponse;
        }
        if (null == classroomVo.getId() || "".equals(classroomVo.getId().trim()))
        {
            classroomEbi.save(classroomVo);
        } else
        {
            classroomEbi.update(classroomVo);
        }
        jsonResponse.setMessage("操作成功！");
        return jsonResponse;
    }
    /**
     * 删除班级
     *
     * @param classroomVo 需要删除的班级
     * @return 跳转班级列表页面
     */
    @RequestMapping("classroom/delete")
    @SystemLogAnnotation(module = "班级管理", methods = "班级删除")
    public String classroomDelete(ClassroomVo classroomVo) throws OperationException
    {

        if (null != classroomVo.getId())
        {

            classroomEbi.delete(classroomVo);
        }

        return "redirect:/manage/classroom";
    }

    @ResponseBody
    @RequestMapping("getClassroomBySchoolID.do")
    public JsonNode getClassroomBySchoolID(String id)
    {
        CustomJsonSerializer serializer = new CustomJsonSerializer(ClassroomVo.class, "id,name", null);
        return serializer.toJson_JsonNode(classroomEbi.getAllBySchoolId(id));
    }

    //-----------------------------------ClassroomManager-----------END------------------------------------------
    //-----------------------------------ClassroomManager-----------END------------------------------------------
    //-----------------------------------ClassroomManager-----------END------------------------------------------
    //-----------------------------------ClassroomManager-----------END------------------------------------------
    //-----------------------------------ClassroomManager-----------END------------------------------------------
    //-----------------------------------ClassroomManager-----------END------------------------------------------

//======================================================================================================================

    //-----------------------------------------------ResourceManager--------------------------------------------
    //-----------------------------------------------ResourceManager--------------------------------------------
    //-----------------------------------------------ResourceManager--------------------------------------------
    //-----------------------------------------------ResourceManager--------------------------------------------
    //-----------------------------------------------ResourceManager--------------------------------------------
    //-----------------------------------------------ResourceManager--------------------------------------------


    @RequestMapping("resource")
    public String toresourceTree()
    {
        return "/manage/resource/tree";
    }

    @ResponseBody
    @RequestMapping("resource/tree.do")
    public JsonResponse resourceTree()
    {
        return new JsonListResponse<TresourceVo>(
                resourceEbi.getAll(),
                "id,[text],[parent]parent.id",
                0, true)
                .addCustomJsonElementFormatter("text", obj -> obj.getName() + "   -   " + obj.getUrl())
                .addNullValue("parent", "#")
                .serialize();

    }

    /**
     * 跳转资源添加/修改页面
     *
     * @param parent  接受前台传递的资源id
     * @param request HttpServletRequest
     * @return 跳转edit
     */
    @RequestMapping("resource/edit")
    public String resourceEdit(String id, String parent, HttpServletRequest request)
    {
        //判断前台是否传递资源ID
        request.setAttribute("types", resourcetypeEbi.getAll());
        if (!StringUtil.isEmpty(id))
        {
            //根据资源ID获取资源完整信息从而进行数据回显
            TresourceVo tresourceVo = resourceEbi.get(id);
            if (tresourceVo.getParent() != null)
            {
                request.setAttribute("parent", resourceEbi.get(tresourceVo.getParent().getId()));
            }
            request.setAttribute("resource", tresourceVo);
        }
        if (!StringUtil.isEmpty(parent))
        {
            request.setAttribute("parent", resourceEbi.get(parent));

        }
        //如果待编辑资源为空 则会传进来parent.id

        return "/manage/resource/edit";
    }


    /**
     * 添加资源
     *
     * @param tresourceVo 需要添加的信息(必须包含学校id)
     * @return 跳转资源列表页面
     */
    @ResponseBody
    @RequestMapping("resource/edit.do")
    @SystemLogAnnotation(module = "资源管理", methods = "资源添加/修改")
    public JsonResponse resourceDoAdd(@Validated(value = {AddGroup.class}) TresourceVo tresourceVo, BindingResult bindingResult,
                                HttpServletRequest request) throws OperationException
    {

        JsonResponse jsonResponse = new JsonResponse();
        if (bindingResult.hasErrors())
        {
            jsonResponse.setCode(400);
            jsonResponse.setPayload(ValidatedErrorUtil.getErrorMessage(bindingResult));
            return jsonResponse;
        }
        if (null == tresourceVo.getId() || "".equals(tresourceVo.getId().trim()))
        {
            tresourceVo.setId(tresourceVo.getUrl().substring(1).replace("/", "_"));
            resourceEbi.save(tresourceVo);
            String allRes = (String) request.getServletContext().getAttribute(SysConsts.ALL_RESOUCERS_AUTHENTIC_URL_NAME);
            request.getServletContext().setAttribute(SysConsts.ALL_RESOUCERS_AUTHENTIC_URL_NAME, allRes + ",[" + tresourceVo.getUrl() + ",");
        } else
        {
            resourceEbi.update(tresourceVo);
        }
        jsonResponse.setMessage("操作成功！");
        return jsonResponse;
    }


    /**
     * 删除资源
     *
     * @param tresourceVo 需要删除的资源
     * @return 跳转资源列表页面
     */
    @ResponseBody
    @RequestMapping("resource/delete.do")
    @SystemLogAnnotation(module = "资源管理", methods = "资源删除")
    public JsonResponse resourceDelete(TresourceVo tresourceVo) throws OperationException
    {
        if (!StringUtil.isEmpty(tresourceVo.getId()))
        {
            resourceEbi.delete(tresourceVo);
        }
        return new JsonResponse("删除成功");
    }

    @ResponseBody
    @RequestMapping("resource/move.do")
    public JsonResponse moveResource(String id, String parent) throws OperationException
    {
        resourceEbi.move(id, parent);

        return new JsonResponse();
    }

    //-----------------------------------resourceManager-----------END------------------------------------------
    //-----------------------------------resourceManager-----------END------------------------------------------
    //-----------------------------------resourceManager-----------END------------------------------------------
    //-----------------------------------resourceManager-----------END------------------------------------------
    //-----------------------------------resourceManager-----------END------------------------------------------
    //-----------------------------------resourceManager-----------END------------------------------------------

//======================================================================================================================

    //--------------------------------------------------LogManager----------------------------------------------
    //--------------------------------------------------LogManager----------------------------------------------
    //--------------------------------------------------LogManager----------------------------------------------
    //--------------------------------------------------LogManager----------------------------------------------
    //--------------------------------------------------LogManager----------------------------------------------
    //--------------------------------------------------LogManager----------------------------------------------

    /**
     * 导出指定时间内的日志信息
     *
     * @param logQueryModel 该模型存放了资源属性
     * @return
     */
    @RequestMapping("log/export.do")
    @SystemLogAnnotation(module = "日志管理", methods = "日志导出")
    public ResponseEntity<byte[]> exportLog(LogQueryModel logQueryModel, HttpServletRequest request) throws Exception
    {
//        String path=request.getServletContext().getContextPath();
        String path = request.getServletContext().getRealPath("/WEB-INF/log");
        //根据查询条件查询
        File file = logEbi.getAll(logQueryModel, path);
        byte[] body = null;
        InputStream inputStream = new FileInputStream(file);
        body = new byte[inputStream.available()];
        inputStream.read(body);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attchement;filename=" + file.getName());
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<byte[]> entity = new ResponseEntity<>(body, headers, status);
        inputStream.close();
        file.delete();
        return entity;
    }

    //--------------------------------------------LogManager-----END--------------------------------------------
    //--------------------------------------------LogManager-----END--------------------------------------------
    //--------------------------------------------LogManager-----END--------------------------------------------
    //--------------------------------------------LogManager-----END--------------------------------------------
    //--------------------------------------------LogManager-----END--------------------------------------------
    //--------------------------------------------LogManager-----END--------------------------------------------
}
