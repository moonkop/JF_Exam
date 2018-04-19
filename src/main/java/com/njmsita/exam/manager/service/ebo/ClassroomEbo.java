package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.base.BaseQueryModel;
import com.njmsita.exam.manager.dao.dao.ClassroomDao;
import com.njmsita.exam.manager.model.ClassroomModel;
import com.njmsita.exam.manager.service.ebi.ClassroomEbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
@Service
public class ClassroomEbo implements ClassroomEbi
{
    @Autowired
    private ClassroomDao classroomDao;

    public void save(ClassroomModel classroomModel)
    {
        classroomDao.save(classroomModel);
    }
    public void update(ClassroomModel classroomModel)
    {
        classroomDao.update(classroomModel);
    }

    public void delete(ClassroomModel classroomModel)
    {
        classroomDao.delete(classroomModel);
    }

    public List<ClassroomModel> getAll()
    {
        return classroomDao.getAll();
    }

    public ClassroomModel get(Serializable uuid)
    {
        return classroomDao.get(uuid);
    }

    public List<ClassroomModel> getAll(BaseQueryModel qm, Integer pageNum, Integer pageCount)
    {
        return classroomDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryModel qm)
    {
        return classroomDao.getCount(qm);
    }
}
