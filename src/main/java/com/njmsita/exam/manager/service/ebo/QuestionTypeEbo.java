package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.QuestionDao;
import com.njmsita.exam.manager.dao.dao.QuestionTypeDao;
import com.njmsita.exam.manager.model.QuestionTypeVo;
import com.njmsita.exam.manager.model.QuestionVo;
import com.njmsita.exam.manager.service.ebi.QuestionTypeEbi;
import com.njmsita.exam.utils.exception.OperationException;
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
    @Autowired
    private QuestionDao questionDao;

    public void save(QuestionTypeVo questionTypeVo) throws OperationException
    {
        QuestionTypeVo temp=questionTypeDao.getByName(questionTypeVo.getName());
        if (temp!=null){
            throw new OperationException("当前题型名称为："+questionTypeVo.getName()+"的题型已存在，请勿重复操作");
        }
        questionTypeDao.save(questionTypeVo);
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

    public void update(QuestionTypeVo questionTypeVo) throws OperationException
    {
        QuestionTypeVo temp=questionTypeDao.getByName(questionTypeVo.getName());
        if (null !=temp&& temp.getId()!= questionTypeVo.getId())
        {
            throw new OperationException("当前题型名称为："+questionTypeVo.getName()+"的题型已存在，请勿重复操作");
        }
        temp.setName(questionTypeVo.getName());
    }

    public void delete(QuestionTypeVo questionTypeVo) throws OperationException
    {
        List<QuestionVo> questionList= questionDao.getByQuestionType(questionTypeVo.getId());
        if (questionList.size()>0){
            throw new OperationException("当前题型下尚有题目存在，请不要删除！");
        }
        questionTypeDao.delete(questionTypeVo);
    }

    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
}
