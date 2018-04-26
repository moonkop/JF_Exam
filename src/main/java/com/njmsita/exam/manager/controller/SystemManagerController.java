package com.njmsita.exam.manager.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.njmsita.exam.authentic.model.TresourceVo;
import com.njmsita.exam.authentic.model.TroleVo;
import com.njmsita.exam.authentic.model.querymodel.ResourceQueryModel;
import com.njmsita.exam.authentic.model.querymodel.TroleQueryModel;
import com.njmsita.exam.authentic.service.ebi.ResourceEbi;
import com.njmsita.exam.authentic.service.ebi.ResourcetypeEbi;
import com.njmsita.exam.authentic.service.ebi.RoleEbi;
import com.njmsita.exam.manager.model.ClassroomVo;
import com.njmsita.exam.manager.model.SchoolVo;
import com.njmsita.exam.manager.model.querymodel.ClassroomQueryModel;
import com.njmsita.exam.manager.model.querymodel.SchoolQueryModel;
import com.njmsita.exam.manager.service.ebi.ClassroomEbi;
import com.njmsita.exam.manager.service.ebi.SchoolEbi;
import com.njmsita.exam.base.BaseController;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.format.CustomerJsonSerializer;
import com.njmsita.exam.utils.format.StringUtil;
import com.njmsita.exam.utils.idutil.IdUtil;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.cxf.common.util.ReflectionInvokationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private ResourcetypeEbi resourcetypeEbi;
//======================================================================================================================

    //-------------------------------------------SchoolManager----------------------------------------------
    //-------------------------------------------SchoolManager----------------------------------------------
    //-------------------------------------------SchoolManager----------------------------------------------
    //-------------------------------------------SchoolManager----------------------------------------------
    //-------------------------------------------SchoolManager----------------------------------------------
    //-------------------------------------------SchoolManager----------------------------------------------

    /**
     * 跳转学校页面(分页)
     *
     * @param schoolQueryModel 该模型存放了学校属性
     * @param pageNum          页码
     * @param pageSize         页面大小
     * @param model
     *
     * @return JSON{ rows: 内容（list） total: 查询结果总数 }
     */

    //todo 以后将请求方法 doEdit doAdd 之类的写成 edit.do 包括list 现有的已经改好了
    //TODO 请求转发问题，没有pageNum   pageSize参数
    @RequestMapping("school/list.do")
    public String toSchoolList(SchoolQueryModel schoolQueryModel, Integer pageNum, Integer pageSize, Model model)
    {

        //调用BaseController的方法设置数据总量及最大页码数
        pageCount = pageSize;
        setDataTotal(schoolEbi.getCount(schoolQueryModel));

        //根据查询条件及指定页码查询
        List<SchoolVo> schoolVoList = schoolEbi.getAll(schoolQueryModel, pageNum, pageSize);
        model.addAttribute("schoolVoList", schoolVoList);

        return "manage/school/list";
    }

    //测试方法
    @ResponseBody
    @RequestMapping("school/list1.do")
    public JSON schoolList(SchoolQueryModel schoolQueryModel, Integer pageNum, Integer pageSize)
    {
        List<SchoolVo> rows = schoolEbi.getAll(schoolQueryModel, pageNum, pageSize);
        JSONObject object = new JSONObject();
        object.put("rows", rows);
        object.put("total", schoolEbi.getCount(schoolQueryModel));
        return object;
    }

    /**
     * 跳转到学校管理页面
     *
     * @return 跳转学校管理
     */
    @RequestMapping("school")
    public String toSchoolList()
    {
        return "manage/school/list";
    }

    @RequestMapping("school/detail")
    public String toSchoodetail(String id, HttpServletRequest request)
    {
        SchoolVo schoolVo = schoolEbi.get(id);
        request.setAttribute("school", schoolVo);
        return "manage/school/detail";
    }


    /**
     * 跳转学校添加/修改页面
     * <p>
     * （此处将添加和修改页面合并，如果前台传递ID则进行修改否则进入添加页面）
     *
     * @param school  接受前台传递的学校id
     * @param request HttpServletRequest
     *
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
     *
     * @return 跳转学校列表页面
     */
    @RequestMapping("school/edit.do")
    public String doAdd(SchoolVo school) throws OperationException
    {
        if (null == school.getId() || "".equals(school.getId())) //fixed empty
        {
            school.setId(IdUtil.getUUID());
            schoolEbi.save(school);
        } else
        {
            schoolEbi.update(school);
        }
        return "redirect:/manage/school";
    }


    /**
     * 删除学校
     *
     * @param school 需要删除的学校
     *
     * @return 跳转学校列表页面
     */
    @RequestMapping("school/delete.do")
    public String schoolDelete(SchoolVo school) throws OperationException
    {

        if (null != school.getId() && !"".equals(school.getId().trim()))
        {

            schoolEbi.delete(school);
        }

        return "redirect:/manage/school";
        //  return "redirect:/manage/roleVo/list";
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


    @ResponseBody
    @RequestMapping("role/list.do")
    public JsonNode roleList(TroleQueryModel troleQueryModel, Integer pageNum, Integer pageSize)
    {
        CustomerJsonSerializer serializer = new CustomerJsonSerializer(TroleVo.class, "id,name", null);
        ObjectNode result = CustomerJsonSerializer.getDefaultMapper().createObjectNode();
        List<TroleVo> troleVoList = roleEbi.getAll(troleQueryModel, pageNum, pageSize);
        List<ObjectNode> rows = new ArrayList<>();
        for (TroleVo troleVo : troleVoList)
        {
            ObjectNode node = serializer.toJson_ObjectNode(troleVo);
            rows.add(node);
        }
        result.put("rows", CustomerJsonSerializer.toJson_JsonNode1(rows));
        result.put("total", roleEbi.getCount(troleQueryModel));
        return result;
    }

    @ResponseBody
    @RequestMapping("roleResource/tree.do")
    public JsonNode roleResourceList(boolean edit)
    {
        CustomerJsonSerializer serializer = new CustomerJsonSerializer(TroleVo.class, "id,name", null);
        return null;

    }


    @RequestMapping("role")
    public String toRoleList()
    {
        return "/manage/role/list";
    }

    @RequestMapping("role/detail")
    public String toRoleDetail(TroleVo troleVo, HttpServletRequest request)
    {
        request.setAttribute("role", roleEbi.get(troleVo.getId()));
        return "/manage/role/detail";
    }

    /**
     * 跳转角色页面(分页)
     *
     * @param roleQueryModel 该模型存放了角色属性
     * @param model
     * @param pageNum        页码
     * @param pageSize       页面大小
     *
     * @return
     */

    @RequestMapping("role/list")
    public String toRoleList(TroleQueryModel roleQueryModel, Model model, Integer pageNum, Integer pageSize)
    {

        //调用BaseController的方法设置数据总量及最大页码数
        pageCount = pageSize;
        setDataTotal(roleEbi.getCount(roleQueryModel));

        //根据查询条件及指定页码查询
        List<TroleVo> roleList = roleEbi.getAll(roleQueryModel, pageNum, pageSize);
        model.addAttribute("roleList", roleList);

        return "manage/role/list";
    }

    /**
     * 跳转角色添加/修改页面
     * <p>
     * （此处将添加和修改页面合并，如果前台传递ID则进行修改否则进入添加页面）
     *
     * @param roleVo  接受前台传递的角色id
     * @param request HttpServletRequest
     *
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
            request.setAttribute("roleVo", roleVo);

            //获取当前角色已拥有资源
            Set<TresourceVo> roleReses = roleVo.getReses();
            request.setAttribute("roleReses", roleReses);

            //获取当前角色未拥有资源
            Set<TresourceVo> otherReses = roleVo.getReses();
            otherReses.removeAll(roleReses);
            request.setAttribute("otherReses", otherReses);
        }
        return "redirect:/manage/role/list";
    }

    /**
     * 添加角色
     *
     * @param roleVo 需要添加的信息
     *
     * @return 跳转角色列表页面
     */
    @RequestMapping("role/edit.do")
    public String roleDoAdd(TroleVo roleVo, String[] resourceIds) throws OperationException
    {
        if (null == roleVo.getId() || "".equals(roleVo.getId().trim()))
        {
            roleVo.setId(IdUtil.getUUID());
            roleEbi.save(roleVo, resourceIds);
        } else
        {
            roleEbi.update(roleVo, resourceIds);
        }
        return "redirect:/manage/role/list";
    }


    /**
     * 删除角色
     *
     * @param roleVo 需要删除的角色
     *
     * @return 跳转角色列表页面
     */
    @RequestMapping("role/delete")
    public String roleDelete(TroleVo roleVo) throws OperationException
    {

        if (null != roleVo.getId() && !"".equals(roleVo.getId().trim()))
        {

            roleEbi.delete(roleVo);
        }

        return "redirect:/manage/role/list";
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
    public JsonNode classroomList(ClassroomQueryModel classroomQueryModel, Integer pageNum, Integer pageSize)
    {
        CustomerJsonSerializer serializer = new CustomerJsonSerializer(ClassroomVo.class, "id,name", null);
        ObjectNode result = CustomerJsonSerializer.getDefaultMapper().createObjectNode();
        List<ClassroomVo> classroomList = classroomEbi.getAll(classroomQueryModel, pageNum, pageSize);
        List<ObjectNode> rows = new ArrayList<>();
        for (ClassroomVo classroomVo : classroomList)
        {
            ObjectNode node = serializer.toJson_ObjectNode(classroomVo);
            node.put("school", classroomVo.getSchoolVo().getName());
            rows.add(node);
        }
        result.put("rows", CustomerJsonSerializer.toJson_JsonNode1(rows));
        result.put("total", classroomEbi.getCount(classroomQueryModel));
        return result;

    }

    /**
     * 跳转班级页面(分页)
     *
     * @param classroomQueryModel 该模型存放了班级属性
     * @param model
     * @param pageNum             页码
     * @param pageSize            页面大小
     *
     * @return
     */

    @RequestMapping("classroom/list")
    public String toClassroomList(ClassroomQueryModel classroomQueryModel, Model model, Integer pageNum, Integer pageSize)
    {

        //调用BaseController的方法设置数据总量及最大页码数
        pageCount = pageSize;
        setDataTotal(classroomEbi.getCount(classroomQueryModel));

        //根据查询条件和指定的页码查询
        List<ClassroomVo> classroomList = classroomEbi.getAll(classroomQueryModel, pageNum, pageSize);
        model.addAttribute("classroomList", classroomList);

        return "manage/classroom/list";
    }

    /**
     * 跳转班级添加/修改页面
     * <p>
     * （此处将添加和修改页面合并，如果前台传递ID则进行修改否则进入添加页面）
     *
     * @param classroomVo 接受前台传递的班级id
     * @param request     HttpServletRequest
     *
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
     *
     * @return 跳转班级列表页面
     */
    @RequestMapping("classroom/edit.do")
    public String classroomDoAdd(ClassroomVo classroomVo) throws OperationException
    {
        if (null == classroomVo.getId() || "".equals(classroomVo.getId().trim()))
        {
            classroomVo.setId(IdUtil.getUUID());
            classroomEbi.save(classroomVo);
        } else
        {
            classroomEbi.update(classroomVo);
        }
        return "redirect:/manage/classroom";
    }


    /**
     * 删除班级
     *
     * @param classroomVo 需要删除的班级
     *
     * @return 跳转班级列表页面
     */
    @RequestMapping("classroom/delete")
    public String classroomDelete(ClassroomVo classroomVo) throws OperationException
    {

        if (null != classroomVo.getId())
        {

            classroomEbi.delete(classroomVo);
        }

        return "redirect:/manage/classroom/list";
    }

    @ResponseBody
    @RequestMapping("getClassroomBySchoolID.do")
    public JsonNode getClassroomBySchoolID(String id)
    {
        CustomerJsonSerializer serializer = new CustomerJsonSerializer(ClassroomVo.class, "id,name", null);
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

    @RequestMapping("resource/list1")
    public String toresourceList()
    {
        return "/manage/resource/list";
    }


    @ResponseBody
    @RequestMapping("resource/list.do")
    public JsonNode resourceList(ResourceQueryModel resourceQueryModel, Integer pageNum, Integer pageSize)
    {
        //创建自定义序列化器 并设置过滤器
        CustomerJsonSerializer serializer = new CustomerJsonSerializer(TresourceVo.class, "id,name,url,remark", null);
        //创建返回值对象 json类型
        ObjectNode result = CustomerJsonSerializer.getDefaultMapper().createObjectNode();
        List<TresourceVo> tresourceVoList = resourceEbi.getAll(resourceQueryModel, pageNum, pageSize);
        //对象转换后存放的数组
        List<ObjectNode> rows = new ArrayList<>();
        for (TresourceVo tresourceVo : tresourceVoList)
        {
            //自定义过滤序列化对象
            ObjectNode node = serializer.toJson_ObjectNode(tresourceVo);
            //添加额外的特殊属性
            node.put("type", tresourceVo.getResourcetype().getName());
            rows.add(node);
        }
        //将数组转换为json节点 并插入返回值对象
        result.put("rows", CustomerJsonSerializer.toJson_JsonNode1(rows));
        result.put("total", resourceEbi.getCount(resourceQueryModel));
        return result;
    }

    @ResponseBody
    @RequestMapping("resource/tree.do")
    public List<ObjectNode> resourceTree()
    {
        List<TresourceVo> list = resourceEbi.getAll();
        CustomerJsonSerializer serializer = new CustomerJsonSerializer(TresourceVo.class, "id,url,remark", null);
        List<ObjectNode> rows = new ArrayList<>();
        for (TresourceVo tresourceVo : list)
        {
            ObjectNode node = serializer.toJson_ObjectNode(tresourceVo);
            if (tresourceVo.getParent() != null)
            {
                node.put("parent", tresourceVo.getParent().getId());
            } else
            {
                node.put("parent", "#");
            }
            node.put("text", tresourceVo.getName() + "    " + tresourceVo.getUrl());
            rows.add(node);
        }
        return rows;
    }

    /**
     * 跳转资源页面(分页)
     *
     * @param resourceQueryModel 该模型存放了资源属性
     * @param model
     * @param pageNum            页码
     * @param pageSize           页面大小
     *
     * @return
     */
    @RequestMapping("resource/list")
    public String toresourceList(ResourceQueryModel resourceQueryModel, Model model, Integer pageNum, Integer pageSize)
    {

        //调用BaseController的方法设置数据总量及最大页码数
        pageCount = pageSize;
        setDataTotal(resourceEbi.getCount(resourceQueryModel));

        //根据查询条件和指定的页码查询
        List<TresourceVo> resourceList = resourceEbi.getAll(resourceQueryModel, pageNum, pageSize);
        model.addAttribute("resourceList", resourceList);

        return "manage/resource/list";
    }

    /**
     * 跳转资源添加/修改页面
     * <p>
     * （此处将添加和修改页面合并，如果前台传递ID则进行修改否则进入添加页面）
     *
     * @param tresourceVo 接受前台传递的资源id
     * @param request     HttpServletRequest
     *
     * @return 跳转edit
     */
    @RequestMapping("resource/edit")
    public String resourceEdit(TresourceVo tresourceVo, HttpServletRequest request)
    {
        //判断前台是否传递资源ID
        request.setAttribute("types", resourcetypeEbi.getAll());

        if (!StringUtil.isEmpty(tresourceVo.getId()))
        {
            //根据资源ID获取资源完整信息从而进行数据回显
            tresourceVo = resourceEbi.get(tresourceVo.getId());
            request.setAttribute("parent", resourceEbi.get(tresourceVo.getParent().getId()));
            request.setAttribute("resource", tresourceVo);
        } else
        {
            //如果待编辑资源为空 则会传进来parent.id
            request.setAttribute("parent", resourceEbi.get(tresourceVo.getParent().getId()));

        }
        return "/manage/resource/edit";
    }

    /**
     * 添加资源
     *
     * @param tresourceVo 需要添加的信息(必须包含学校id)
     *
     * @return 跳转资源列表页面
     */
    @RequestMapping("resource/edit.do")
    public String resourceDoAdd(TresourceVo tresourceVo) throws OperationException
    {
        if (null == tresourceVo.getId() || "".equals(tresourceVo.getId().trim()))
        {
            tresourceVo.setId(IdUtil.getUUID());
            resourceEbi.save(tresourceVo);
        } else
        {
            resourceEbi.update(tresourceVo);
        }
        return "redirect:/manage/resource";
    }


    /**
     * 删除资源
     *
     * @param tresourceVo 需要删除的资源
     *
     * @return 跳转资源列表页面
     */
    @RequestMapping("resource/delete.do")
    public String resourceDelete(TresourceVo tresourceVo) throws OperationException
    {

        if (null != tresourceVo.getId() && !"".equals(tresourceVo.getId().trim()))
        {

            resourceEbi.delete(tresourceVo);
        }

        return "redirect:/manage/resource";
    }

    //-----------------------------------resourceManager-----------END------------------------------------------
    //-----------------------------------resourceManager-----------END------------------------------------------
    //-----------------------------------resourceManager-----------END------------------------------------------
    //-----------------------------------resourceManager-----------END------------------------------------------
    //-----------------------------------resourceManager-----------END------------------------------------------
    //-----------------------------------resourceManager-----------END------------------------------------------
}
