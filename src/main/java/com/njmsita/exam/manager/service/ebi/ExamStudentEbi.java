package com.njmsita.exam.manager.service.ebi;

import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.QuestionVo;
import com.njmsita.exam.manager.model.StudentExamQuestionVo;
import com.njmsita.exam.manager.model.StudentExamVo;
import com.njmsita.exam.manager.model.querymodel.StudentExamListQueryModel;
import com.njmsita.exam.utils.exception.UnLoginException;
import org.quartz.SchedulerException;

import java.io.Serializable;
import java.util.List;

public interface ExamStudentEbi
{

    public List<StudentExamVo> getAll();

    public StudentExamVo get(Serializable uuid);


    public List<StudentExamVo> getAll(BaseQueryVO qm, Integer pageNum, Integer pageCount);

    public Integer getCount(BaseQueryVO qm);


    /**
     * 学生考试存档
     *
     * @param login
     * @param studentExamId
     * @param studentExamQuestionList
     */
    public void archive(StudentVo login, String studentExamId, List<StudentExamQuestionVo> studentExamQuestionList) throws Exception;

    /**
     * 提交作答
     *
     * @param studentExamVo
     * @param login
     */
    public void submit(StudentExamVo studentExamVo, StudentVo login) throws Exception;

    /**
     * 查看学生个人考试情况
     *
     * @param examVo
     * @param login
     *
     * @return
     */
    public StudentExamVo getStudentExam(ExamVo examVo, StudentVo login) throws Exception;

    /**
     * 学生进入考试
     * @param studentExamId
     * @param loginStudent
     * @throws Exception
     */
    public StudentExamVo enterExam(String studentExamId, StudentVo loginStudent) throws Exception;

    /**
     * 学生考试开始
     * @param studentExamPo
     * @throws SchedulerException
     */
    public void studentExamStart(StudentExamVo studentExamPo) throws SchedulerException;

    /**
     * 获得学生作答
     * @param studentExamId
     * @param loginStudent
     * @return
     * @throws Exception
     */
    public List<StudentExamQuestionVo> getStudentAnswer(String studentExamId, StudentVo loginStudent) throws Exception;

    /**
     * 获得试题
     * @param studentExamId
     * @param loginStudent
     * @return
     * @throws Exception
     */
    public List<QuestionVo> getPaperQuestion(String studentExamId, StudentVo loginStudent) throws Exception;



    public List<StudentExamVo> getStudentExamList(StudentExamListQueryModel queryModel) throws UnLoginException;


    int getStudentExamCount(StudentExamListQueryModel queryModel);
}
