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

/**
 * 教师业务成实现类
 */
@Service
@Transactional
public class TeacherEbo implements TeacherEbi
{
    @Autowired
    private TeacherDao teaDao;

    public void save(TeacherVo teacherVo)
    {
        teaDao.save(teacherVo);
    }


    public List<TeacherVo> getAll()
    {
        return teaDao.getAll();
    }

    public TeacherVo get(Serializable uuid)
    {
        return teaDao.get(uuid);
    }

    public List<TeacherVo> getAll(BaseQueryModel qm, Integer pageNum, Integer pageCount)
    {
        return teaDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryModel qm)
    {
        return teaDao.getCount(qm);
    }

    //------------------------------以上为基本方法--------------------------------------------
    //------------------------------以上为基本方法--------------------------------------------
    //------------------------------以上为基本方法--------------------------------------------
    //------------------------------以上为基本方法--------------------------------------------
    //------------------------------以上为基本方法--------------------------------------------


    public TeacherVo login(String teacherId, String password, String loginIp)
    {
        //密码进行MD5加密
        password = MD5Utils.md5(password);
        TeacherVo loginTea=teaDao.getTeaByTeaIdAndPwd(teacherId,password);
        if (loginTea!=null){
            loginTea.setLastLoginTime(System.currentTimeMillis());
            loginTea.setLastLoginIp(loginIp);
        }
        return loginTea;
    }

    public TeacherVo updateByLogic(TeacherVo teacherVo, long l)
    {
        System.out.println(teacherVo);
        TeacherVo temp=null;
        if(null!=teacherVo.getId()&&!"".equals(teacherVo.getId().trim())){
            temp=teaDao.get(teacherVo.getId());
            if(null!=temp){
                temp.setMail(teacherVo.getMail());
               // temp.setIdCardNo(teacherVo.getIdCardNo());
                temp.setTelephone(teacherVo.getTelephone());
                temp.setModifytime(l);
            }
        }else{
            //TODO 抛出异常
            throw new RuntimeException("未找到该用户");
        }
        return temp;
    }
}
