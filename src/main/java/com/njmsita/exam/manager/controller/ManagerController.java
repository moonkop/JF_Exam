package com.njmsita.exam.manager.controller;


import com.njmsita.exam.authentic.model.TroleVo;
import com.njmsita.exam.authentic.model.querymodel.TroleQueryModel;
import com.njmsita.exam.authentic.service.ebi.RoleEbi;
import com.njmsita.exam.manager.model.SchoolVo;
import com.njmsita.exam.manager.model.querymodel.SchoolQueryVo;
import com.njmsita.exam.manager.service.ebi.SchoolEbi;
import com.njmsita.exam.authentic.service.ebi.StudentEbi;
import com.njmsita.exam.authentic.service.ebi.TeacherEbi;
import com.njmsita.exam.base.BaseController;
import com.njmsita.exam.utils.idutil.IdUtil;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 管理控制器
 */
@Controller
@RequestMapping("/manage")
public class ManagerController extends BaseController
{
    @Autowired
    private SchoolEbi schoolEbi;
    @Autowired
    private RoleEbi roleEbi;


//======================================================================================================================

    //-------------------------------------------SchoolManager----------------------------------------------
    //-------------------------------------------SchoolManager----------------------------------------------
    //-------------------------------------------SchoolManager----------------------------------------------
    //-------------------------------------------SchoolManager----------------------------------------------
    //-------------------------------------------SchoolManager----------------------------------------------
    //-------------------------------------------SchoolManager----------------------------------------------

    /**
     * 跳转学校页面(分页)
     * @param schoolQueryVo     该模型存放了学校属性
     * @param pageNum           页码
     * @param pageSize          页面大小
     * @param model
     * @return                  跳转学校列表页面
     *
     * @return JSON{
     *     rows: 内容（list）
     *     total: 查询结果总数
     * }
     *
     */
    //TODO  异步请求分页，要带上pageNum maxPageNum totalData
    //todo 以后将请求方法 doEdit doAdd 之类的写成 edit.do 包括list 现有的已经改好了
    //TODO 请求转发问题，没有pageNum   pageSize参数
    @RequestMapping("school/list.do")
    public String toSchoolList(SchoolQueryVo schoolQueryVo,Integer pageNum,Integer pageSize, Model model){

        //调用BaseController的方法设置数据总量及最大页码数
        pageCount=pageSize;
        setDataTotal(schoolEbi.getCount(schoolQueryVo));

        //根据查询条件及指定页码查询
        List<SchoolVo> schoolVoList = schoolEbi.getAll(schoolQueryVo,pageNum,pageSize);
        model.addAttribute("schoolVoList",schoolVoList);

        return "manage/school/list";
    }

    //测试方法
    @ResponseBody
    @RequestMapping("school/list1.do")
    public JSON schoolList(SchoolQueryVo schoolQueryVo,Integer pageNum,Integer pageSize)
    {
        List<SchoolVo> rows = schoolEbi.getAll(schoolQueryVo, pageNum, pageSize);
        JSONObject object = new JSONObject();
        object.put("rows", rows);
        object.put("total",schoolEbi.getCount(schoolQueryVo));
        return object;
    }

    /**
     * 跳转到学校管理页面
     * @return 跳转学校管理
     */
    @RequestMapping("school")
    public String toschoolList()
    {
        return "manage/school/list";
    }


    /**
     * 跳转学校添加/修改页面
     *
     * （此处将添加和修改页面合并，如果前台传递ID则进行修改否则进入添加页面）
     *
     * @param school    接受前台传递的学校id
     * @param request   HttpServletRequest
     * @return          跳转edit
     */
    @RequestMapping("school/edit")
    public String edit(SchoolVo school, HttpServletRequest request)
    {

        //判断前台是否传递学校ID
        if(null!=school.getId()){
            //根据学校ID获取学校完整信息从而进行数据回显
            school=schoolEbi.get(school.getId());
            request.setAttribute("school", school);
        }
       // return "redirect:/manage/roleVo/list";
        // fixme 这些个rolevo是什么鬼
        return "/manage/school/edit";
    }

    /**
     * 添加学校
     * @param school    需要添加的信息
     * @return          跳转学校列表页面
     */
    @RequestMapping("school/edit.do")
    public String doAdd(SchoolVo school)
    {
        if (null == school.getId()||"".equals(school.getId())) //fixed empty
        {
            //todo 不能插入重复名学校
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
     * @return 跳转学校列表页面
     */
    @RequestMapping("school/delete.do")
    public String schoolDelete(SchoolVo school)
    {

        //TODO 此处进行异常处理  异常描述：若该删除的学校有关联的学生或班级则抛出异常
        if(null!=school.getId()){

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

    /**
     * 跳转角色页面(分页)
     * @param roleQueryVo       该模型存放了角色属性
     * @param model
     * @param pageNum           页码
     * @param pageSize          页面大小
     * @return
     */
    //TODO  异步请求分页，要带上pageNum maxPageNum totalData
    @RequestMapping("role/list")
    public String toRoleList(TroleQueryModel roleQueryVo,Model model,Integer pageNum,Integer pageSize){

        //调用BaseController的方法设置数据总量及最大页码数
        pageCount=pageSize;
        setDataTotal(roleEbi.getCount(roleQueryVo));

        //根据查询条件及指定页码查询
        List<TroleVo> roleList = roleEbi.getAll(roleQueryVo,pageNum,pageSize);
        model.addAttribute("roleList",roleList);

        return "manager/role/list";
    }

    /**
     * 跳转角色添加/修改页面
     *
     * （此处将添加和修改页面合并，如果前台传递ID则进行修改否则进入添加页面）
     *
     * @param roleVo    接受前台传递的角色id
     * @param request   HttpServletRequest
     * @return          跳转edit
     */
    @RequestMapping("role/add")
    public String roleEdit(TroleVo roleVo, HttpServletRequest request){
        //判断前台是否传递学校ID
        if(null!= roleVo.getId()){
            //根据学校ID获取学校完整信息从而进行数据回显
            roleVo =roleEbi.get(roleVo.getId());
            request.setAttribute("roleVo", roleVo);
        }
        return "redirect:/manage/role/list";
    }

    /**
     * 添加角色
     * @param roleVo    需要添加的信息
     * @return          跳转角色列表页面
     */
    @RequestMapping("role/doAdd")
    public String roleDoAdd(TroleVo roleVo){
        if(null== roleVo.getId()){
            //todo 不能插入重复名学校
            roleVo.setId(IdUtil.getUUID());
            roleEbi.save(roleVo);
        }else{
            roleEbi.update(roleVo);
        }
        return "redirect:/manager/role/list";
    }


    /**
     * 删除角色
     * @param  roleVo   需要删除的角色
     * @return          跳转角色列表页面
     */
    @RequestMapping("role/delete")
    public String roleDelete(TroleVo roleVo){

        if(null!=roleVo.getId()){

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
}
