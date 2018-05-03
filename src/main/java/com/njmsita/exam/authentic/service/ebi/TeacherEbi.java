package com.njmsita.exam.authentic.service.ebi;

import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.base.BaseEbi;
import com.njmsita.exam.utils.exception.FormatException;
import com.njmsita.exam.utils.exception.OperationException;
import org.apache.poi.hssf.usermodel.HSSFSheet;

/**
 * 教师业务层接口
 */
public interface TeacherEbi extends BaseEbi<TeacherVo>
{
    /**
     * 登陆
     * @param teacherId     教师ID
     * @param password      密码
     * @param loginIp       登陆ip
     * @return
     */
    public TeacherVo login(String teacherId, String password, String loginIp);

    /**
     * 用户个人逻辑更新用户数据
     * @param teacherVo     用户收据
     * @param l             修改时间
     */
    public TeacherVo updateByLogic(TeacherVo teacherVo, long l) throws OperationException;

    /**
     * 根据表格批量导入教师信息
     * @param sheet         表格
     */
    public void bulkInputBySheet(HSSFSheet sheet) throws OperationException, FormatException;

    /**
     * 密码重置
     * @param teacherVo
     */
    public void resetPassword(TeacherVo teacherVo);

    /**
     * 修改密码
     * @param loginTea      当前用户
     * @param ordPassword   原始密码
     * @param newPassword   新密码
     */
    public void modifyPassword(TeacherVo loginTea, String ordPassword, String newPassword) throws OperationException;
}
