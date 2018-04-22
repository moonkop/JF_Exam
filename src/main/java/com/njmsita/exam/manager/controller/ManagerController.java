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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private TeacherEbi teacherEbi;
    @Autowired
    private StudentEbi studentEbi;
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
     *
     * @param schoolQueryVo   分页数据  查询条件
     * @return
     */
    /**
     * 跳转学校页面(分页)
     * @param schoolQueryVo     该模型存放了学校属性
     * @param pageNum           页码
     * @param pageSize          页面大小
     * @param model
     * @return                  跳转学校列表页面
     */
    //TODO  异步请求分页，要带上pageNum maxPageNum totalData
    @RequestMapping("school/list")
    public String toSchoolList(SchoolQueryVo schoolQueryVo,Integer pageNum,Integer pageSize, Model model){

        //根据查询条件及指定页码查询
        List<SchoolVo> schoolVoList = schoolEbi.getAll(schoolQueryVo,pageNum,pageSize);
        model.addAttribute("schoolVoList",schoolVoList);

        return "manager/roleVo/list";
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
    @RequestMapping("school/add")
    public String schoolEdit(SchoolVo school, HttpServletRequest request){
        //判断前台是否传递学校ID
        if(null!=school.getId()){
            //根据学校ID获取学校完整信息从而进行数据回显
            school=schoolEbi.get(school.getId());
            request.setAttribute("roleVo",school);
        }
        return "redirect:/manage/roleVo/list";
    }

    /**
     * 添加学校
     * @param school    需要添加的信息
     * @return          跳转学校列表页面
     */
    @RequestMapping("school/doAdd")
    public String schoolDoAdd(SchoolVo school){
        if(null==school.getId()){
            school.setId(IdUtil.getUUID());
            schoolEbi.save(school);
        }else{
            schoolEbi.update(school);
        }
        return "redirect:/manager/roleVo/list";
    }


    /**
     * 删除学校
     * @param  school   需要删除的学校
     * @return          跳转学校列表页面
     */
    @RequestMapping("school/delete")
    public String schoolDelete(SchoolVo school){

        //TODO 此处进行异常处理  异常描述：若该删除的学校有关联的学生或班级则抛出异常
        schoolEbi.delete(school);

        return "redirect:/manage/roleVo/list";
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

        roleEbi.delete(roleVo);

        return "redirect:/manage/role/list";
    }

    //-----------------------------------RoleManager-----------END------------------------------------------
    //-----------------------------------RoleManager-----------END------------------------------------------
    //-----------------------------------RoleManager-----------END------------------------------------------
    //-----------------------------------RoleManager-----------END------------------------------------------
    //-----------------------------------RoleManager-----------END------------------------------------------
    //-----------------------------------RoleManager-----------END------------------------------------------
}
