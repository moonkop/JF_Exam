package com.njmsita.exam.authentic.service.ebo;

import com.njmsita.exam.authentic.dao.dao.TeacherDao;
import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.authentic.service.ebi.TeacherEbi;
import com.njmsita.exam.base.BaseQueryModel;
import com.njmsita.exam.utils.format.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class TeacherEbo implements TeacherEbi
{
    @Autowired
    private TeacherDao teacherDao;

    public void save(TeacherVo teacherVo)
    {
        teacherDao.save(teacherVo);
    }

    public void update(TeacherVo teacherVo)
    {
        teacherDao.update(teacherVo);
    }

    public void delete(TeacherVo teacherVo)
    {
        teacherDao.delete(teacherVo);
    }

    public List<TeacherVo> getAll()
    {
        return teacherDao.getAll();
    }

    public TeacherVo get(Serializable uuid)
    {
        return teacherDao.get(uuid);
    }

    public List<TeacherVo> getAll(BaseQueryModel qm, Integer pageNum, Integer pageCount)
    {
        return teacherDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryModel qm)
    {
        return teacherDao.getCount(qm);
    }

    public TeacherVo login(String teacherId, String password, String loginIp)
    {
        password = MD5Utils.md5(password);
        TeacherVo loginTea= teacherDao.getTeaByTeaIdAndPwd(teacherId,password);
        if (loginTea!=null){
            loginTea.setLastLoginTime(System.currentTimeMillis());
            loginTea.setLastLoginIp(loginIp);
        }
        return loginTea;
    }
}
