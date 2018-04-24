package com.njmsita.exam.authentic.service.ebo;

import com.njmsita.exam.authentic.dao.dao.RoleDao;
import com.njmsita.exam.authentic.dao.dao.StudentDao;
import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.authentic.model.TroleVo;
import com.njmsita.exam.authentic.service.ebi.StudentEbi;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.SchoolDao;
import com.njmsita.exam.manager.model.SchoolVo;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.FormatException;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.format.MD5Utils;
import com.njmsita.exam.utils.idutil.IdUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
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
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private SchoolDao schoolDao;

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

    public void update(StudentVo studentVo) throws OperationException
    {
        SchoolVo schoolVo=schoolDao.get(studentVo.getSchool().getId());
        if(null!=schoolVo){
            if(null!=studentVo.getId()&&!"".equals(studentVo.getId().trim())){
                studentDao.update(studentVo);
            }else{
                throw new OperationException("请不要进行非法操作");
            }
        }else{
            throw new OperationException("请不要进行非法操作");
        }
    }

    public void delete(StudentVo studentVo)
    {
        studentDao.delete(studentVo);
    }

    //--------------------------------------------以上为基本方法------------------------------------------
    //--------------------------------------------以上为基本方法------------------------------------------
    //--------------------------------------------以上为基本方法------------------------------------------
    //--------------------------------------------以上为基本方法------------------------------------------
    //--------------------------------------------以上为基本方法------------------------------------------
    //--------------------------------------------以上为基本方法------------------------------------------

    public StudentVo login(String studentId, String password, String schoolId, String loginIp)
    {
        //密码进行MD5加密
        password = MD5Utils.md5(password);
        StudentVo loginStudent=studentDao.getTeaByStuIdAndPwdFromSchool(studentId,password,schoolId);
        if (loginStudent!=null){
            loginStudent.setLastLoginTime(System.currentTimeMillis());
            loginStudent.setLastLoginIp(loginIp);
        }
        return loginStudent;
    }

    /**
     * 用户个人信息编辑
     * @param studentVo     用户数据
     * @param l             修改时间
     * @return
     */
    public StudentVo updateByLogic(StudentVo studentVo, long l) throws OperationException
    {
        StudentVo temp=null;
        if(null!=studentVo.getId()&&!"".equals(studentVo.getId().trim())){
            temp=studentDao.get(studentVo.getId());
            if(null!=temp){
                temp.setMail(studentVo.getMail());
                temp.setName(studentVo.getName());
                temp.setClassroom(studentVo.getClassroom());
                // temp.setIdCardNo(teacherVo.getIdCardNo());
                temp.setTelephone(studentVo.getTelephone());
                temp.setModifytime(l);
            }else{
                throw new OperationException("请不要进行非法操作！");
            }
        }
        return temp;
    }


    public void save(StudentVo studentVo, String schoolId) throws OperationException
    {

        StudentVo temp=studentDao.getByStudentIdToSchool(studentVo.getStudentId(),schoolId);
        if(temp==null){
            studentVo.setLastLoginIp("-");
            studentVo.setLastLoginTime(0l);
            studentVo.setCreatetime(System.currentTimeMillis());
            studentVo.setPassword(MD5Utils.md5(studentVo.getStudentId()));
            studentVo.setModifytime(0l);
            TroleVo role = roleDao.getByName(SysConsts.STUDENT_ROLE_NAME);
            studentVo.setRole(role);
            studentDao.save(studentVo);
        }else{
            SchoolVo schoolVo= schoolDao.get(schoolId);
            throw new OperationException("对不起，当前学校:"+schoolVo.getName()+"已存在学号为："+studentVo.getStudentId()+"的学生。请勿重复操作！");
        }
    }





















    public void bulkInputBySheet(HSSFSheet sheet, String schoolId) throws OperationException, FormatException
    {
        List<StudentVo> students=new ArrayList<StudentVo>();
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
                StudentVo temp=new StudentVo();
                temp.setStudentId(row.getCell(1).getStringCellValue());
                temp.setName(row.getCell(2).getStringCellValue());
                temp.setMail(row.getCell(6).getStringCellValue());
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
                temp.setPassword(MD5Utils.md5(temp.getStudentId()));
                temp.setId(IdUtil.getUUID());
                TroleVo role = roleDao.getByName(SysConsts.STUDENT_ROLE_NAME);
                temp.setRole(role);

                students.add(temp);
            }
        }

        //遍历检查在指定学校中是否有学号已存在的学生,并且表格中不能有重复学号
        for (int i=0;i<students.size()-1;i++)
        {
            if (null!=studentDao.getByStudentIdToSchool(students.get(i).getStudentId(),schoolId)){
                SchoolVo schoolVo= schoolDao.get(schoolId);
                throw new OperationException("对不起，当前学校:"+schoolVo.getName()+"已存在学号为："+students.get(i).getStudentId()+"的学生。请勿重复操作！");
            }
            for (int j=i+1;j<students.size();j++)
            {
                if(students.get(j).getStudentId().equals(students.get(i).getStudentId())){
                    throw new FormatException("对不起，表格中存在重复学号："+students.get(i).getStudentId()+"。请核对后重新导入");
                }
            }
        }

        //没有学号重复
        studentDao.bulkInput(students);
    }
}
