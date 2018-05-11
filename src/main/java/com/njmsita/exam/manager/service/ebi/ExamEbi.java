package com.njmsita.exam.manager.service.ebi;

import com.njmsita.exam.base.BaseEbi;
import com.njmsita.exam.manager.model.ExamVo;

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
    public void save(ExamVo examVo, String[] markTeachers,String paperId ,String[] classroomIds);

    /**
     * 修改考试
     * @param examVo        考试信息
     * @param markTeachers  阅卷教师
     * @param classroomIds  参加班级
     * @param paperId       试卷id
     */
    public void update(ExamVo examVo, String[] markTeachers,String paperId ,String[] classroomIds);
}
