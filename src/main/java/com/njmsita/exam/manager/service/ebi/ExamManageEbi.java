package com.njmsita.exam.manager.service.ebi;

import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.base.BaseEbi;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.ScheduleVo;
import com.njmsita.exam.manager.model.StudentExamQuestionVo;
import com.njmsita.exam.manager.model.StudentExamVo;
import com.njmsita.exam.manager.model.querymodel.ExamEditWrapper;
import com.njmsita.exam.manager.model.querymodel.ExamListQueryModel;
import com.njmsita.exam.manager.service.ebo.ExamInvoker;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.exception.UnAuthorizedException;
import com.njmsita.exam.utils.exception.UnLoginException;
import org.apache.commons.collections.iterators.ObjectGraphIterator;

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
     */
    void save(ExamEditWrapper wrapper, TeacherVo teacherVo) throws Exception;

    /**
     * 修改考试
     */
    void update(ExamEditWrapper wrapper, TeacherVo teacherVo) throws Exception;


    ExamVo getWithPaper(Serializable uuid);

    /**
     * 查询当前就是所发起的考试
     *
     * @param teacherId
     *
     * @return
     */
    List<ExamVo> getByCreateTeacher(String teacherId) throws Exception;

    /**
     * 查询当前教师所参与阅卷的试卷
     *
     * @param teacherId
     *
     * @return
     */
    List<ExamVo> getByMarkTeacher(String teacherId) throws Exception;

    /**
     * 审核通过
     *
     * @param examVo
     * @param teacherVo
     */
    void setPass(ExamVo examVo, TeacherVo teacherVo) throws Exception;

    /**
     * 审核不通过
     *
     * @param examVo
     * @param teacherVo
     */
    void setNoPass(ExamVo examVo, TeacherVo teacherVo) throws Exception;

    /**
     * 取消考试
     *
     * @param examVo
     * @param teacherVo
     */
    void cancel(ExamVo examVo, TeacherVo teacherVo) throws Exception;

    /**
     * 删除考试
     *
     * @param examVo
     * @param teacherVo
     */
    void deleteCanceled(ExamVo examVo, TeacherVo teacherVo) throws Exception;

    /**
     * 更新阅卷教师
     *
     * @param examVo
     * @param markTeachers
     */
    void updateMarkTeacher(ExamVo examVo, String[] markTeachers) throws Exception;

    /**
     * 判断该考试该教师可以进行的操作
     *
     * @param exam
     *
     * @return
     */
    Set<String> getValidOperations(ExamVo exam, TeacherVo loginTeacher) throws UnLoginException, Exception;

    Set<String> getValidOperations(StudentExamVo studentExamVo, StudentVo student) throws UnLoginException;


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
    boolean checkPermission(String permission, TeacherVo loginUser, ExamVo exam) throws UnAuthorizedException, UnLoginException, Exception;

    boolean checkPermission(String permission, StudentVo loginUser, StudentExamVo exam) throws UnAuthorizedException, UnLoginException, Exception;

    void invoke(ExamInvoker examInvoker);

    /**
     * 管理员考试列表
     *
     * @param id
     * @param examQueryModel
     *
     * @return
     */
    List<ExamVo> getAllByAdmin(String id, ExamListQueryModel examQueryModel) throws Exception;

    /**
     * 获取指定考试所有学生的试卷
     *
     * @param examVo
     * @param login
     *
     * @return
     */
    List<StudentExamVo> getAllStudentExamByExam(ExamVo examVo, TeacherVo login) throws Exception;

    ExamVo getExamNotNull(ExamVo examVo) throws OperationException;

    ExamVo getExamNotNull(String examId) throws OperationException;

    void log(ScheduleVo scheduleVo, String method);

    void outmodedSchedule(ScheduleVo scheduleVo);

    void stop(String examId, TeacherVo attribute) throws Exception;

    /**
     * 获取考试的可执行任务
     *
     * @return
     */
    List<ScheduleVo> getAllByExecutable();

    /**
     * 更新任务状态
     *
     * @param scheduleVo
     */
    void updateSchedule(ScheduleVo scheduleVo);
}
