package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.base.BaseQueryModel;
import com.njmsita.exam.manager.dao.dao.LogDao;
import com.njmsita.exam.manager.model.LogModel;
import com.njmsita.exam.manager.service.ebi.LogEbi;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class LogEbo implements LogEbi
{
    @Autowired
    private LogDao logDao;

    public void save(LogModel logModel)
    {
        logDao.save(logModel);
    }

    public void update(LogModel logModel)
    {
        logDao.update(logModel);
    }

    public void delete(LogModel logModel)
    {
        logDao.delete(logModel);
    }

    public List<LogModel> getAll()
    {
        return logDao.getAll();
    }

    public LogModel get(Serializable uuid)
    {
        return logDao.get(uuid);
    }

    public List<LogModel> getAll(BaseQueryModel qm, Integer pageNum, Integer pageCount)
    {
        return logDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryModel qm)
    {
        return logDao.getCount(qm);
    }
}
