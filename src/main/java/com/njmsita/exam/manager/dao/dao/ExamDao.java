package com.njmsita.exam.manager.dao.dao;

import com.njmsita.exam.base.BaseDao;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.LogVo;
import com.njmsita.exam.manager.model.querymodel.LogQueryModel;

import java.io.Serializable;
import java.util.List;

/**
 * 考试持久层接口
 */
public interface ExamDao extends BaseDao<ExamVo>
{
    /**
     * 根据创建教师id查询试卷
     * @param teacherId
     * @return
     */
    public List<ExamVo> getByCreateTeacher(String teacherId);

    /**
     * 根据阅卷教师id查询试卷
     * @param teacherId
     * @return
     */
    public List<ExamVo> getByMarkTeacher(String teacherId);

    public ExamVo getExamWithPaper(Serializable uuid);
    public void SetPaper(ExamVo examVo);


}
