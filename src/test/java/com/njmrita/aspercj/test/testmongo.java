package com.njmrita.aspercj.test;


import com.njmsita.exam.manager.dao.dao.QuestionDao;
import com.njmsita.exam.manager.model.QuestionVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class testmongo
{
    @Autowired
    MongoTemplate template;

    @Autowired
    QuestionDao questionDao;

    @Test
    public void questiontest()
    {
        QuestionVo questionVo= this.template.findOne(new Query(Criteria.where("id").is("7872be652aaa4ab6910a289a13434f3c")),QuestionVo.class);


    }
}
