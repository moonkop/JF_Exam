package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.base.BaseQueryModel;
import com.njmsita.exam.manager.dao.dao.SubjectDao;
import com.njmsita.exam.manager.model.SubjectModel;
import com.njmsita.exam.manager.service.ebi.SubjectEbi;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class SubjectEbo implements SubjectEbi
{
    @Autowired
    private SubjectDao subjectDao;

    public void save(SubjectModel subjectModel)
    {
        subjectDao.save(subjectModel);
    }

    public void update(SubjectModel subjectModel)
    {
        subjectDao.update(subjectModel);
    }

    public void delete(SubjectModel subjectModel)
    {
        subjectDao.delete(subjectModel);
    }

    public List<SubjectModel> getAll()
    {
        return subjectDao.getAll();
    }

    public SubjectModel get(Serializable uuid)
    {
        return subjectDao.get(uuid);
    }

    public List<SubjectModel> getAll(BaseQueryModel qm, Integer pageNum, Integer pageCount)
    {
        return subjectDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryModel qm)
    {
        return subjectDao.getCount(qm);
    }
}
