package com.njmsita.exam.manager.dao.dao;

import com.njmsita.exam.base.BaseDao;
import com.njmsita.exam.manager.model.LogVo;
import com.njmsita.exam.manager.model.StudentExamQuestionVo;
import com.njmsita.exam.manager.model.StudentExamVo;
import com.njmsita.exam.manager.model.querymodel.LogQueryModel;

import java.util.List;

/**
 * 学生作答情况持久层接口
 */
public interface StudentExamQuestionDao extends BaseDao<StudentExamQuestionVo>
{
    /**
     * 根据学生考试获取所有题目作答情况
     * @param studentExamVo
     * @return
     */
    public List<StudentExamQuestionVo> getAllByStudentExam(StudentExamVo studentExamVo);
}
