package com.njmsita.exam.authentic.service.ebo;

import com.njmsita.exam.authentic.dao.dao.StudentDao;
import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.authentic.service.ebi.StudentEbi;
import com.njmsita.exam.base.BaseQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 学生业务层实现类
 */
@Service
@Transactional
public class StudentEbo implements StudentEbi
{
    @Autowired
    private StudentDao studentDao;

    public void save(StudentVo studentEntity)
    {
        studentDao.save(studentEntity);
    }

    public List<StudentVo> getAll()
    {
        return studentDao.getAll();
    }

    public StudentVo get(Serializable uuid)
    {
        return studentDao.get(uuid);
    }

    public List<StudentVo> getAll(BaseQueryVO qm, Integer pageNum, Integer pageSize)
    {
        return studentDao.getAll(qm,pageNum,pageSize);
    }

    public Integer getCount(BaseQueryVO qm)
    {
        return studentDao.getCount(qm);
    }

    public void update(StudentVo studentEntity)
    {
        studentDao.update(studentEntity);
    }

    public void delete(StudentVo studentVo)
    {
        studentDao.delete(studentVo);
    }
}
