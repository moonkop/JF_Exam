package com.njmsita.exam.manager.dao.dao;

import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.PaperVo;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;


public interface PaperMongoDao
{
    public void insert(PaperVo paperVo);

    public PaperVo queryOne(Query query);

    public List<PaperVo> query(Query query);

    public List<PaperVo> queryAll();

    public void updateOne(Query query, Update update);

    public void delete(Query query);

    public void save(PaperVo temp);

    public List<PaperVo> queryAll(BaseQueryVO qm, Integer pageNum, Integer pageCount);

    public Long getCount(BaseQueryVO qm);

    public void insert(PaperVo paperVo, String collection);

    public PaperVo queryOne(Query query, String collection);

    public void delete(Query query, String collection);

    public void save(PaperVo paperVo, String collection);

    public void savaPaperToMongoExamPaper(ExamVo examVo);

    public ExamVo queryPaperFromMongoExamPaper(ExamVo examVo);

    public void deletePaperFromMongoExamPaper(ExamVo examVo);

    public void updatePaperFromMongoExamPaper(ExamVo examVo);
}
