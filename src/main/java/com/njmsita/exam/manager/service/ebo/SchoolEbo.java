package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.base.BaseQueryModel;
import com.njmsita.exam.manager.dao.dao.SchoolDao;
import com.njmsita.exam.manager.model.SchoolModel;
import com.njmsita.exam.manager.service.ebi.SchoolEbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
@Service
public class SchoolEbo implements SchoolEbi
{
    @Autowired
    private SchoolDao schoolDao;

    public void save(SchoolModel schoolModel)
    {
        schoolDao.save(schoolModel);
    }

    public void update(SchoolModel schoolModel)
    {
        schoolDao.update(schoolModel);
    }

    public void delete(SchoolModel schoolModel)
    {
        schoolDao.delete(schoolModel);
    }

    public List<SchoolModel> getAll()
    {
        return schoolDao.getAll();
    }

    public SchoolModel get(Serializable uuid)
    {
        return schoolDao.get(uuid);
    }

    public List<SchoolModel> getAll(BaseQueryModel qm, Integer pageNum, Integer pageCount)
    {
        return schoolDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryModel qm)
    {
        return schoolDao.getCount(qm);
    }
}
