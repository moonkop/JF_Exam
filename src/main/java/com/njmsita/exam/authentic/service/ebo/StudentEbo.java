package com.njmsita.exam.authentic.service.ebo;

import com.njmsita.exam.authentic.dao.dao.StudentDao;
import com.njmsita.exam.authentic.model.StudentEntity;
import com.njmsita.exam.authentic.service.ebi.StudentEbi;
import com.njmsita.exam.base.BaseQueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class StudentEbo implements StudentEbi
{
    @Autowired
    private StudentDao studentDao;

    public boolean save(StudentEntity studentEntity)
    {
        Serializable save = studentDao.save(studentEntity);
        return save==null?false:true;
    }

    public void update(StudentEntity studentEntity)
    {
        studentDao.update(studentEntity);
    }


    public List<StudentEntity> getAll()
    {
        return studentDao.getAll();
    }

    public StudentEntity get(Serializable uuid)
    {
        return studentDao.get(uuid);
    }

    public List<StudentEntity> getAll(BaseQueryModel qm, Integer pageNum, Integer pageCount)
    {
        return studentDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryModel qm)
    {
        return studentDao.getCount(qm);
    }
}
