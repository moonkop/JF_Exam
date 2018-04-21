package com.njmsita.exam.authentic.controller;


import com.njmsita.exam.authentic.model.SchoolVo;
import com.njmsita.exam.authentic.model.querymodel.SchoolQueryVo;
import com.njmsita.exam.authentic.service.ebi.SchoolEbi;
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

    //------------------------------------------TeacherManager----------------------------------------------
    //------------------------------------------TeacherManager----------------------------------------------
    //------------------------------------------TeacherManager----------------------------------------------
    //------------------------------------------TeacherManager----------------------------------------------
    //------------------------------------------TeacherManager----------------------------------------------
    //------------------------------------------TeacherManager----------------------------------------------
    /**
     *跳转教师列表
     */
    @RequestMapping("teacher")
    public String toTeacherList(int pageNum){
//        List
//        List<TeacherVo> teahcerList =teacherEbi.
        return "manager/teacher/list";
    }



    //-------------------------------TeacherManager-----------END-------------------------------------------
    //-------------------------------TeacherManager-----------END-------------------------------------------
    //-------------------------------TeacherManager-----------END-------------------------------------------
    //-------------------------------TeacherManager-----------END-------------------------------------------
    //-------------------------------TeacherManager-----------END-------------------------------------------
    //-------------------------------TeacherManager-----------END-------------------------------------------

//==========================================================================================================================

    //-------------------------------------------SchoolManager----------------------------------------------
    //-------------------------------------------SchoolManager----------------------------------------------
    //-------------------------------------------SchoolManager----------------------------------------------
    //-------------------------------------------SchoolManager----------------------------------------------
    //-------------------------------------------SchoolManager----------------------------------------------
    //-------------------------------------------SchoolManager----------------------------------------------

    /**
     * 跳转学校页面(分页)
     * @param schoolQueryVo 该模型存放了学校属性  分页数据  查询条件
     * @return              跳转学校列表页面
     */
    @RequestMapping("school")
    public String toSchoolList(SchoolQueryVo schoolQueryVo, Model model){
        //调用BaseController的方法设置数据总量及最大页码数
        setDataTotal(schoolEbi.getCount(schoolQueryVo));

        //判断前端是否制定页码，若没有则使用默认
        if(null!=schoolQueryVo.getPageNum()){
            pageNum=schoolQueryVo.getPageNum();
        }

        //存入schoolQueryVo模型中便于页面调用
        schoolQueryVo.setMaxPageNum(maxPageNum);
        schoolQueryVo.setDataTotal(dataTotal);

        //根据查询条件及指定页码查询
        List<SchoolVo> schoolVoList = schoolEbi.getAll(schoolQueryVo, pageNum, pageCount);
        model.addAttribute("schoolVoList",schoolVoList);

        return "manager/school/list";
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
    @RequestMapping("add")
    public String edit(SchoolVo school, HttpServletRequest request){
        //判断前台是否传递学校ID
        if(null!=school.getId()){
            //根据学校ID获取学校完整信息从而进行数据回显
            school=schoolEbi.get(school.getId());
            request.setAttribute("school",school);
        }
        return "redirect:/manager/toSchoolList";
    }

    /**
     * 添加学校
     * @param school
     * @return          跳转学校列表页面
     */
    @RequestMapping("doAdd")
    public String doAdd(SchoolVo school){
        if(null==school.getId()){
            school.setId(IdUtil.getUUID());
            schoolEbi.save1(school);
        }else{
            schoolEbi.update(school);
        }
        return "redirect:/manager/toSchoolList";
    }
    //--------------------------------SchoolManager----------END--------------------------------------------
    //--------------------------------SchoolManager----------END--------------------------------------------
    //--------------------------------SchoolManager----------END--------------------------------------------
    //--------------------------------SchoolManager----------END--------------------------------------------
    //--------------------------------SchoolManager----------END--------------------------------------------
    //--------------------------------SchoolManager----------END--------------------------------------------
}
