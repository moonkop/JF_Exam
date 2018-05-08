package com.njmsita.exam.manager.controller;

import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.authentic.service.ebi.TeacherEbi;
import com.njmsita.exam.manager.model.PaperVo;
import com.njmsita.exam.manager.model.SubjectVo;
import com.njmsita.exam.manager.model.querymodel.PaperQueryModel;
import com.njmsita.exam.manager.service.ebi.PaperEbi;
import com.njmsita.exam.manager.service.ebi.SubjectEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.json.JsonListResponse;
import com.njmsita.exam.utils.json.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
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


    /**
     * 跳转到试卷管理页面
     *
     * @return 跳转试卷列表
     */
    @RequestMapping("toList")
    public String toPaperList(HttpServletRequest request)
    {
        List<SubjectVo> subjectList=subjectEbi.getAll();
        List<TeacherVo> teacherList=teacherEbi.getAll();
        TeacherVo login= (TeacherVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME);
        teacherList.remove(login);
        request.setAttribute("subjectList",subjectList);
        request.setAttribute("teacherList",teacherList);
        return "/paper/list";
    }

    @RequestMapping("list.do")
    public JsonResponse paperList(PaperQueryModel paperQueryModel, Integer pageNum, Integer pageSize,
                                  HttpServletRequest request)
    {
        List<PaperVo> list=null;
        TeacherVo teacherVo= (TeacherVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME);
        if(paperQueryModel.getShowMe()==null){
            paperQueryModel.setShowMe(false);
        }
        if (paperQueryModel.getShowMe()){
            paperQueryModel.setTeacher(teacherVo);
        }
        list=paperEbi.getAll(paperQueryModel,pageNum,pageSize);
        return new JsonListResponse<>(list,"id,title,[teacher]teacher.id,[subject]subject.name",0);
    }

    public String createPaper(HttpServletRequest request){
        List<SubjectVo> subjectList=subjectEbi.getAll();
        request.setAttribute("subjectList",subjectList);
        return "/paper/create";
    }
}
