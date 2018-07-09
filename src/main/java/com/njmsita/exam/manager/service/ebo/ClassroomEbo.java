package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.authentic.dao.dao.StudentDao;
import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.ClassroomDao;
import com.njmsita.exam.manager.dao.dao.SchoolDao;
import com.njmsita.exam.manager.model.ClassroomVo;
import com.njmsita.exam.manager.model.SchoolVo;
import com.njmsita.exam.manager.service.ebi.ClassroomEbi;
import com.njmsita.exam.utils.exception.OperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 班级业务层实现类
 */
@Service
@Transactional
public class ClassroomEbo implements ClassroomEbi
{
    @Autowired
    private ClassroomDao classroomDao;
    @Autowired
    private SchoolDao schoolDao;

    @Autowired
    private StudentDao studentDao;

    public void save(ClassroomVo classroomVo) throws OperationException
    {
        if(
                null==classroomDao.findByNameFrom(classroomVo.getName(),classroomVo.getSchoolVo().getId())
                ){
            classroomDao.save(classroomVo);
        }else{
            SchoolVo schoolVo=schoolDao.get(classroomVo.getSchoolVo().getId());
            throw new OperationException("当前学校名为："+schoolVo.getName()+"的学校已存在班级名为："+classroomVo.getName()+"的班级，请勿重复操作");
        }
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

    public void update(ClassroomVo classroomVo) throws OperationException
    {
        ClassroomVo classroomVo1=classroomDao.findByNameFrom(classroomVo.getName(),classroomVo.getSchoolVo().getId());
        if(null==classroomVo1||classroomVo1.getId().equals(classroomVo.getId())){
            classroomDao.update(classroomVo);
        }else{
            SchoolVo schoolVo=schoolDao.get(classroomVo.getSchoolVo().getId());
            throw new OperationException("当前学校名为："+schoolVo.getName()+"的学校已存在班级名为："+classroomVo.getName()+"的班级，请勿重复操作");
        }
    }

    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------

    public void delete(ClassroomVo classroomVo) throws OperationException
    {
        List<StudentVo> students=studentDao.getAllByClassroomId(classroomVo.getId());
        if(0==students.size()){
            classroomDao.delete(classroomVo);
        }else{
            throw new OperationException("该班级有关联的学生，不能执行该操作");
        }

    }

    @Override
    public List<ClassroomVo> getAllBySchoolId(String id)
    {
        return classroomDao.getAllBySchoolId(id);
    }
}
