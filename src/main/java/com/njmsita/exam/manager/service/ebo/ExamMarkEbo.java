package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.authentic.dao.dao.StudentDao;
import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.manager.dao.dao.*;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.StudentExamQuestionVo;
import com.njmsita.exam.manager.model.StudentExamVo;
import com.njmsita.exam.manager.service.ebi.ExamManageEbi;
import com.njmsita.exam.manager.service.ebi.ExamMarkEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.format.FormatUtil;
import com.njmsita.exam.utils.format.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExamMarkEbo implements ExamMarkEbi
{

    @Autowired
    private ExamDao examDao;
    @Autowired
    private StudentExamDao studentExamDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StudentExamQuestionDao studentExamQuestionDao;
    @Autowired
    private QuestionTypeDao questionTypeDao;
    @Autowired
    private ExamManageEbi examManageEbi;


    public void saveMarked(List<StudentExamQuestionVo> studentExamQeustionList, String studentExamId) throws Exception
    {
        if (studentExamQeustionList == null || studentExamQeustionList.size() > 0)
        {
            throw new OperationException("所保存的题目不能为空,请不要进行非法操作！");
        }
        if (StringUtil.isEmpty(studentExamId))
        {
            throw new OperationException("所要保存的学生试卷,请不要进行非法操作！");
        }
        StudentExamVo studentExamVo = studentExamDao.get(studentExamId);
        if (null == studentExamVo)
        {
            throw new OperationException("索要保存的学生试卷不存在,请不要进行非法操作！");
        }
        double scores = 0;
        for (StudentExamQuestionVo studentExamQuestionVo : studentExamQeustionList)
        {
            StudentExamQuestionVo temp = studentExamQuestionDao.get(studentExamQuestionVo.getId());
            if (temp == null)
            {
                throw new OperationException("个别题目不存在,请不要进行非法操作！");
            }
            if (!studentExamVo.getId().equals(temp.getStudentExam().getId()))
            {
                throw new OperationException("个别题目属于所提交的学生试卷,请不要进行非法操作！");
            }
            scores += studentExamQuestionVo.getScore();
            temp.setScore(FormatUtil.formatScore(studentExamQuestionVo.getScore()));
            temp.setRemark(studentExamQuestionVo.getRemark());
        }
        studentExamVo.setScore(FormatUtil.formatScore(scores));
    }

    public void submitMarked(ExamVo examVo, TeacherVo login) throws Exception
    {
        if (StringUtil.isEmpty(examVo.getId()))
        {

        }
        examVo = examManageEbi.getExamNotNull(examVo);
        if (!examVo.getMarkTeachers().contains(login))
        {

        }
        List<StudentExamQuestionVo> questionList = studentExamQuestionDao.getByExam(examVo);
        for (StudentExamQuestionVo studentExamQuestionVo : questionList)
        {
            if (studentExamQuestionVo.getQuestionTypeVo().equals(SysConsts.NO_ANSWER_QUESTION_TYPE_NAME))
            {
                if (null == studentExamQuestionVo.getTeacherVo())
                {
                    throw new OperationException("所选考试下有题目尚未批阅，不能提交阅卷！");
                }
            }
        }
        examVo.setExamStatus(SysConsts.EXAM_STATUS_ENDING);
    }
}
