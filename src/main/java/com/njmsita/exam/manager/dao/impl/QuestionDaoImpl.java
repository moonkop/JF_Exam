package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.QuestionDao;
import com.njmsita.exam.manager.model.QuestionVo;
import com.njmsita.exam.manager.model.querymodel.QuestionQueryModel;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.format.StringUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 题目持久层实现类
 */
@Repository
public class QuestionDaoImpl extends BaseImpl<QuestionVo> implements QuestionDao
{

    public DetachedCriteria doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
        QuestionQueryModel qqm= (QuestionQueryModel) qm;
        if(null!=qqm.getSubject()&&qqm.getSubject().getId()!=0){
            dc.createAlias("subject","s1");
            dc.add(Restrictions.eq("s1.id",qqm.getSubject().getId()));
        }
        if(null!=qqm.getTopicIds()&&qqm.getTopicIds().size()!=0){
            dc.createAlias("topic","to1");
            dc.add(Restrictions.in("to1.id",qqm.getTopicIds()));
        }
        if(null!=qqm.getTeacher()&&!StringUtil.isEmpty(qqm.getTeacher().getId())){
            dc.createAlias("teacher","te1");
            dc.add(Restrictions.eq("te1.id", qqm.getTeacher().getId()));
        }
        if(null!=qqm.getQuestionType()&&qqm.getQuestionType().getId()!=0){
            dc.createAlias("questionType","que1");
            dc.add(Restrictions.eq("que1.id", qqm.getQuestionType().getId()));
        }
        if(null!=qqm.getIsPrivate()&&(qqm.getIsPrivate()==0||qqm.getIsPrivate()==1)){
            dc.add(Restrictions.eq("isPrivate", qqm.getIsPrivate()));
        }
        return dc;

    }

    public DetachedCriteria doQbc1(DetachedCriteria dc, BaseQueryVO qm)
    {
        QuestionQueryModel qqm= (QuestionQueryModel) qm;
        if(null!=qqm.getSubject()&&qqm.getSubject().getId()!=null&&qqm.getSubject().getId()!=0){
            dc.createAlias("subject","s1");
            dc.add(Restrictions.eq("s1.id",qqm.getSubject().getId()));
        }
        if(null!=qqm.getTopicIds()&&qqm.getTopicIds().size()!=0){
            dc.createAlias("topic","to1");
            dc.add(Restrictions.in("to1.id",qqm.getTopicIds()));
        }
        if(null!=qqm.getQuestionType()&&qqm.getQuestionType().getId()!=null&&qqm.getQuestionType().getId()!=0){
            dc.createAlias("questionType","que1");
            dc.add(Restrictions.eq("que1.id", qqm.getQuestionType().getId()));
        }
        if(null!=qqm.getTeacher()&&!StringUtil.isEmpty(qqm.getTeacher().getId())){
            dc.createAlias("teacher","te1");
            dc.add(Restrictions.eq("te1.id", qqm.getTeacher().getId()));
        }
        return dc;
    }
    public void doQbcByPublic(DetachedCriteria dc)
    {
        dc.add(Restrictions.eq("isPrivate", 0));

    }


    public List<QuestionVo> getAllByTeacher(QuestionQueryModel qqm, Integer offset, Integer pageSize,TeacherVo login)
    {
        DetachedCriteria dc=DetachedCriteria.forClass(QuestionVo.class);
        if(qqm.getShowMe()){
            List<QuestionVo> list1= (List<QuestionVo>) this.getHibernateTemplate().findByCriteria(doQbc1(dc,qqm),offset,pageSize);
            return list1;
        }else {
            if(qqm.getTeacher()!=null){

                //查出指定科目下指定知识点的指定题型指定教师的题目
                doQbcByPublic(doQbc1(dc,qqm));
                List<QuestionVo> list1= (List<QuestionVo>) this.getHibernateTemplate().findByCriteria(dc,offset,pageSize);
                return list1;
            }else {
                //查出指定科目下指定知识点的指定题型公开的题目
                doQbc1(dc,qqm);
                doQbcByPublic(dc);
                List<QuestionVo> list= (List<QuestionVo>) this.getHibernateTemplate().findByCriteria(dc,offset,pageSize);

                //查出指定科目下指定知识点的指定题型并且属于当前教师私有的题目
                DetachedCriteria dc1=DetachedCriteria.forClass(QuestionVo.class);
                dc1.createAlias("teacher","te2");
                dc1.add(Restrictions.eq("te2.id",login.getId()));
                dc1.add(Restrictions.eq("isPrivate", 1));
                List<QuestionVo> listLoginPrivate= (List<QuestionVo>) this.getHibernateTemplate().findByCriteria(dc1,offset,pageSize);

                //两个集合合并
                list.addAll(listLoginPrivate);
                return list;
            }
        }
    }

    public void bulkInput(List<QuestionVo> questions)
    {
        int i=0;
        for (QuestionVo question : questions)
        {
            this.getHibernateTemplate().save(question);
            i++;
        }
    }

    public List<QuestionVo> getAllByAdmin(BaseQueryVO qm, Integer offset, Integer pageSize)
    {
        DetachedCriteria dc=DetachedCriteria.forClass(QuestionVo.class);
        doQbc(dc, qm);
        return (List<QuestionVo>) this.getHibernateTemplate().findByCriteria(dc,offset,pageSize);
    }

    public List<QuestionVo> getBySubject(Integer id)
    {
        String hql="from QuestionVo where subject.id=?";
        return (List<QuestionVo>) this.getHibernateTemplate().find(hql,id);
    }

    public List<QuestionVo> getByQuestionType(Integer id)
    {
        String hql="from QuestionVo where questionType.id=?";
        return (List<QuestionVo>) this.getHibernateTemplate().find(hql,id);
    }

    public DetachedCriteria doQbc2(DetachedCriteria dc, String[] topicIds, Integer questionTypeId)
    {
        if(null!=topicIds){
            dc.createAlias("topic","to2");
            dc.add(Restrictions.in("to2.id",topicIds));
        }
            dc.createAlias("questionType","que2");
            dc.add(Restrictions.eq("que2.id", questionTypeId));
        return dc;
    }

    public List<QuestionVo> getByTopicIdsAndTypeId(String[] topicIds, Integer questionTypeId, TeacherVo login)
    {
        DetachedCriteria dc=DetachedCriteria.forClass(QuestionVo.class);
        //判断出卷教师是否是管理员
        if(login.getRole().getId().equals(SysConsts.ADMIN_ROLE_ID)){
            //查询指定知识点  指定题型的所有题目
            doQbcByPublic(doQbc2(dc,topicIds,questionTypeId));
            List<QuestionVo> list1= (List<QuestionVo>) this.getHibernateTemplate().findByCriteria(dc);
            return list1;
        }else {
            //查出指定科目下指定知识点的指定题型公开的题目
            doQbc2(dc,topicIds,questionTypeId);
            doQbcByPublic(dc);
            List<QuestionVo> list= (List<QuestionVo>) this.getHibernateTemplate().findByCriteria(dc);

            //查出指定科目下指定知识点的指定题型并且属于当前教师私有的题目
            DetachedCriteria dc1=DetachedCriteria.forClass(QuestionVo.class);
            dc1.createAlias("teacher","te3");
            dc1.add(Restrictions.eq("te3.id",login.getId()));
            dc1.add(Restrictions.eq("isPrivate", 1));
            List<QuestionVo> listLoginPrivate= (List<QuestionVo>) this.getHibernateTemplate().findByCriteria(dc1);

            list.addAll(listLoginPrivate);
            return list;
        }
    }
}
