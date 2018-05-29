package com.njmsita.exam.manager.service.ebi;

import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.authentic.model.UserModel;
import com.njmsita.exam.base.BaseEbi;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.ScheduleVo;
import com.njmsita.exam.manager.model.StudentExamQuestionVo;
import com.njmsita.exam.manager.model.StudentExamVo;
import com.njmsita.exam.manager.model.querymodel.ExamQueryModel;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.exception.UnAuthorizedException;
import com.njmsita.exam.utils.exception.UnLoginException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 考试业务层接口
 */
public interface ExamManageEbi extends BaseEbi<ExamVo>
{

    /**
     * 发起考试
     *
     * @param examVo       考试信息
     * @param markTeachers 阅卷教师
     * @param classroomIds 参加班级
     * @param paperId      试卷id
     */
    public void save(ExamVo examVo, String[] markTeachers, String paperId, String[] classroomIds) throws OperationException;

    /**
     * 修改考试
     *
     * @param examVo       考试信息
     * @param markTeachers 阅卷教师
     * @param classroomIds 参加班级
     * @param paperId      试卷id
     */
    public void update(ExamVo examVo, String[] markTeachers, String paperId, String[] classroomIds,TeacherVo teacherVo) throws Exception;


    public ExamVo getWithPaper(Serializable uuid);
    /**
     * 查询当前就是所发起的考试
     *
     * @param teacherId
     *
     * @return
     */
    public List<ExamVo> getByCreateTeacher(String teacherId) throws Exception;

    /**
     * 查询当前教师所参与阅卷的试卷
     *
     * @param teacherId
     *
     * @return
     */
    public List<ExamVo> getByMarkTeacher(String teacherId) throws Exception;

    /**
     * 审核通过
     *
     * @param examVo
     * @param teacherVo
     */
    public void setPass(ExamVo examVo, TeacherVo teacherVo) throws Exception;

    /**
     * 审核不通过
     *
     * @param examVo
     * @param teacherVo
     */
    public void setNoPass(ExamVo examVo, TeacherVo teacherVo) throws Exception;

    /**
     * 取消考试
     *
     * @param examVo
     * @param teacherVo
     */
    public void cancel(ExamVo examVo, TeacherVo teacherVo) throws Exception;

    /**
     * 删除考试
     *
     * @param examVo
     * @param teacherVo
     */
    public void deleteCanceled(ExamVo examVo, TeacherVo teacherVo) throws Exception;

    /**
     * 更新阅卷教师
     *
     * @param examVo
     * @param markTeachers
     */
    public void updateMarkTeacher(ExamVo examVo, String[] markTeachers) throws Exception;



    /**
     * 判断该考试该教师可以进行的操作
     *
     * @param exam
     * @param loginUser
     *
     * @return
     */
    public Set<String> getValidOperations(ExamVo exam, UserModel loginUser) throws UnLoginException;

    /**
     * 验证是否有当前操作的权限
     *
     * @param permission
     * @param loginUser
     * @param exam
     *
     * @return
     *
     * @throws UnAuthorizedException
     */
    public boolean checkPermission(String permission, UserModel loginUser, ExamVo exam) throws UnAuthorizedException, UnLoginException, Exception;

    /**
     * 判断该考试该教师可以进行的操作
     *
     * @param exam
     * @param UserId
     *
     * @return
     */
    public Set<String> getValidOperations(ExamVo exam, String UserId);

    /**
     * 验证是否有当前操作的权限
     *
     * @param permission
     * @param UserId
     * @param exam
     *
     * @return
     *
     * @throws UnAuthorizedException
     */
    public boolean checkPermission(String permission, String UserId, ExamVo exam) throws UnAuthorizedException;


    /**
     * 管理员考试列表
     *
     * @param id
     * @param examQueryModel
     * @param pageNum
     * @param pageSize
     *
     * @return
     */
    public List<ExamVo> getAllByAdmin(String id, ExamQueryModel examQueryModel, Integer pageNum, Integer pageSize) throws Exception;


    /**
     * 获取指定考试所有学生的试卷
     *
     * @param examVo
     * @param login
     *
     * @return
     */
    public List<StudentExamVo> getAllStudentExamByExam(ExamVo examVo, TeacherVo login) throws Exception;

    /**
     * 获取指定学生试卷的所有待批改题目作答情况
     *
     * @param studentExamVo
     *
     * @return
     */
    public List<StudentExamQuestionVo> getAllStudentexamQuestionByStudentExam(StudentExamVo studentExamVo) throws Exception;

    public ExamVo getExamNotNull(ExamVo examVo) throws OperationException;

    public void saveLog(ScheduleVo scheduleVo, String method);

}
