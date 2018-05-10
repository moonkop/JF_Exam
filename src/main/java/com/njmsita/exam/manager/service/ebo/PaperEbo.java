package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.PaperDao;
import com.njmsita.exam.manager.dao.dao.QuestionDao;
import com.njmsita.exam.manager.dao.dao.SubjectDao;
import com.njmsita.exam.manager.model.PaperVo;
import com.njmsita.exam.manager.model.QuestionVo;
import com.njmsita.exam.manager.model.SubjectVo;
import com.njmsita.exam.manager.service.ebi.PaperEbi;
import com.njmsita.exam.utils.exception.OperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.plugin2.util.PojoUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 试卷业务层实现类
 */
@Service
@Transactional
public class PaperEbo implements PaperEbi
{
    @Autowired
    private PaperDao paperDao;
    @Autowired
    private SubjectDao subjectDao;

    public List<PaperVo> getAll()
    {
        return paperDao.getAll();
    }

    public PaperVo get(Serializable uuid)
    {
        return paperDao.get(uuid);
    }

    public List<PaperVo> getAll(BaseQueryVO qm, Integer pageNum, Integer pageCount)
    {
        return paperDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryVO qm)
    {
        return paperDao.getCount(qm);
    }

    public void save(PaperVo paperVo) throws OperationException
    {
        infoValid(paperVo);
        paperDao.save(paperVo);
    }

    public void update(PaperVo paperVo) throws OperationException
    {
        infoValid(paperVo);
        PaperVo temp= paperDao.get(paperVo.getId());
        if(temp==null){
            throw new OperationException("当前选择的试卷不存在，请不要进行非法操作！");
        }
        temp.setSubjectVo(paperVo.getSubjectVo());
        temp.setComment(paperVo.getComment());
        temp.setQuestionContain(paperVo.getQuestionContain());
        temp.setTitle(paperVo.getTitle());
    }

    public void delete(PaperVo paper)
    {
        paperDao.delete(paper);
    }


    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------

    /**
     * 信息校验
     * @param paperVo
     */
    private void infoValid(PaperVo paperVo) throws OperationException
    {
        if(paperVo.getSubjectVo()==null){
            throw new OperationException("选择科目不能为空，请不要进行非法操作！");
        }
        if(paperVo.getSubjectVo().getId()==0||paperVo.getSubjectVo().getId()==null){
            throw new OperationException("选择科目不能为空，请不要进行非法操作！");
        }
        SubjectVo subject=subjectDao.get(paperVo.getSubjectVo().getId());
        if (subject==null){
            throw new OperationException("选择科目不存在，请不要进行非法操作！");
        }
        paperVo.setSubjectVo(subject);
    }

}
