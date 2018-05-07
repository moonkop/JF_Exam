package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.ClassroomDao;
import com.njmsita.exam.manager.dao.dao.SchoolDao;
import com.njmsita.exam.authentic.dao.dao.StudentDao;
import com.njmsita.exam.manager.model.ClassroomVo;
import com.njmsita.exam.manager.model.SchoolVo;
import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.manager.service.ebi.SchoolEbi;
import com.njmsita.exam.utils.exception.OperationException;
import org.hibernate.Hibernate;
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
public class SchoolEbo implements SchoolEbi
{
    @Autowired
    private SchoolDao schoolDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ClassroomDao classroomDao;

    public void save(SchoolVo schoolVo) throws OperationException
    {
        if(null==schoolDao.findByName(schoolVo.getName())){
            schoolDao.save(schoolVo);
        }else{
            throw new OperationException("当前学校名为："+schoolVo.getName()+"的学校已存在，请勿重复操作");
        }
    }

    public List<SchoolVo> getAll()
    {
        return schoolDao.getAll();
    }

    public SchoolVo get(Serializable uuid)
    {
        return schoolDao.get(uuid);
    }

    public List<SchoolVo> getAll(BaseQueryVO qm, Integer pageNum, Integer pageCount)
    {
        return schoolDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryVO qm)
    {
        return schoolDao.getCount(qm);
    }

    public void update(SchoolVo schoolVo) throws OperationException
    {
        SchoolVo schoolVoHasSameName=schoolDao.findByName(schoolVo.getName());

        if(schoolVoHasSameName==null||(schoolVoHasSameName.getId()!=schoolVo.getId())){
         //   schoolDao.update(schoolVo);
            SchoolVo schoolVoPersistent = schoolDao.get(schoolVo.getId());
            if (schoolVoPersistent == null)
            {
                throw new OperationException("不存在该id的学校，请勿非法操作");
            }
            schoolVoPersistent.setName(schoolVo.getName());
        }else{
            throw new OperationException("当前学校名为："+schoolVo.getName()+"的学校已存在，请勿重复操作");
        }
    }

    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------

    public void delete(SchoolVo school) throws OperationException
    {
        List<StudentVo> students=studentDao.getAllBySchoolId(school.getId());
        List<ClassroomVo> classrooms=classroomDao.getAllBySchoolId(school.getId());

        //当且仅当该学校下没有任何学生和班级时才可以删除
        if(0==students.size()&&classrooms.size()==0){
            schoolDao.delete(school);
        }else{
            throw new OperationException("该学校有关联的学生或班级，不能进行删除操作！");
        }

    }
}
