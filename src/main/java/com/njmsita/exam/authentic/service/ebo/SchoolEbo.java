package com.njmsita.exam.authentic.service.ebo;

import com.njmsita.exam.authentic.dao.dao.SchoolDao;
import com.njmsita.exam.authentic.model.SchoolVo;
import com.njmsita.exam.authentic.service.ebi.SchoolEbi;
import com.njmsita.exam.base.BaseQueryModel;
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
public class SchoolEbo implements SchoolEbi
{
    @Autowired
    private SchoolDao schoolDao;

    public void save(SchoolVo schoolVo)
    {
        schoolDao.save(schoolVo);
    }

    public List<SchoolVo> getAll()
    {
        return schoolDao.getAll();
    }

    public SchoolVo get(Serializable uuid)
    {
        return schoolDao.get(uuid);
    }

    public List<SchoolVo> getAll(BaseQueryModel qm, Integer pageNum, Integer pageCount)
    {
        return schoolDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryModel qm)
    {
        return schoolDao.getCount(qm);
    }

    public void update(SchoolVo schoolVo)
    {
        schoolDao.update(schoolVo);
    }
}
