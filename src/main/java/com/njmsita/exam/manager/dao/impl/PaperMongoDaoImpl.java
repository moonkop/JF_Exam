package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.PaperMongoDao;
import com.njmsita.exam.manager.model.PaperVo;
import com.njmsita.exam.manager.model.querymodel.PaperQueryModel;
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
public class PaperMongoDaoImpl implements PaperMongoDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void doCriteria(BaseQueryVO qm,Query query){
        PaperQueryModel paperQueryModel= (PaperQueryModel) qm;
        if (paperQueryModel.getSubject() != null)
        {
            query.addCriteria(Criteria.where("subject.id").is(paperQueryModel.getSubject().getId()));
        }
        if (paperQueryModel.getTeacher() != null)
        {
            query.addCriteria(Criteria.where("teacher.id").is(paperQueryModel.getSubject().getId()));
        }
    }

    public void insert(PaperVo paperVo) {
        this.mongoTemplate.insert(paperVo);
    }

    public PaperVo queryOne(Query query) {
        return this.mongoTemplate.findOne(query,PaperVo.class);
    }

    public List<PaperVo> query(Query query) {
        return this.mongoTemplate.find(query,PaperVo.class);
    }

    public List<PaperVo> queryAll() {
        return this.mongoTemplate.findAll(PaperVo.class);
    }

    public void updateOne(Query query, Update update) {
        this.mongoTemplate.updateFirst(query,update,PaperVo.class);
    }

    public void delete(Query query) {
        this.mongoTemplate.remove(query,PaperVo.class);
    }

    public void save(PaperVo paperVo)
    {
        this.mongoTemplate.save(paperVo);
    }

    public List<PaperVo> queryAll(BaseQueryVO qm, Integer pageNum, Integer pageCount)
    {
        Query query = new Query();
        doCriteria(qm,query);
        query.limit(pageCount);
        query.skip((pageNum-1)*pageCount);
        return this.mongoTemplate.find(query,PaperVo.class);
    }

    public Long getCount(BaseQueryVO qm)
    {
        Query query = new Query();
        doCriteria(qm,query);
        return this.mongoTemplate.count(query,PaperVo.class);
    }

    public void insert(PaperVo paperVo, String collection)
    {
        this.mongoTemplate.insert(paperVo,collection);
    }

    public PaperVo queryOne(Query query, String collection)
    {
        return this.mongoTemplate.findOne(query,PaperVo.class,collection);
    }

    public void delete(Query query, String collection)
    {
        this.mongoTemplate.remove(query,PaperVo.class,collection);
    }

    public void save(PaperVo paperVo, String collection)
    {
        this.mongoTemplate.save(paperVo,collection);
    }



    public void savaPaperToMongoExamPaper(PaperVo paperVo, String ExamId)
    {
        paperVo.setExamId(ExamId);
        paperVo.setId(IdUtil.getUUID());
        this.insert(paperVo, SysConsts.EXAM_PAPER_SAVA_MONGO_OF_COLLECTION);
    }

    /**
     * 从指定Collection取出指定的考试所需试卷
     *
     * @param examId
     *
     * @return
     */
    public PaperVo getPaperVoByExamId(String examId)
    {
        PaperVo paperVo = this.queryOne(new Query(Criteria.where("examId").is(examId)), SysConsts.EXAM_PAPER_SAVA_MONGO_OF_COLLECTION);

        return paperVo;
    }
    /**
     * 从指定Collection删除指定的考试所需试卷
     *
     *
     * @param ExamId@return
     */
    public void deletePaperFromMongoExamPaper(String ExamId)
    {
        this.delete(new Query(Criteria.where("examId").is(ExamId)), SysConsts.EXAM_PAPER_SAVA_MONGO_OF_COLLECTION);
    }

    /**
     * 更新指定Collection中指定的考试所需试卷
     *
     * @param paperVo
     *
     * @param examId
     * @return
     */
    public void updatePaperFromMongoExamPaper(PaperVo paperVo, String examId)
    {
        this.deletePaperFromMongoExamPaper(examId);
        this.savaPaperToMongoExamPaper(paperVo, examId);
    }

    public PaperVo get(String id)
    {
        return  this.queryOne(new Query(Criteria.where("id").is(id)));

    }

}
