package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.PaperMongoDao;
import com.njmsita.exam.manager.dao.dao.SubjectDao;
import com.njmsita.exam.manager.model.PaperVo;
import com.njmsita.exam.manager.model.SubjectVo;
import com.njmsita.exam.manager.service.ebi.PaperEbi;
import com.njmsita.exam.utils.exception.OperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * 试卷业务层实现类
 */
@Service
@Transactional
public class PaperEbo implements PaperEbi
{
    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private PaperMongoDao paperMongoDao;

    public List<PaperVo> getAll()
    {
        return paperMongoDao.queryAll();
    }

    public PaperVo get(Serializable uuid)
    {
        return paperMongoDao.queryOne(new Query(Criteria.where("id").is(uuid)));
    }

    public List<PaperVo> getAll(BaseQueryVO qm, Integer pageNum, Integer pageCount)
    {
        return paperMongoDao.queryAll(qm,pageNum,pageCount);
    }

    public Integer getCount(BaseQueryVO qm)
    {
        return paperMongoDao.getCount(qm).intValue();
    }

    public void save(PaperVo paperVo) throws OperationException
    {
        infoValid(paperVo);
        paperMongoDao.insert(paperVo);
    }

    public void update(PaperVo paperVo) throws OperationException
    {
        PaperVo temp= paperMongoDao.queryOne(new Query(Criteria.where("id").is(paperVo.getId())));
        if(temp==null){
            throw new OperationException("当前选择的试卷不存在，请不要进行非法操作！");
        }
        temp.setComment(paperVo.getComment());
        temp.setTitle(paperVo.getTitle());
        temp.setQuestionList(paperVo.getQuestionList());
        paperMongoDao.save(temp);
    }

    public void delete(PaperVo paper)
    {
        paperMongoDao.delete(new Query(Criteria.where("id").is(paper.getId())));
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
        if(paperVo.getSubject()==null){
            throw new OperationException("选择科目不能为空，请不要进行非法操作！");
        }
        if(paperVo.getSubject().getId()==0||paperVo.getSubject().getId()==null){
            throw new OperationException("选择科目不能为空，请不要进行非法操作！");
        }
        SubjectVo subject=subjectDao.get(paperVo.getSubject().getId());
        if (subject==null){
            throw new OperationException("选择科目不存在，请不要进行非法操作！");
        }
        paperVo.setSubject(subject);
    }

}
