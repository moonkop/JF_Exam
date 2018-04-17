package com.njmsita.exam.authentic.service.ebo;

import com.njmsita.exam.authentic.dao.dao.TeacherDao;
import com.njmsita.exam.authentic.model.TeacherModel;
import com.njmsita.exam.authentic.service.ebi.TeacherEbi;
import com.njmsita.exam.base.BaseQueryModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class TeacherEbo implements TeacherEbi
{
    @Autowired
    private TeacherDao teacherDao;

    public void save(TeacherModel teacherModel)
    {
        teacherDao.save(teacherModel);
    }

    public void update(TeacherModel teacherModel)
    {
        teacherDao.update(teacherModel);
    }

    public void delete(TeacherModel teacherModel)
    {
        teacherDao.delete(teacherModel);
    }

    public List<TeacherModel> getAll()
    {
        return teacherDao.getAll();
    }

    public TeacherModel get(Serializable uuid)
    {
        return teacherDao.get(uuid);
    }

    public List<TeacherModel> getAll(BaseQueryModel qm, Integer pageNum, Integer pageCount)
    {
        return teacherDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryModel qm)
    {
        return teacherDao.getCount(qm);
    }
}
