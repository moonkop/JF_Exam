package com.njmsita.exam.manager.dao.dao;

import com.njmsita.exam.base.BaseDao;
import com.njmsita.exam.manager.model.QuestionVo;
import com.njmsita.exam.manager.model.SubjectVo;

import java.util.List;

/**
 * 题目持久层接口
 */
public interface QuestionDao extends BaseDao<QuestionVo>
{
    /**
     * 根据科目id查询题目
     * @param id    科目id
     * @return
     */
    public List<QuestionVo> getBySubject(Integer id);

    /**
     * 根据题型id查询题目
     * @param id    题型id
     * @return
     */
    public List<QuestionVo> getByQuestionType(Byte id);
}
