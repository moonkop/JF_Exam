package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.authentic.dao.dao.StudentDao;
import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.ClassroomDao;
import com.njmsita.exam.manager.model.ClassroomVo;
import com.njmsita.exam.manager.service.ebi.ClassroomEbi;
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
public class ClassroomEbo implements ClassroomEbi
{
    @Autowired
    private ClassroomDao classroomDao;

    @Autowired
    private StudentDao studentDao;

    public void save(ClassroomVo classroomVo)
    {
        classroomDao.save(classroomVo);
    }

    public List<ClassroomVo> getAll()
    {
        return classroomDao.getAll();
    }

    public ClassroomVo get(Serializable uuid)
    {
        return classroomDao.get(uuid);
    }

    public List<ClassroomVo> getAll(BaseQueryVO qm, Integer pageNum, Integer pageCount)
    {
        return classroomDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryVO qm)
    {
        return classroomDao.getCount(qm);
    }

    public void update(ClassroomVo classroomVo)
    {
        classroomDao.update(classroomVo);
    }

    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------

    public void delete(ClassroomVo classroomVo)
    {
        List<StudentVo> students=studentDao.getAllByClassroomId(classroomVo.getId());
        if(0==students.size()){
            classroomDao.delete(classroomVo);
        }else{
            //TODO 抛出异常
            System.out.println("这个班级有学生，不能删除");
        }

    }
}
