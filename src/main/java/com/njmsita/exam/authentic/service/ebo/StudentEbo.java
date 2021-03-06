package com.njmsita.exam.authentic.service.ebo;

import com.njmsita.exam.authentic.dao.dao.RoleDao;
import com.njmsita.exam.authentic.dao.dao.StudentDao;
import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.authentic.model.TroleVo;
import com.njmsita.exam.authentic.service.ebi.StudentEbi;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.ClassroomDao;
import com.njmsita.exam.manager.dao.dao.SchoolDao;
import com.njmsita.exam.manager.model.ClassroomVo;
import com.njmsita.exam.manager.model.SchoolVo;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.FormatException;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.format.MD5Utils;
import com.njmsita.exam.utils.format.StringUtil;
import org.apache.bcel.generic.NEW;
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
    @Autowired
    private ClassroomDao classroomDao;

    public void save(StudentVo studentEntity) throws OperationException
    {
        checkGender(studentEntity);

        if (studentEntity.getSchool() == null || StringUtil.isEmpty(studentEntity.getSchool().getId()))
        {
            throw new OperationException("当前学校不能为空");
        }
        SchoolVo schoolVo = schoolDao.get(studentEntity.getSchool().getId());
        if (schoolVo == null)
        {
            throw new OperationException("当前学校不存在");
        }
        StudentVo studentTemp = studentDao.getByStudentIdFromSchool(studentEntity.getStudentId(), schoolVo.getId());
        if (studentTemp != null)
        {
            throw new OperationException("对不起，当前学校:" + schoolVo.getName() + "已存在学号为：" + studentEntity.getStudentId() + "的学生。请勿重复操作！");
        }

        //没有设置密码默认为学号
        if (StringUtil.isEmpty(studentEntity.getPassword()))
        {
            studentEntity.setPassword(MD5Utils.md5(studentEntity.getStudentId()));
        } else
        {
            studentEntity.setPassword(MD5Utils.md5(studentEntity.getPassword()));
        }


        //没有传递班级id则设置为空
        if (studentEntity.getClassroom() != null && studentEntity.getClassroom().getId() != null
                && !studentEntity.getClassroom().getId().equals(""))
        {
            ClassroomVo classroomVo = classroomDao.get(studentEntity.getClassroom().getId());
            studentEntity.setClassroom(classroomVo);
        }
        studentEntity.setSchool(schoolVo);
        studentEntity.setLastLoginIp("-");
        studentEntity.setLastLoginTime(0l);
        studentEntity.setCreatetime(System.currentTimeMillis());
        studentEntity.setModifytime(0l);
        TroleVo role = roleDao.getByName(SysConsts.STUDENT_ROLE_NAME);
        studentEntity.setRole(role);
        studentDao.save(studentEntity);



        /*
          if (schoolVo != null)
        {
            if (studentTemp == null)
            {

                //没有设置密码默认为学号
                if (null == studentEntity.getPassword() || "".equals(studentEntity.getPassword().trim()))
                {
                    studentEntity.setPassword(MD5Utils.md5(studentEntity.getStudentId()));
                }

                //没有传递班级id则设置为空
                if (studentEntity.getClassroom().getId() != null
                        && !studentEntity.getClassroom().getId().equals(""))
                {
                    ClassroomVo classroomVo = classroomDao.get(studentEntity.getClassroom().getId());
                    studentEntity.setClassroom(classroomVo);
                }
                studentEntity.setLastLoginIp("-");
                studentEntity.setLastLoginTime(0l);
                studentEntity.setCreatetime(System.currentTimeMillis());
                studentEntity.setModifytime(0l);
                TroleVo role = roleDao.getByName(SysConsts.STUDENT_ROLE_NAME);
                studentEntity.setRole(role);
                studentDao.save(studentEntity);
            } else
            {
                throw new OperationException("对不起，当前学校:" + schoolVo.getName() + "已存在学号为：" + studentEntity.getStudentId() + "的学生。请勿重复操作！");
            }
        } else
        {
            throw new OperationException("当前学校不存在，请不要进行非法操作！");
        }
        */
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
        return studentDao.getAll(qm, pageNum, pageSize);
    }

    public Integer getCount(BaseQueryVO qm)
    {
        return studentDao.getCount(qm);
    }

    public void update(StudentVo studentVo) throws OperationException
    {
        checkGender(studentVo);

        SchoolVo schoolVo = schoolDao.get(studentVo.getSchool().getId());
        ClassroomVo classroomVo = null;
        if (!StringUtil.isEmpty(studentVo.getClassroom().getId()))
        {
            classroomVo = classroomDao.get(studentVo.getClassroom().getId());
            if (classroomVo == null)
            {
                throw new OperationException("对不起！没有对应的班级，请不要进行非法操作！");
            }
        }
        if (schoolVo == null)
        {
            throw new OperationException("当前学校不存在，请不要进行非法操作！");
        }
//将要被修改的学生
        StudentVo studentPo = studentDao.get(studentVo.getId());

        if (studentPo == null)
        {
            throw new OperationException("未找到id为" + studentVo.getId() + "的学生。请勿非法操作！");
        }
        //从中取出的student 若有 且 若id与要修改的相同 则取出的是同一名学生 可以进行修改
        //若不同 则取出的是与将要传入的数据相同的学生信息一致的另一名学生 则代表有冲突 不能进行修改
        //若从中没有取出student  则没有冲突 可以修改
        StudentVo studentHasSameStudentIDAndSchool = studentDao.getByStudentIdFromSchool(studentVo.getStudentId(), schoolVo.getId());
        if (studentHasSameStudentIDAndSchool != null && !studentHasSameStudentIDAndSchool.getId().equals(studentVo.getId()))
        {
            throw new OperationException("对不起，当前学校:" + schoolVo.getName() + "已存在学号为：" + studentVo.getStudentId() + "的学生。请勿重复操作！");
        }
        studentPo.setRole(roleDao.get(SysConsts.STUDENT_ROLE_ID));
        studentPo.setStudentId(studentVo.getStudentId());
        studentPo.setName(studentVo.getName());
        studentPo.setMail(studentVo.getMail());
        studentPo.setIdCardNo(studentVo.getIdCardNo());
        studentPo.setTelephone(studentVo.getTelephone());
        if (studentVo.getPassword() != null)
        {
            studentPo.setPassword(MD5Utils.md5(studentVo.getPassword()));
        }
        studentPo.setGender(studentVo.getGender());
        studentPo.setModifytime(System.currentTimeMillis());
        studentPo.setSchool(schoolVo);
        studentPo.setClassroom(classroomVo);


        /*
        *
        *      if (schoolVo!=null){
            StudentVo temp=studentDao.get(studentVo.getId());
            if(temp!=null){
                if(studentDao.getByStudentIdFromSchool(studentVo.getStudentId(),schoolVo.getId()).getId().equals(studentVo.getId())){
                    temp.setStudentId(studentVo.getStudentId());
                    temp.setName(studentVo.getName());
                    temp.setMail(studentVo.getMail());
                    temp.setIdCardNo(studentVo.getIdCardNo());
                    temp.setTelephone(studentVo.getTelephone());
                    if(studentVo.getPassword()!=null){
                        temp.setPassword(MD5Utils.md5(studentVo.getPassword()));
                    }
                    temp.setModifytime(System.currentTimeMillis());
                    temp.setSchool(schoolVo);
                    temp.setClassroom(classroomVo);
                }else{
                    throw new OperationException("对不起，当前学校:"+schoolVo.getName()+"已存在学号为："+studentVo.getStudentId()+"的学生。请勿重复操作！");
                }
            }else{
                throw new OperationException("对不起，当前选择的学校不存在学号为："+studentVo.getStudentId()+"的学生。请勿非法作！");
            }
        }else{
            throw new OperationException("当前学校不存在，请不要进行非法操作！");
        }
        *
        * */


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
//        password = MD5Utils.md5(password);
        StudentVo loginStudent = studentDao.getTeaByStuIdAndPwdFromSchool(studentId, password, schoolId);
        if (loginStudent != null)
        {
            loginStudent.setLastLoginTime(System.currentTimeMillis());
            loginStudent.setLastLoginIp(loginIp);
        }
        return loginStudent;
    }

    /**
     * 用户个人信息编辑
     *
     * @param studentVo 用户数据
     * @param l         修改时间
     * @return
     */
    public StudentVo updateByLogic(StudentVo studentVo, long l) throws OperationException
    {
        StudentVo temp = null;
        if (null != studentVo.getId() && !"".equals(studentVo.getId().trim()))
        {
            temp = studentDao.get(studentVo.getId());
            if (null != temp)
            {
                temp.setMail(studentVo.getMail());
                temp.setName(studentVo.getName());

                temp.setTelephone(studentVo.getTelephone());
                temp.setModifytime(l);
            } else
            {
                throw new OperationException("请不要进行非法操作！");
            }
        }
        return temp;
    }

    public void bulkInputBySheet(HSSFSheet sheet, String schoolId, String classroomId) throws OperationException, FormatException
    {
        SchoolVo schoolVo = schoolDao.get(schoolId);
        if (schoolVo == null)
        {
            throw new OperationException("不存在当前选择的学校，请勿非法操作！");
        }

        ClassroomVo classroomVo = classroomDao.get(classroomId);
        if (classroomVo == null)
        {
            throw new OperationException("不存在当前选择的班级，请勿非法操作！");
        }
        List<StudentVo> students = new ArrayList<StudentVo>();
        for (Row row : sheet)
        {
            int rowNum = row.getRowNum();
            //跳过标题行
            if (rowNum == 0 || rowNum == 1)
                continue;
            try
            {
                //如果id为空则不录入该行
                if (null != row.getCell(1) && null != row.getCell(1).getStringCellValue() && !
                        "".equals(row.getCell(1).getStringCellValue()))
                {

                    //读取表中数据并进行校验和属性设置
                    StudentVo temp = setStudent(row);

                    //设置初始信息
                    temp.setClassroom(classroomVo);

                    temp.setLastLoginIp("-");
                    temp.setLastLoginTime(0l);
                    temp.setCreatetime(System.currentTimeMillis());
                    temp.setModifytime(0l);
                    temp.setSchool(schoolVo);
                    //默认密码为工号
                    temp.setPassword(MD5Utils.md5(temp.getStudentId()));
                    TroleVo role = roleDao.getByName(SysConsts.STUDENT_ROLE_NAME);
                    temp.setRole(role);

                    students.add(temp);
                }
            } catch (IllegalStateException e)
            {
                throw new OperationException("表中数据格式错误，请核对后重试");
            }
        }

        //验证表格中是否有重复学号信息，以及数据库中是否已经存在数据
        distinct(students, schoolVo);

        //没有学号重复
        studentDao.bulkInput(students);
    }

    public void resetPassword(String id)
    {
        StudentVo temp= new StudentVo();
        temp = studentDao.get(id);
        temp.setPassword(MD5Utils.md5(temp.getStudentId()));
    }

    public void modifyPassword(StudentVo loginStudent, String oldPassword, String newPassword) throws OperationException
    {
        if (StringUtil.isEmpty(newPassword))
        {
            throw new OperationException("新密码不能为空，请不要进行非法操作！");
        }
        loginStudent = studentDao.get(loginStudent.getId());
        loginStudent.setPassword(MD5Utils.md5(newPassword));
    }

    /**
     * 验证表格中是否有重复学号信息，以及数据库中是否已经存在数据
     *
     * @param students 学生信息集合
     * @param schoolVo 所属学校
     * @throws OperationException
     * @throws FormatException
     */
    private void distinct(List<StudentVo> students, SchoolVo schoolVo) throws OperationException, FormatException
    {
        //遍历检查在指定学校中是否有学号已存在的学生,并且表格中不能有重复学号
        for (int i = 0; i < students.size() - 1; i++)
        {
            if (null != studentDao.getByStudentIdFromSchool(students.get(i).getStudentId(), schoolVo.getId()))
            {
                throw new OperationException("对不起，当前学校:" + schoolVo.getName() + "已存在学号为：" + students.get(i).getStudentId() + "的学生。请勿重复操作！");
            }
            for (int j = i + 1; j < students.size(); j++)
            {
                if (students.get(j).getStudentId().equals(students.get(i).getStudentId()))
                {
                    throw new FormatException("对不起，表格中存在重复学号：" + students.get(i).getStudentId() + "。请核对后重新导入");
                }
            }
        }
    }

    /**
     * 批量导入数据读取及校验
     *
     * @param row 行数据
     * @return
     * @throws FormatException
     */
    private StudentVo setStudent(Row row) throws FormatException
    {
        StudentVo temp = new StudentVo();
        int rowNum = row.getRowNum();

        String studentId = "";
        String name = "";
        String idCardNo = "";
        String telephone = "";
        String mail = "";

        //读取数据
        if (row.getCell(1) != null)
        {
            studentId = row.getCell(1).getStringCellValue();
        }
        if (row.getCell(2) != null)
        {
            name = row.getCell(2).getStringCellValue();
        }
        if (row.getCell(4) != null)
        {
            idCardNo = row.getCell(4).getStringCellValue();
        }
        if (row.getCell(5) != null)
        {
            telephone = row.getCell(5).getStringCellValue();
        }
        if (row.getCell(6) != null)
        {
            mail = row.getCell(6).getStringCellValue();
        }

        //属性校验
        if (!StringUtil.isEmpty(idCardNo)&&idCardNo.length() > 18)
        {
            throw new FormatException("表格中第" + (rowNum + 1) + "行的“身份证号”属性格式错误，请核对后重新导入！");
        }
        if (!StringUtil.isEmpty(telephone)&&telephone.length() > 11)
        {
            throw new FormatException("表格中第" + (rowNum + 1) + "行的“手机号”属性格式错误，请核对后重新导入！");
        }
        if (StringUtil.isEmpty(name))
        {
            throw new FormatException("表格中第" + (rowNum + 1) + "行的“姓名”属性为空，请核对后重新导入！");
        }
        if (StringUtil.isEmpty(studentId))
        {
            throw new FormatException("表格中第" + (rowNum + 1) + "行的“学号”属性为空，请核对后重新导入！");
        } else if (studentId.length() > 32)
        {
            throw new FormatException("表格中第" + (rowNum + 1) + "行的“学号”属性格式不正确，请核对后重新导入！");
        }
        //设置性别
        if (SysConsts.INFO_TEACHER_OR_SUTDENT_GENDER_MALE.equals(row.getCell(3).getStringCellValue()))
        {
            temp.setGender(0);
        } else if (SysConsts.INFO_TEACHER_OR_SUTDENT_GENDER_FEMALE.equals(row.getCell(3).getStringCellValue()))
        {
            temp.setGender(1);
        } else
        {
            throw new FormatException("表格中第" + (rowNum + 1) + "行的“性别”属性格式错误，请核对后重新导入！");
        }

        //属性设置
        temp.setStudentId(studentId);
        temp.setName(name);
        temp.setIdCardNo(idCardNo);
        temp.setMail(mail);
        temp.setTelephone(telephone);

        return temp;
    }

    /**
     * 校验性别
     *
     * @param studentEntity
     * @throws OperationException
     */
    private void checkGender(StudentVo studentEntity) throws OperationException
    {
        if (studentEntity.getGender() != 0 && studentEntity.getGender() != 1)
        {
            throw new OperationException("性别格式错误，请不要进行非法操作");
        }
    }
}
