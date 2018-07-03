package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.manager.dao.dao.PaperExamDao;
import com.njmsita.exam.manager.model.PaperVo;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.idutil.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaperExamDaoImpl implements PaperExamDao
{
    @Autowired
    MongoTemplate mongoTemplate;



    public void insert(PaperVo paperVo) {
        this.mongoTemplate.insert(paperVo,SysConsts.EXAM_PAPER_COLLECTION_NAME);
    }

    public PaperVo queryOne(Query query) {
        return this.mongoTemplate.findOne(query,PaperVo.class,SysConsts.EXAM_PAPER_COLLECTION_NAME);
    }

    public List<PaperVo> query(Query query) {
        return this.mongoTemplate.find(query,PaperVo.class,SysConsts.EXAM_PAPER_COLLECTION_NAME);
    }

    public List<PaperVo> queryAll() {
        return this.mongoTemplate.findAll(PaperVo.class,SysConsts.EXAM_PAPER_COLLECTION_NAME);
    }

    public void updateOne(Query query, Update update) {
        this.mongoTemplate.updateFirst(query,update,PaperVo.class,SysConsts.EXAM_PAPER_COLLECTION_NAME);
    }

    public void delete(Query query) {
        this.mongoTemplate.remove(query,PaperVo.class,SysConsts.EXAM_PAPER_COLLECTION_NAME);
    }

    public void save(PaperVo paperVo)
    {
        this.mongoTemplate.save(paperVo,SysConsts.EXAM_PAPER_COLLECTION_NAME);
    }
    
    
    @Override
    public void savePaperToMongoExamPaper(PaperVo paperVo, String ExamId)
    {
        paperVo.setExamId(ExamId);
        paperVo.setId(IdUtil.getUUID());
        this.insert(paperVo);
    }

    /**
     * 从指定Collection取出指定的考试所需试卷
     *
     * @param examId
     *
     * @return
     */
    @Override
    public PaperVo getPaperVoByExamId(String examId)
    {
        PaperVo paperVo = this.queryOne(new Query(Criteria.where("examId").is(examId)));

        return paperVo;
    }
    /**
     * 从指定Collection删除指定的考试所需试卷
     *
     *
     * @param ExamId@return
     */
    @Override
    public void deletePaperFromMongoExamPaper(String ExamId)
    {
        this.delete(new Query(Criteria.where("examId").is(ExamId)));
    }

    /**
     * 更新指定Collection中指定的考试所需试卷
     *
     * @param paperVo
     *
     * @param examId
     * @return
     */
    @Override
    public void updatePaperFromMongoExamPaper(PaperVo paperVo, String examId)
    {
        this.deletePaperFromMongoExamPaper(examId);
        this.savePaperToMongoExamPaper(paperVo, examId);
    }
}
