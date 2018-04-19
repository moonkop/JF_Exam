package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.base.BaseQueryModel;
import com.njmsita.exam.manager.dao.dao.QuestionTypeDao;
import com.njmsita.exam.manager.model.QuestionTypeModel;
import com.njmsita.exam.manager.service.ebi.QuestionTypeEbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
@Service
public class QuestionTypeEbo implements QuestionTypeEbi
{
    @Autowired
    private QuestionTypeDao questionTypeDao;

    public void save(QuestionTypeModel questionTypeModel)
    {
        questionTypeDao.save(questionTypeModel);
    }

    public void update(QuestionTypeModel questionTypeModel)
    {
        questionTypeDao.update(questionTypeModel);
    }

    public void delete(QuestionTypeModel questionTypeModel)
    {
        questionTypeDao.delete(questionTypeModel);
    }

    public List<QuestionTypeModel> getAll()
    {
        return questionTypeDao.getAll();
    }

    public QuestionTypeModel get(Serializable uuid)
    {
        return questionTypeDao.get(uuid);
    }

    public List<QuestionTypeModel> getAll(BaseQueryModel qm, Integer pageNum, Integer pageCount)
    {
        return questionTypeDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryModel qm)
    {
        return questionTypeDao.getCount(qm);
    }
}
