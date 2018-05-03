package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.QuestionTypeDao;
import com.njmsita.exam.manager.dao.dao.SubjectDao;
import com.njmsita.exam.manager.model.QuestionTypeVo;
import com.njmsita.exam.manager.service.ebi.QuestionTypeEbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 题型业务层实现类
 */
@Service
@Transactional
public class QuestionTypeEbo implements QuestionTypeEbi
{
    @Autowired
    private QuestionTypeDao questionTypeDao;

    public void save(QuestionTypeVo QuestionTypeVo)
    {
        questionTypeDao.save(QuestionTypeVo);
    }

    public List<QuestionTypeVo> getAll()
    {
        return questionTypeDao.getAll();
    }

    public QuestionTypeVo get(Serializable uuid)
    {
        return questionTypeDao.get(uuid);
    }

    public List<QuestionTypeVo> getAll(BaseQueryVO qm, Integer pageNum, Integer pageCount)
    {
        return questionTypeDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryVO qm)
    {
        return questionTypeDao.getCount(qm);
    }

    public void update(QuestionTypeVo QuestionTypeVo)
    {
        questionTypeDao.update(QuestionTypeVo);
    }

    public void delete(QuestionTypeVo QuestionTypeVo)
    {
        questionTypeDao.delete(QuestionTypeVo);
    }

    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
}
