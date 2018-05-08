package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.PaperDao;
import com.njmsita.exam.manager.model.PaperVo;
import com.njmsita.exam.manager.service.ebi.PaperEbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 试卷业务层实现类
 */
@Service
@Transactional
public class PaperEbo implements PaperEbi
{
    @Autowired
    private PaperDao paperDao;

    public void save(PaperVo paperVo)
    {
        paperDao.save(paperVo);
    }

    public List<PaperVo> getAll()
    {
        return paperDao.getAll();
    }

    public PaperVo get(Serializable uuid)
    {
        return paperDao.get(uuid);
    }

    public List<PaperVo> getAll(BaseQueryVO qm, Integer pageNum, Integer pageCount)
    {
        return paperDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryVO qm)
    {
        return paperDao.getCount(qm);
    }

    public void update(PaperVo paperVo)
    {
        paperDao.update(paperVo);
    }

    public void delete(PaperVo paper)
    {
        paperDao.delete(paper);
    }

    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
}
