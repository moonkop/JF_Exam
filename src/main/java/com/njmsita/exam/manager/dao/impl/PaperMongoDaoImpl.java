package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.PaperMongoDao;
import com.njmsita.exam.manager.model.PaperVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class PaperMongoDaoImpl implements PaperMongoDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void doCriteria(BaseQueryVO qm,Query query){

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
}
