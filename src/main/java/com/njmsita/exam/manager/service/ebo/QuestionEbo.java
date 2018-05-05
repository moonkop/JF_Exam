package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.QuestionDao;
import com.njmsita.exam.manager.model.QuestionVo;
import com.njmsita.exam.manager.service.ebi.QuestionEbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 题目业务层实现类
 */
@Service
@Transactional
public class QuestionEbo implements QuestionEbi
{
    @Autowired
    private QuestionDao questionDao;

    public void save(QuestionVo questionVo)
    {
        questionDao.save(questionVo);
    }

    public List<QuestionVo> getAll()
    {
        return questionDao.getAll();
    }

    public QuestionVo get(Serializable uuid)
    {
        return questionDao.get(uuid);
    }

    public List<QuestionVo> getAll(BaseQueryVO qm, Integer pageNum, Integer pageCount)
    {
        return questionDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryVO qm)
    {
        return questionDao.getCount(qm);
    }

    public void update(QuestionVo questionVo)
    {
        questionDao.update(questionVo);
    }

    public void delete(QuestionVo questionVo)
    {
        questionDao.delete(questionVo);
    }

    public List<QuestionVo> getBySubject(Integer id)
    {
        return questionDao.getBySubject(id);
    }

    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
}
