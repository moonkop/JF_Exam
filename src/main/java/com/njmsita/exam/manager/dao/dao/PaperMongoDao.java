package com.njmsita.exam.manager.dao.dao;

import com.njmsita.exam.manager.model.PaperVo;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;


public interface PaperMongoDao {
    void insert(PaperVo paperVo);

    PaperVo queryOne(Query query);

    List<PaperVo> query(Query query);

    List<PaperVo> queryAll();

    void updateOne(Query query, Update update);

    void delete(Query query);
}
