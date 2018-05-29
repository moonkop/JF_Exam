package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.PaperDao;
import com.njmsita.exam.manager.model.PaperVo;
import com.njmsita.exam.manager.model.querymodel.PaperQueryModel;
import com.njmsita.exam.utils.format.StringUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * 试卷持久层实现类
 */
@Repository
public class PaperDaoImpl extends BaseImpl<PaperVo> implements PaperDao
{

    public DetachedCriteria doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
        PaperQueryModel pqm= (PaperQueryModel) qm;
        if(null!=pqm.getSubject()&&null!=pqm.getSubject().getId()&&pqm.getSubject().getId()!=0){
            dc.createAlias("subject","sb");
            dc.add(Restrictions.eq("sb.id",pqm.getSubject().getId()));
        }
        if(null!=pqm.getTeacher()&&null!=pqm.getTeacher().getId()&& StringUtil.isEmpty(pqm.getTeacher().getId())){
            dc.createAlias("teacher","te1");
            dc.add(Restrictions.eq("te1.id",pqm.getTeacher().getId()));
        }
        if(StringUtil.isEmpty(pqm.getTitle())){
            dc.add(Restrictions.like("title","%"+pqm.getTitle()+"%"));
        }
        return dc;

    }

}
