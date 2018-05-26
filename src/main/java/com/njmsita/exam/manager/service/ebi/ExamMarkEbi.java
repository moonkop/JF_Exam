package com.njmsita.exam.manager.service.ebi;

import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.StudentExamQuestionVo;

import java.util.List;

public interface ExamMarkEbi
{
    /**
     * 保存阅卷存档
     * @param studentExamQeustionList
     * @param studentExamId
     */
    public void saveMarked(List<StudentExamQuestionVo> studentExamQeustionList, String studentExamId) throws Exception;

    /**
     * 提交阅卷
     * @param examVo
     * @param login
     */
    public void submitMarked(ExamVo examVo, TeacherVo login) throws Exception;
}
