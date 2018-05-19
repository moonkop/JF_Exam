package com.njmsita.exam.manager.dao.impl;

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
}
