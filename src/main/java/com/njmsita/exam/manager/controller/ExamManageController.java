package com.njmsita.exam.manager.controller;

import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.service.ebi.ExamEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.format.StringUtil;
import com.njmsita.exam.utils.idutil.IdUtil;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 考试管理控制器
 */
@Controller
@RequestMapping("/exam")
public class ExamManageController
{
    @Autowired
    private ExamEbi examEbi;

    @RequestMapping("createExam")
    @SystemLogAnnotation(module = "考试管理", methods = "发起/修改考试")
    public JsonResponse examDoAdd(@Validated(value = {AddGroup.class}) ExamVo examVo, BindingResult bindingResult,
                                  @RequestParam(name = "markTeachers") String[] markTeachers , String paperId ,
                                  @RequestParam(name = "classroomIds") String[] classroomIds ,
                                  HttpServletRequest request) throws Exception
    {
        JsonResponse jsonResponse=new JsonResponse();
        if (bindingResult.hasErrors())
        {
            List<FieldError> list = bindingResult.getFieldErrors();
            for (FieldError fieldError : list)
            {
                //校验信息，key=属性名+Error
                request.setAttribute(fieldError.getField() + "Error", fieldError.getDefaultMessage());
            }
            jsonResponse.setCode(500);
        }
        if (StringUtil.isEmpty(examVo.getId()))
        {
            TeacherVo login= (TeacherVo) request.getSession().getAttribute(SysConsts.USER_LOGIN_TEACHER_OBJECT_NAME);
            examVo.setCreateTeacher(login);
            examVo.setId(IdUtil.getUUID());
            examEbi.save(examVo,markTeachers,paperId,classroomIds);
        } else
        {
            examEbi.update(examVo,markTeachers,paperId,classroomIds);
        }
        return jsonResponse;
    }
}
