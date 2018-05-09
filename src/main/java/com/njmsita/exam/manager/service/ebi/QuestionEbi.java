package com.njmsita.exam.manager.service.ebi;

import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.base.BaseEbi;
import com.njmsita.exam.manager.model.QuestionVo;
import com.njmsita.exam.manager.model.querymodel.QuestionQueryModel;
import com.njmsita.exam.utils.exception.OperationException;
import org.apache.poi.hssf.usermodel.HSSFSheet;

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
    public QuestionVo updateOrSaveToMe(QuestionVo questionVo, TeacherVo teacherVo) throws OperationException;

    /**
     * 根据科目批量导入题目
     * @param sheet
     * @param subjectId
     */
    public void bulkInputBySheet(HSSFSheet sheet, Integer subjectId)throws Exception;

    /**
     * 根据知识点  题型  自动选择出指定数量的题目
     * @param topicIds          知识点
     * @param questionTypeId    题型
     * @param questionNum       所需题目的数量
     * @param teacherVo
     * @return
     */
    public List<QuestionVo> autoSelectByTopicIdsAndType(String[] topicIds, Integer questionTypeId,
           Integer questionNum, TeacherVo teacherVo)throws Exception;
}
