package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.authentic.dao.dao.StudentDao;
import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.ClassroomDao;
import com.njmsita.exam.manager.dao.dao.SubjectDao;
import com.njmsita.exam.manager.model.ClassroomVo;
import com.njmsita.exam.manager.model.SubjectVo;
import com.njmsita.exam.manager.service.ebi.SubjectEbi;
import com.njmsita.exam.manager.service.ebi.SubjectEbi;
import com.njmsita.exam.utils.exception.OperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 学科业务层实现类
 */
@Service
@Transactional
public class SubjectEbo implements SubjectEbi
{
    @Autowired
    private SubjectDao subjectDao;

    public void save(SubjectVo subjectVo)
    {
        subjectDao.save(subjectVo);
    }

    public List<SubjectVo> getAll()
    {
        return subjectDao.getAll();
    }

    public SubjectVo get(Serializable uuid)
    {
        return subjectDao.get(uuid);
    }

    public List<SubjectVo> getAll(BaseQueryVO qm, Integer pageNum, Integer pageCount)
    {
        return subjectDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryVO qm)
    {
        return subjectDao.getCount(qm);
    }

    public void update(SubjectVo subjectVo)
    {
        subjectDao.update(subjectVo);
    }

    public void delete(SubjectVo subjectVo)
    {
        subjectDao.delete(subjectVo);
    }

    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
}
