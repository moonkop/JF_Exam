package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.base.BaseQueryModel;
import com.njmsita.exam.manager.dao.dao.TopicDao;
import com.njmsita.exam.manager.model.TopicModel;
import com.njmsita.exam.manager.service.ebi.TopicEbi;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class TopicEbo implements TopicEbi
{
    @Autowired
    private TopicDao topicDao;

    public void save(TopicModel topicModel)
    {
        topicDao.save(topicModel);
    }

    public void update(TopicModel topicModel)
    {
        topicDao.update(topicModel);
    }

    public void delete(TopicModel topicModel)
    {
        topicDao.delete(topicModel);
    }

    public List<TopicModel> getAll()
    {
        return topicDao.getAll();
    }

    public TopicModel get(Serializable uuid)
    {
        return topicDao.get(uuid);
    }

    public List<TopicModel> getAll(BaseQueryModel qm, Integer pageNum, Integer pageCount)
    {
        return topicDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryModel qm)
    {
        return topicDao.getCount(qm);
    }
}
