package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.base.BaseQueryModel;
import com.njmsita.exam.manager.dao.dao.ExamDao;
import com.njmsita.exam.manager.model.ExamModel;
import com.njmsita.exam.manager.service.ebi.ExamEbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
@Transactional
@Service
public class ExamEbo implements ExamEbi
{
    @Autowired
    private ExamDao examDao;

    public void save(ExamModel examModel)
    {
        examDao.save(examModel);
    }

    public void update(ExamModel examModel)
    {
        examDao.update(examModel);
    }

    public void delete(ExamModel examModel)
    {
        examDao.delete(examModel);
    }

    public List<ExamModel> getAll()
    {
        return examDao.getAll();
    }

    public ExamModel get(Serializable uuid)
    {
        return examDao.get(uuid);
    }

    public List<ExamModel> getAll(BaseQueryModel qm, Integer pageNum, Integer pageCount)
    {
        return examDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryModel qm)
    {
        return examDao.getCount(qm);
    }
}
