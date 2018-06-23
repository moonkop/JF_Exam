package com.njmsita.exam.manager.service.ebi;

import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.StudentExamQuestionVo;
import com.njmsita.exam.utils.exception.ItemNotFoundException;
import com.njmsita.exam.utils.exception.OperationException;

import java.util.List;
import java.util.Map;

public interface ExamMarkEbi
{
    /**
     * 保存阅卷存档
     * @param studentExamQeustionList
     * @param loginTeacher
     *
     */
    public void saveMarked(List<StudentExamQuestionVo> studentExamQeustionList, TeacherVo loginTeacher) throws Exception;

    /**
     * 提交阅卷
     * @param examVo
     * @param login
     */
    public void submitMarked(ExamVo examVo, TeacherVo login) throws Exception;

    /**
     * 获取需要手动批阅的题目
     * @param studentExamId
     * @return
     * @throws Exception
     */
    public List<StudentExamQuestionVo> GetManualMarkWorkOutFormStudentExam(String studentExamId) throws Exception;


    Map<String, Object> getStudentWorkout(String studentExamId) throws OperationException;

    Map<String, Object> getMarkProgress(String ExamId) throws ItemNotFoundException;

    List<Map<String, Object>> getStudentExamList(String examId);
}
