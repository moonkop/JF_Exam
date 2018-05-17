package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.SubjectDao;
import com.njmsita.exam.manager.dao.dao.TopicDao;
import com.njmsita.exam.manager.model.SubjectVo;
import com.njmsita.exam.manager.model.TopicVo;
import com.njmsita.exam.manager.service.ebi.TopicEbi;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.format.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * 知识点业务层实现类
 */
@Service
@Transactional
public class TopicEbo implements TopicEbi
{
    @Autowired
    private TopicDao topicDao;
    @Autowired
    private SubjectDao subjectDao;

    public void save(TopicVo topicVo) throws OperationException
    {
        infoValidate(topicVo);
        if(topicDao.getByName(topicVo.getName(), topicVo.getSubjectVo().getId())!=null){
            throw new OperationException("当前知识点名称为："+topicVo.getName()+"的知识点已存在，请勿重复操作");
        }
        topicDao.save(topicVo);
    }

    public List<TopicVo> getAll()
    {
        return topicDao.getAll();
    }

    @Override
    public Set<String> getAllChildren(String topicId)
    {
        Stack<TopicVo> topicVoStack = new Stack<>();
        Set<String> children= new HashSet<>();
        topicVoStack.push(topicDao.get(topicId));

        while (topicVoStack.empty() != true)
        {
            TopicVo currentTopic= topicVoStack.pop();
            children.add(currentTopic.getId());
            for (TopicVo topic : currentTopic.getChild())
            {
                topicVoStack.push(topic);
            }
        }
        return children;

    }

    public TopicVo get(Serializable uuid)
    {
        return topicDao.get(uuid);
    }

    public List<TopicVo> getAll(BaseQueryVO qm, Integer pageNum, Integer pageCount)
    {
        return topicDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryVO qm)
    {
        return topicDao.getCount(qm);
    }

    public void update(TopicVo topicVo) throws OperationException
    {
        infoValidate(topicVo);
        TopicVo temp=topicDao.getByName(topicVo.getName(), topicVo.getSubjectVo().getId());
        if(temp!=null&&temp.getId()!=topicVo.getId()){
            throw new OperationException("当前知识点名称为："+topicVo.getName()+"的知识点已存在，请勿重复操作");
        }
        temp.setName(topicVo.getName());
    }

    public void delete(TopicVo topicVo)
    {
        topicDao.delete(topicVo);
    }

    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------

    /**
     * 信息校验
     * @param topicVo   前台信息
     */
    private  void infoValidate(TopicVo topicVo) throws OperationException
    {
        if(topicVo.getSubjectVo()==null){
            throw new OperationException("选择科目不能为空，请不要进行非法操作！");
        }
        if(topicVo.getSubjectVo().getId()==0||topicVo.getSubjectVo().getId()==null){
            throw new OperationException("选择科目不能为空，请不要进行非法操作！");
        }
        TopicVo parent=null;
        if(topicVo.getParent()!=null&&!StringUtil.isEmpty(topicVo.getParent().getId())){
            parent=topicDao.get(topicVo.getParent().getId());
            if (parent==null){
                throw new OperationException("选择父知识点不存在，请不要进行非法操作！");
            }
        }else {
            parent=new TopicVo();
            parent.setId("-");
        }
        topicVo.setParent(parent);
        SubjectVo subject=subjectDao.get(topicVo.getSubjectVo().getId());
        if (subject==null){
            throw new OperationException("选择科目不存在，请不要进行非法操作！");
        }
        topicVo.setName(topicVo.getName().toUpperCase());
        topicVo.setSubjectVo(subject);
    }

    public List<TopicVo> getBySubject(Integer subjectId, String parent) throws OperationException
    {
        if(subjectId==0||subjectId==null){
            throw new OperationException("知识点不能为空，请不要进行非法操作！");
        }
        return topicDao.getBySubject(subjectId,parent);
    }

    //todo 根据学科查知识点树
    public List<TopicVo> getTopicBySubject(SubjectVo subjectVo)
    {
        return null;
    }
}
