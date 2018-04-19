package com.njmsita.exam.authentic.service.ebo;

import com.njmsita.exam.authentic.dao.dao.StudentDao;
import com.njmsita.exam.authentic.model.StudentModel;
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

    public void save(StudentModel studentModel)
    {
        studentDao.save(studentModel);
    }

    public void update(StudentModel studentModel)
    {
        studentDao.update(studentModel);
    }

    public void delete(StudentModel studentModel)
    {
        studentDao.delete(studentModel);
    }

    public List<StudentModel> getAll()
    {
        return studentDao.getAll();
    }

    public StudentModel get(Serializable uuid)
    {
        return studentDao.get(uuid);
    }

    public List<StudentModel> getAll(BaseQueryModel qm, Integer pageNum, Integer pageCount)
    {
        return studentDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryModel qm)
    {
        return studentDao.getCount(qm);
    }
}
