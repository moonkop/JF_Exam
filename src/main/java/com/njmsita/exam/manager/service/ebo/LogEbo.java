package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.LogDao;
import com.njmsita.exam.manager.model.LogVo;
import com.njmsita.exam.manager.service.ebi.LogEbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 学校业务层实现类
 */
@Service
@Transactional
public class LogEbo implements LogEbi
{
    @Autowired
    private LogDao logdao;

    public void save(LogVo LogVo)
    {
        logdao.save(LogVo);
    }

    public List<LogVo> getAll()
    {
        return logdao.getAll();
    }

    public LogVo get(Serializable uuid)
    {
        return logdao.get(uuid);
    }

    public List<LogVo> getAll(BaseQueryVO qm, Integer pageNum, Integer pageCount)
    {
        return logdao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryVO qm)
    {
        return logdao.getCount(qm);
    }

    public void update(LogVo LogVo)
    {
        logdao.update(LogVo);
    }

    public void delete(LogVo logVo)
    {
        logdao.delete(logVo);
    }

    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
}
