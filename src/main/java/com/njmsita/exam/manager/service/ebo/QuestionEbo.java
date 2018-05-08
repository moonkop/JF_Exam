package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.QuestionDao;
import com.njmsita.exam.manager.dao.dao.QuestionTypeDao;
import com.njmsita.exam.manager.dao.dao.SubjectDao;
import com.njmsita.exam.manager.dao.dao.TopicDao;
import com.njmsita.exam.manager.model.QuestionTypeVo;
import com.njmsita.exam.manager.model.QuestionVo;
import com.njmsita.exam.manager.model.SubjectVo;
import com.njmsita.exam.manager.model.TopicVo;
import com.njmsita.exam.manager.model.querymodel.QuestionQueryModel;
import com.njmsita.exam.manager.service.ebi.QuestionEbi;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.format.StringUtil;
import com.njmsita.exam.utils.idutil.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 题目业务层实现类
 */
@Service
@Transactional
public class QuestionEbo implements QuestionEbi
{
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private QuestionTypeDao questionTypeDao;
    @Autowired
    private TopicDao topicDao;

    public void save(QuestionVo questionVo) throws OperationException
    {
        infoValidate(questionVo);
        questionVo.setCreateTime(System.currentTimeMillis());
        questionDao.save(questionVo);
    }

    public List<QuestionVo> getAll()
    {
        return questionDao.getAll();
    }

    public QuestionVo get(Serializable uuid)
    {
        return questionDao.get(uuid);
    }

    public List<QuestionVo> getAll(BaseQueryVO qm, Integer pageNum, Integer pageCount)
    {
        return questionDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryVO qm)
    {
        return questionDao.getCount(qm);
    }

    public void update(QuestionVo questionVo) throws OperationException
    {
        infoValidate(questionVo);
        QuestionVo temp=questionDao.get(questionVo.getId());
        temp.setCode(questionVo.getCode());
        temp.setIsPrivate(questionVo.getIsPrivate());
        temp.setOutline(questionVo.getOutline());
        temp.setOption(questionVo.getOption());
        temp.setAnswer(questionVo.getAnswer());

        temp.setTopic(questionVo.getTopic());
        temp.setQuestionType(questionVo.getQuestionType());
        temp.setSubject(questionVo.getSubject());
    }

    public void delete(QuestionVo questionVo)
    {
        questionDao.delete(questionVo);
    }

    public List<QuestionVo> getBySubject(Integer id)
    {
        return questionDao.getBySubject(id);
    }

    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------

    /**
     * 信息校验
     * @param questionVo   前台信息
     */
    private  void infoValidate(QuestionVo questionVo) throws OperationException
    {
        if(questionVo.getSubject()==null){
            throw new OperationException("选择科目不能为空，请不要进行非法操作！");
        }
        if(questionVo.getSubject().getId()==0||questionVo.getSubject().getId()==null){
            throw new OperationException("选择科目不能为空，请不要进行非法操作！");
        }
        if(questionVo.getQuestionType()==null){
            throw new OperationException("选择题型不能为空，请不要进行非法操作！");
        }
        if(questionVo.getQuestionType().getId()==0||questionVo.getQuestionType().getId()==null){
            throw new OperationException("选择题型不能为空，请不要进行非法操作！");
        }
        if(questionVo.getTopic()==null){
            throw new OperationException("选择知识点不能为空，请不要进行非法操作！");
        }
        if(StringUtil.isEmpty(questionVo.getTopic().getId())){
            throw new OperationException("选择知识点不能为空，请不要进行非法操作！");
        }
        SubjectVo subject=subjectDao.get(questionVo.getSubject().getId());
        if (subject==null){
            throw new OperationException("选择科目不存在，请不要进行非法操作！");
        }
        TopicVo topicVo=topicDao.get(questionVo.getTopic().getId());
        if (topicVo==null){
            throw new OperationException("选择知识点不存在，请不要进行非法操作！");
        }
        QuestionTypeVo questionTypeVo =questionTypeDao.get(questionVo.getQuestionType().getId());
        if (questionTypeVo==null){
            throw new OperationException("选择题型不存在，请不要进行非法操作！");
        }
        if(questionVo.getIsPrivate()==null){
            questionVo.setIsPrivate(0);
        }else if(questionVo.getIsPrivate()!=0&&questionVo.getIsPrivate()!=1){
            throw new OperationException("不存在的可见类型，请不要进行非法操作！");
        }
        questionVo.setSubject(subject);
        questionVo.setQuestionType(questionTypeVo);
        questionVo.setTopic(topicVo);
    }

    public List<QuestionVo> getAllByTeacher(QuestionQueryModel questionQueryModel, Integer offset, Integer pageSize, TeacherVo login)
    {
        return questionDao.getAllByTeacher(questionQueryModel,offset,pageSize, login);
    }

    public void updateOrSaveToMe(QuestionVo questionVo, TeacherVo teacherVo) throws OperationException
    {
        QuestionVo temp=questionDao.get(questionVo.getId());
        if(temp.getTeacher().getTeacherId().equals(teacherVo.getTeacherId())){
            this.update(questionVo);
        }else {
            questionVo.setTeacher(teacherVo);
            questionVo.setId(IdUtil.getUUID());
            this.save(questionVo);
        }
    }
}
