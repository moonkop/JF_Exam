package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.QuestionDao;
import com.njmsita.exam.manager.dao.dao.SubjectDao;
import com.njmsita.exam.manager.model.QuestionVo;
import com.njmsita.exam.manager.model.SubjectVo;
import com.njmsita.exam.manager.service.ebi.SubjectEbi;
import com.njmsita.exam.utils.exception.OperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * 学科业务层实现类
 */
@Service
@Transactional
public class SubjectEbo implements SubjectEbi
{
    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private QuestionDao questionDao;

    public void save(SubjectVo subjectVo) throws OperationException
    {
        SubjectVo temp=subjectDao.getByName(subjectVo.getName());
        if (temp!=null){
            throw new OperationException("当前学科名称为："+subjectVo.getName()+"的学科已存在，请勿重复操作");
        }
        subjectDao.save(subjectVo);
    }

    public List<SubjectVo> getAll()
    {
        return subjectDao.getAll();
    }

    public SubjectVo get(Serializable uuid)
    {
        return subjectDao.get(uuid);
    }

    public List<SubjectVo> getAll(BaseQueryVO qm, Integer pageNum, Integer pageCount)
    {
        return subjectDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryVO qm)
    {
        return subjectDao.getCount(qm);
    }

    public void update(SubjectVo subjectVo) throws OperationException
    {
        SubjectVo temp=subjectDao.getByName(subjectVo.getName());
        if (null !=temp&& temp.getId()!=subjectVo.getId())
        {
            throw new OperationException("当前学科名称为："+subjectVo.getName()+"的学科已存在，请勿重复操作");
        }
        if (temp == null)
        {
            temp = subjectDao.get(subjectVo.getId());
        }
        temp.setName(subjectVo.getName());
    }

    public void delete(SubjectVo subjectVo) throws OperationException
    {
        List<QuestionVo> questionList= questionDao.getBySubject(subjectVo.getId());
        if (questionList.size()>0){
            throw new OperationException("当前学科下尚有题目存在，请不要删除！");
        }
        subjectDao.delete(subjectVo);
    }

    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
}
