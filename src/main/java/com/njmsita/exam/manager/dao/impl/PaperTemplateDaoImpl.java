package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.PaperTemplateDao;
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
public class PaperTemplateDaoImpl implements PaperTemplateDao
{
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
            query.addCriteria(Criteria.where("teacher.id").is(paperQueryModel.getTeacher().getId()));
        }
    }

    public void insert(PaperVo paperVo) {
        this.mongoTemplate.insert(paperVo,SysConsts.PAPER_TEMPLATE_COLLECTION_NAME);
    }

    public PaperVo queryOne(Query query) {
        return this.mongoTemplate.findOne(query,PaperVo.class,SysConsts.PAPER_TEMPLATE_COLLECTION_NAME);
    }

    public List<PaperVo> query(Query query) {
        return this.mongoTemplate.find(query,PaperVo.class,SysConsts.PAPER_TEMPLATE_COLLECTION_NAME);
    }

    public List<PaperVo> queryAll() {
        return this.mongoTemplate.findAll(PaperVo.class,SysConsts.PAPER_TEMPLATE_COLLECTION_NAME);
    }

    public void updateOne(Query query, Update update) {
        this.mongoTemplate.updateFirst(query,update,PaperVo.class,SysConsts.PAPER_TEMPLATE_COLLECTION_NAME);
    }

    public void delete(Query query) {
        this.mongoTemplate.remove(query,PaperVo.class,SysConsts.PAPER_TEMPLATE_COLLECTION_NAME);
    }

    public void save(PaperVo paperVo)
    {
        this.mongoTemplate.save(paperVo,SysConsts.PAPER_TEMPLATE_COLLECTION_NAME);
    }

    public List<PaperVo> queryAll(BaseQueryVO qm, Integer pageNum, Integer pageCount)
    {
        Query query = new Query();
        doCriteria(qm,query);
        query.limit(pageCount);
        query.skip((pageNum-1)*pageCount);
        return this.mongoTemplate.find(query,PaperVo.class,SysConsts.PAPER_TEMPLATE_COLLECTION_NAME);
    }

    public Long getCount(BaseQueryVO qm)
    {
        Query query = new Query();
        doCriteria(qm,query);
        return this.mongoTemplate.count(query,PaperVo.class,SysConsts.PAPER_TEMPLATE_COLLECTION_NAME);
    }

    public PaperVo get(String id)
    {
        return  this.queryOne(new Query(Criteria.where("id").is(id)));

    }

}
