package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.manager.dao.dao.SchoolDao;
import com.njmsita.exam.authentic.dao.dao.StudentDao;
import com.njmsita.exam.manager.model.SchoolVo;
import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.manager.service.ebi.SchoolEbi;
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

    public void save(SchoolVo schoolVo)
    {
        schoolDao.save(schoolVo);
    }

    public List<SchoolVo> getAll()
    {
        return schoolDao.getAll();
    }

    public SchoolVo get(Serializable uuid)
    {
        return schoolDao.get(uuid);
    }

    public List<SchoolVo> getAll(BaseQueryModel qm, Integer pageNum, Integer pageCount)
    {
        return schoolDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryModel qm)
    {
        return schoolDao.getCount(qm);
    }

    public void update(SchoolVo schoolVo)
    {
        schoolDao.update(schoolVo);
    }

    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------

    public void delete(SchoolVo school)
    {
        List<StudentVo> students=studentDao.getAllBySchoolId(school.getId());
        if(0==students.size()){
            schoolDao.delete(school);
        }else{
            //TODO 抛出异常
            System.out.println("这个学校有学生，不能删除");
        }

    }
}
