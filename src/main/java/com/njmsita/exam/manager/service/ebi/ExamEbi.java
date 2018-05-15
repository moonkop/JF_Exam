package com.njmsita.exam.manager.service.ebi;

import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.base.BaseEbi;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.StudentExamQuestionVo;
import com.njmsita.exam.manager.model.querymodel.ExamQueryModel;
import com.njmsita.exam.utils.exception.OperationException;

import java.util.List;
import java.util.Map;

/**
 * 考试业务层接口
 */
public interface ExamEbi extends BaseEbi<ExamVo>
{

    /**
     * 发起考试
     * @param examVo        考试信息
     * @param markTeachers  阅卷教师
     * @param classroomIds  参加班级
     * @param paperId       试卷id
     */
    public void save(ExamVo examVo, String[] markTeachers,String paperId ,String[] classroomIds) throws OperationException;

    /**
     * 修改考试
     * @param examVo        考试信息
     * @param markTeachers  阅卷教师
     * @param classroomIds  参加班级
     * @param paperId       试卷id
     */
    public void update(ExamVo examVo, String[] markTeachers,String paperId ,String[] classroomIds) throws OperationException;

    /**
     * 查询当前就是所发起的考试
     * @param teacherId
     * @return
     */
    public List<ExamVo> getByCreateTeacher(String teacherId);

    /**
     * 查询当前教师所参与阅卷的试卷
     * @param teacherId
     * @return
     */
    public List<ExamVo> getByMarkTeacher(String teacherId);

    /**
     * 审核通过
     * @param examVo
     * @param teacherVo
     */
    public void setPass(ExamVo examVo, TeacherVo teacherVo) throws Exception;

    /**
     * 审核不通过
     * @param examVo
     * @param teacherVo
     */
    public void setNoPass(ExamVo examVo, TeacherVo teacherVo) throws Exception;

    /**
     * 取消考试
     * @param examVo
     * @param teacherVo
     */
    public void cancel(ExamVo examVo, TeacherVo teacherVo) throws Exception;

    /**
     * 删除考试
     * @param examVo
     * @param teacherVo
     */
    public void deleteCancel(ExamVo examVo, TeacherVo teacherVo) throws Exception;

    /**
     * 更新阅卷教师
     * @param examVo
     * @param markTeachers
     */
    public void updateMarkTeacher(ExamVo examVo, String[] markTeachers) throws Exception;

    /**
     * 获取所有学生的参加的考试
     * @param studentId
     * @return
     */
    public List<ExamVo> getMyExamList(String studentId);

    /**
     * 学生参加考试
     * @param examVo
     * @param studentVo
     * @return
     */
    public Map<String, Object> attendExam(ExamVo examVo, StudentVo studentVo) throws Exception;

    /**
     * 管理员考试列表
     *
     * @param id
     * @param examQueryModel
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<ExamVo> getAllByAdmin(String id, ExamQueryModel examQueryModel, Integer pageNum, Integer pageSize) throws Exception;

    /**
     * 学生考试存档
     * @param login
     * @param studentExamId
     * @param studentExamQuestionList
     */
    public void archive(StudentVo login, String studentExamId, List<StudentExamQuestionVo> studentExamQuestionList) throws Exception;
}
