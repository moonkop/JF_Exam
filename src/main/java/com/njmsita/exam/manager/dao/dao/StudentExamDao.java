package com.njmsita.exam.manager.dao.dao;

import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.base.BaseDao;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.StudentExamVo;

import java.util.List;

/**
 * 学生考试持久层接口
 */
public interface StudentExamDao extends BaseDao<StudentExamVo>
{
    /**
     * 删除该场所有的学生
     *
     * @param examVo
     */
    public void deleteAllByExam(ExamVo examVo);


    /**
     * 获取对应学生对应考试的考试信息
     *
     * @param examVo
     * @param studentVo
     * @return
     */
    public StudentExamVo getByStudentAndExam(ExamVo examVo, StudentVo studentVo);

    /**
     * 根据考试获取所有学生的试卷
     * @param examId
     * @return
     */
    List<StudentExamVo> getAllStudentExambyExamId(String examId);
}
