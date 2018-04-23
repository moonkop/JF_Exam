package com.njmsita.exam.authentic.service.ebo;

import com.njmsita.exam.authentic.dao.dao.TeacherDao;
import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.authentic.model.TroleVo;
import com.njmsita.exam.authentic.service.ebi.RoleEbi;
import com.njmsita.exam.authentic.service.ebi.TeacherEbi;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.format.MD5Utils;
import com.njmsita.exam.utils.idutil.IdUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
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

    @Autowired
    private RoleEbi roleEbi;

    public void save(TeacherVo teacherVo)
    {
        //todo 不能添加重复教师id
        TeacherVo temp=teaDao.getByTeacherId(teacherVo.getTeacherId());
        if(temp==null){
            teacherVo.setLastLoginIp("-");
            teacherVo.setLastLoginTime(0l);
            teacherVo.setCreatetime(System.currentTimeMillis());
            teacherVo.setPassword(MD5Utils.md5(teacherVo.getPassword()));
            teacherVo.setModifytime(0l);
            TroleVo role = roleEbi.getByName(SysConsts.TEACHER_ROLE_NAME);
            teacherVo.setTroleVo(role);
            teaDao.save(teacherVo);
        }else{
            //todo 抛出异常
        }
    }


    public List<TeacherVo> getAll()
    {
        return teaDao.getAll();
    }

    public TeacherVo get(Serializable uuid)
    {
        return teaDao.get(uuid);
    }

    public List<TeacherVo> getAll(BaseQueryVO qm, Integer pageNum, Integer pageSize)
    {
        return teaDao.getAll(qm,pageNum,pageSize);
    }

    public Integer getCount(BaseQueryVO qm)
    {
        return teaDao.getCount(qm);
    }

    /**
     * 管理员更新
     * @param teacherVo
     */
    public void update(TeacherVo teacherVo)
    {
        if(teaDao.getByTeacherId(teacherVo.getTeacherId())==null){
            TeacherVo temp=teaDao.get(teacherVo.getId());
            temp.setTeacherId(teacherVo.getTeacherId());
            temp.setName(teacherVo.getName());
            temp.setMail(teacherVo.getMail());
            temp.setIdCardNo(teacherVo.getIdCardNo());
            temp.setTelephone(teacherVo.getTelephone());
            temp.setPassword(MD5Utils.md5(teacherVo.getPassword()));
            temp.setModifytime(System.currentTimeMillis());
        }
    }

    public void delete(TeacherVo teacherVo)
    {
        teaDao.delete(teacherVo);
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

    /**
     * 用户个人信息编辑
     * @param teacherVo     用户收据
     * @param l             修改时间
     * @return
     */
    public TeacherVo updateByLogic(TeacherVo teacherVo, long l)
    {
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

    public void bulkInputBySheet(HSSFSheet sheet)
    {
        List<TeacherVo> teachers=new ArrayList<TeacherVo>();
        for (Row row : sheet)
        {
            int rowNum=row.getRowNum();
            //跳过标题行
            if (rowNum==0||rowNum==1)
                continue;
            //如果id为空则不录入该行
            if(null!=row.getCell(0).getStringCellValue()&&!
                    "".equals(row.getCell(0).getStringCellValue())){

                //读取表中数据
                TeacherVo temp=new TeacherVo();
                temp.setTeacherId(row.getCell(0).getStringCellValue());
                temp.setName(row.getCell(1).getStringCellValue());
                temp.setMail(row.getCell(3).getStringCellValue());
                temp.setIdCardNo(row.getCell(4).getStringCellValue());
                temp.setTelephone(row.getCell(5).getStringCellValue());

                //设置性别
                if(SysConsts.INFO_TEACHER_OR_SUTDENT_GENDER_MALE.equals(row.getCell(2).getStringCellValue())){
                    temp.setGender(0);
                }else if (SysConsts.INFO_TEACHER_OR_SUTDENT_GENDER_FEMALE.equals(row.getCell(2).getStringCellValue())){
                    temp.setGender(1);
                }else{
                    temp.setGender(null);
                }

                //设置初始信息
                temp.setLastLoginIp("-");
                temp.setLastLoginTime(0l);
                temp.setCreatetime(System.currentTimeMillis());
                //默认密码为工号
                temp.setPassword(MD5Utils.md5(temp.getTeacherId()));
                temp.setId(IdUtil.getUUID());
                TroleVo role = roleEbi.getByName(SysConsts.TEACHER_ROLE_NAME);
                temp.setTroleVo(role);

                teachers.add(temp);
            }
        }

        //遍历检查是否有有教师编号已存在,并且表格中不能有重复教师id
        for (int i=0;i<teachers.size()-1;i++)
        {
            if (null!=teaDao.getByTeacherId(teachers.get(i).getTeacherId())){
                //TODO  重复教师编号抛出异常
                System.out.println("职工号为："+teachers.get(i).getTeacherId()+"的教师已存在");
                //没有异常暂时用return代替
                return;
            }
            for (int j=i+1;j<teachers.size();j++)
            {
                if(teachers.get(j).getTeacherId().equals(teachers.get(i).getTeacherId())){
                    //TODO  重复教师编号抛出异常
                    System.out.println("表格中存在重复职工号："+teachers.get(i).getTeacherId());
                    //没有异常暂时用return代替
                    return;
                }
            }
        }

        //没有重复教师职工号重复
        teaDao.bulkInput(teachers);
    }
}
