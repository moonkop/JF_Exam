package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.base.BaseQueryModel;
import com.njmsita.exam.manager.dao.dao.QuestionDao;
import com.njmsita.exam.manager.model.QuestionModel;
import com.njmsita.exam.manager.service.ebi.QuestionEbi;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class QuestionEbo implements QuestionEbi
{
    @Autowired
    private QuestionDao questionDao;

    public void save(QuestionModel questionModel)
    {
        questionDao.save(questionModel);
    }

    public void update(QuestionModel questionModel)
    {
        questionDao.update(questionModel);
    }

    public void delete(QuestionModel questionModel)
    {
        questionDao.delete(questionModel);
    }

    public List<QuestionModel> getAll()
    {
        return questionDao.getAll();
    }

    public QuestionModel get(Serializable uuid)
    {
        return questionDao.get(uuid);
    }

    public List<QuestionModel> getAll(BaseQueryModel qm, Integer pageNum, Integer pageCount)
    {
        return questionDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryModel qm)
    {
        return questionDao.getCount(qm);
    }
}
