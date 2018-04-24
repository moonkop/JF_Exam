package com.njmsita.exam.authentic.service.ebi;

import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.base.BaseEbi;
import com.njmsita.exam.utils.exception.FormatException;
import com.njmsita.exam.utils.exception.OperationException;
import org.apache.poi.hssf.usermodel.HSSFSheet;

/**
 * 学生业务层接口
 */
public interface StudentEbi extends BaseEbi<StudentVo>
{
    /**
     * 登陆(双重验证：学生id 密码  及  所属学校)
     * @param studentId         验证学生id
     * @param password          验证密码
     * @param schoolId          学校id
     * @param loginIp           ip地址
     * @return
     */
    public StudentVo login(String studentId, String password, String schoolId, String loginIp);

    /**
     * 用户个人逻辑更新用户数据
     * @param studentVo     用户收据
     * @param l             修改时间
     */
    public StudentVo updateByLogic(StudentVo studentVo, long l) throws OperationException;

    /**
     * 根据表格批量导入学生信息
     * @param sheet         表格
     * @param schoolId
     */
    public void bulkInputBySheet(HSSFSheet sheet, String schoolId) throws OperationException, FormatException;


}
