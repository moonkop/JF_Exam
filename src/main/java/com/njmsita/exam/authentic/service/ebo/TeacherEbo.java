package com.njmsita.exam.authentic.service.ebo;

import com.njmsita.exam.authentic.dao.dao.RoleDao;
import com.njmsita.exam.authentic.dao.dao.TeacherDao;
import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.authentic.model.TroleVo;
import com.njmsita.exam.authentic.service.ebi.RoleEbi;
import com.njmsita.exam.authentic.service.ebi.TeacherEbi;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.FormatException;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.format.MD5Utils;
import com.njmsita.exam.utils.format.StringUtil;
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
    private RoleDao roleDao;

    public void save(TeacherVo teacherVo) throws OperationException
    {
        checkGender(teacherVo);
        TeacherVo temp = teaDao.getByTeacherId(teacherVo.getTeacherId());
        if (temp == null)
        {
            teacherVo.setLastLoginIp("-");
            teacherVo.setLastLoginTime(0l);
            teacherVo.setCreatetime(System.currentTimeMillis());

            //密码不设默认为空
            if(teacherVo.getPassword()==null||teacherVo.getPassword().trim().equals("")){
                teacherVo.setPassword(MD5Utils.md5(teacherVo.getTeacherId()));
            }else{
                teacherVo.setPassword(MD5Utils.md5(teacherVo.getPassword()));
            }
            teacherVo.setModifytime(0l);
           // TroleVo role = roleDao.getByName(SysConsts.TEACHER_ROLE_NAME);
            //todo fixed 逻辑修改 以前台传的role为准
            teaDao.save(teacherVo);
        } else
        {
            throw new OperationException("对不起，当前系统已存在职工号为：" + teacherVo.getTeacherId() + "的教师。请勿重复操作！");
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
        return teaDao.getAll(qm, pageNum, pageSize);
    }

    public Integer getCount(BaseQueryVO qm)
    {
        return teaDao.getCount(qm);
    }

    /**
     * 管理员更新
     *
     * @param teacherVo
     */
    public void update(TeacherVo teacherVo) throws OperationException
    {
        checkGender(teacherVo);
        TeacherVo temp=teaDao.get(teacherVo.getId());
        if(temp!=null){
            temp.setTeacherId(teacherVo.getTeacherId());
            temp.setName(teacherVo.getName());
            temp.setMail(teacherVo.getMail());
            temp.setIdCardNo(teacherVo.getIdCardNo());
            temp.setTelephone(teacherVo.getTelephone());
            temp.setTroleVo(teacherVo.getTroleVo());
            if (!StringUtil.isEmpty(teacherVo.getPassword()))
            {
                temp.setPassword(MD5Utils.md5(teacherVo.getPassword()));
            }
            temp.setModifytime(System.currentTimeMillis());
        } else
        {
            throw new OperationException("对不起，未找到id为：" + teacherVo.getId() + "的教师。");
            // throw new OperationException("对不起，当前系统不存在职工号为："+teacherVo.getTeacherId()+"的教师。请勿非法作！");
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
        TeacherVo loginTea = teaDao.getTeaByTeaIdAndPwd(teacherId, password);
        if (loginTea != null)
        {
            loginTea.setLastLoginTime(System.currentTimeMillis());
            loginTea.setLastLoginIp(loginIp);
        }
        return loginTea;
    }

    /**
     * 用户个人信息编辑
     *
     * @param teacherVo 用户收据
     * @param l         修改时间
     * @return
     */
    public TeacherVo updateByLogic(TeacherVo teacherVo, long l) throws OperationException
    {
        TeacherVo temp = null;
        if (null != teacherVo.getId() && !"".equals(teacherVo.getId().trim()))
        {
            temp = teaDao.get(teacherVo.getId());
            if (null != temp)
            {
                temp.setMail(teacherVo.getMail());
                // temp.setIdCardNo(teacherVo.getIdCardNo());
                temp.setTelephone(teacherVo.getTelephone());
                temp.setModifytime(l);
            }
        } else
        {
            throw new OperationException("请不要进行非法操作！");
        }
        return temp;
    }

    public void bulkInputBySheet(HSSFSheet sheet) throws OperationException, FormatException
    {
        List<TeacherVo> teachers = new ArrayList<TeacherVo>();
        for (Row row : sheet)
        {
            int rowNum = row.getRowNum();
            //跳过标题行
            if (rowNum == 0 || rowNum == 1)
                continue;
            try
            {
                //如果id为空则不录入该行
                if(null!=row.getCell(1)&&null!=row.getCell(1).getStringCellValue()&&!
                        "".equals(row.getCell(1).getStringCellValue())){

                    //批量导入数据读取及校验
                    TeacherVo temp=setTeacher(row);

                    //设置初始信息
                    temp.setLastLoginIp("-");
                    temp.setLastLoginTime(0l);
                    temp.setCreatetime(System.currentTimeMillis());
                    //默认密码为工号
                    temp.setPassword(MD5Utils.md5(temp.getTeacherId()));
                    temp.setId(IdUtil.getUUID());
                    TroleVo role = roleDao.getByName(SysConsts.TEACHER_ROLE_NAME);
                    temp.setTroleVo(role);

                    teachers.add(temp);
                }
            }catch (IllegalStateException e){
                throw new OperationException("表中数据格式错误，请核对后重试");
            }
        }

        //验证表格中是否有重复学号信息，以及数据库中是否已经存在数据
        distinct(teachers);

        //没有重复教师职工号重复
        teaDao.bulkInput(teachers);
    }


    /**
     * 验证表格中是否有重复学号信息，以及数据库中是否已经存在数据
     * @param teachers              教师信息集合
     * @throws OperationException
     * @throws FormatException
     */
    private void distinct(List<TeacherVo> teachers) throws OperationException, FormatException
    {
        //遍历检查是否有职工号已存在的学生,并且表格中不能有重复
        for (int i=0;i<teachers.size()-1;i++)
        {
            if (null != teaDao.getByTeacherId(teachers.get(i).getTeacherId()))
            {
                throw new OperationException("对不起，职工号为：" + teachers.get(i).getTeacherId() + "的教师已存在。请勿重复操作！");
            }
            for (int j = i + 1; j < teachers.size(); j++)
            {
                if(teachers.get(j).getTeacherId().equals(teachers.get(i).getTeacherId())){
                    throw new FormatException("对不起，表格中存在重复学号："+teachers.get(i).getTeacherId()+"。请核对后重新导入");
                }
            }
        }
    }

    /**
     * 批量导入数据读取及校验
     * @param row      行数据
     * @return
     * @throws FormatException
     */
    private TeacherVo setTeacher(Row row) throws FormatException
    {
        TeacherVo temp=new TeacherVo();
        int rowNum=row.getRowNum();


        String teacherId="";
        String name="";
        String idCardNo="";
        String telephone="";
        String mail="";

        //读取数据
        if(row.getCell(1)!=null){
            teacherId=row.getCell(1).getStringCellValue();
        }
        if(row.getCell(2)!=null){
            name=row.getCell(2).getStringCellValue();
        }
        if(row.getCell(4)!=null){
            mail=row.getCell(4).getStringCellValue();
        }
        if(row.getCell(5)!=null){
            idCardNo=row.getCell(5).getStringCellValue();
        }
        if(row.getCell(6)!=null){
            telephone=row.getCell(6).getStringCellValue();
        }

        //属性校验
        if(idCardNo.length()>18){
            throw new FormatException("表格中第"+(rowNum+1)+"行的“身份证号”属性格式错误，请核对后重新导入！");
        }
        if(telephone.length()>11){
            throw new FormatException("表格中第"+(rowNum+1)+"行的“手机号”属性格式错误，请核对后重新导入！");
        }
        if(name==null||name.trim().equals("")){
            throw new FormatException("表格中第"+(rowNum+1)+"行的“姓名”属性为空，请核对后重新导入！");
        }
        if(teacherId==null||teacherId.trim().equals("")){
            throw new FormatException("表格中第"+(rowNum+1)+"行的“职工号”属性为空，请核对后重新导入！");
        }
        //设置性别
        if(SysConsts.INFO_TEACHER_OR_SUTDENT_GENDER_MALE.equals(row.getCell(3).getStringCellValue())){
            temp.setGender(0);
        }else if (SysConsts.INFO_TEACHER_OR_SUTDENT_GENDER_FEMALE.equals(row.getCell(3).getStringCellValue())){
            temp.setGender(1);
        }else{
            throw new FormatException("表格中第"+(rowNum+1)+"行的“性别”属性格式错误，请核对后重新导入！");
        }

        //属性设置
        temp.setTeacherId(teacherId);
        temp.setName(name);
        temp.setIdCardNo(idCardNo);
        temp.setMail(mail);
        temp.setTelephone(telephone);

        return temp;
    }

    private void checkGender(TeacherVo teacherVo) throws OperationException
    {
        if(teacherVo.getGender()!=0&&teacherVo.getGender()!=1){
            throw new OperationException("性别格式错误，请不要进行非法操作");
        }
    }
}
