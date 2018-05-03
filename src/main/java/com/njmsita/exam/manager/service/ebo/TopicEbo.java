package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.TopicDao;
import com.njmsita.exam.manager.model.TopicVo;
import com.njmsita.exam.manager.service.ebi.TopicEbi;
import com.njmsita.exam.manager.service.ebi.TopicEbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 知识点业务层实现类
 */
@Service
@Transactional
public class TopicEbo implements TopicEbi
{
    @Autowired
    private TopicDao TopicDao;

    public void save(TopicVo topicVo)
    {
        TopicDao.save(topicVo);
    }

    public List<TopicVo> getAll()
    {
        return TopicDao.getAll();
    }

    public TopicVo get(Serializable uuid)
    {
        return TopicDao.get(uuid);
    }

    public List<TopicVo> getAll(BaseQueryVO qm, Integer pageNum, Integer pageCount)
    {
        return TopicDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryVO qm)
    {
        return TopicDao.getCount(qm);
    }

    public void update(TopicVo topicVo)
    {
        TopicDao.update(topicVo);
    }

    public void delete(TopicVo topicVo)
    {
        TopicDao.delete(topicVo);
    }

    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
}
