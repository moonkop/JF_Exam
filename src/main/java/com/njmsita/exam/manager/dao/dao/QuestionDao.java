package com.njmsita.exam.manager.dao.dao;

import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.base.BaseDao;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.model.QuestionVo;
import com.njmsita.exam.manager.model.SubjectVo;
import com.njmsita.exam.manager.model.querymodel.QuestionQueryModel;

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
    public List<QuestionVo> getByQuestionType(Integer id);

    /**
     *
     * @param questionQueryModel
     * @param offset
     * @param pageSize
     * @param login
     * @return
     */
    public List<QuestionVo> getAllByTeacher(QuestionQueryModel questionQueryModel, Integer offset, Integer pageSize, TeacherVo login);

    /**
     * 批量导入
     * @param questions
     */
    public void bulkInput(List<QuestionVo> questions);

    /**
     * 管理员查看题库
     * @param qm
     * @param pageNum
     * @param pageCount
     * @return
     */
    public List<QuestionVo> getAllByAdmin(BaseQueryVO qm, Integer pageNum, Integer pageCount);
}
