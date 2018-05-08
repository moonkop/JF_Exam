package com.njmsita.exam.manager.service.ebi;

import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.base.BaseEbi;
import com.njmsita.exam.manager.model.QuestionVo;
import com.njmsita.exam.manager.model.querymodel.QuestionQueryModel;
import com.njmsita.exam.utils.exception.OperationException;

import java.util.List;

/**
 * 题目业务层接口
 */
public interface QuestionEbi extends BaseEbi<QuestionVo>
{

    /**
     *
     * @param questionQueryModel
     * @param offset
     * @param pageSize
     * @param teacherVo
     * @return
     */
    public List<QuestionVo> getAllByTeacher(QuestionQueryModel questionQueryModel, Integer offset, Integer pageSize, TeacherVo teacherVo);

    /**
     * 更新或保存为据为己有
     * @param questionVo
     * @param teacherVo
     */
    public void updateOrSaveToMe(QuestionVo questionVo, TeacherVo teacherVo) throws OperationException;
}
