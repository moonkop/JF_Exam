package com.njmsita.exam.manager.service.ebi;

import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.StudentExamQuestionVo;
import com.njmsita.exam.manager.model.StudentExamVo;

import java.util.List;
import java.util.Map;

public interface  ExamStudentEbi
{
    /**
     * 学生考试存档
     * @param login
     * @param studentExamId
     * @param studentExamQuestionList
     */
    public void archive(StudentVo login, String studentExamId, List<StudentExamQuestionVo> studentExamQuestionList) throws Exception;

    /**
     * 提交作答
     * @param studentExamVo
     * @param login
     */
    public void submitAnswer(StudentExamVo studentExamVo, StudentVo login) throws Exception;

    /**
     * 查看学生个人考试情况
     * @param examVo
     * @param login
     * @return
     */
    public StudentExamVo getStudentExam(ExamVo examVo, StudentVo login) throws Exception;

    /**
     * 学生参加考试
     * @param examVo
     * @param studentVo
     * @return
     */
    public Map<String, Object> attendExam(ExamVo examVo, StudentVo studentVo) throws Exception;

}
