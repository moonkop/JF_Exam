package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.base.BaseQueryModel;
import com.njmsita.exam.manager.dao.dao.PaperDao;
import com.njmsita.exam.manager.model.PaperModel;
import com.njmsita.exam.manager.service.ebi.PaperEbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
@Service
public class PaperEbo implements PaperEbi
{
    @Autowired
    private PaperDao paperDao;

    public void save(PaperModel paperModel)
    {
        paperDao.save(paperModel);
    }

    public void update(PaperModel paperModel)
    {
        paperDao.update(paperModel);
    }

    public void delete(PaperModel paperModel)
    {
        paperDao.delete(paperModel);
    }

    public List<PaperModel> getAll()
    {
        return paperDao.getAll();
    }

    public PaperModel get(Serializable uuid)
    {
        return paperDao.get(uuid);
    }

    public List<PaperModel> getAll(BaseQueryModel qm, Integer pageNum, Integer pageCount)
    {
        return paperDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryModel qm)
    {
        return paperDao.getCount(qm);
    }
}
