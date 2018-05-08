package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.PaperDao;
import com.njmsita.exam.manager.model.PaperVo;
import com.njmsita.exam.manager.model.querymodel.PaperQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

/**
 * 试卷持久层实现类
 */
@Repository
public class PaperDaoImpl extends BaseImpl<PaperVo> implements PaperDao
{

    public void doQbc(DetachedCriteria dc, BaseQueryVO qm)
    {
        PaperQueryModel lqm= (PaperQueryModel) qm;
    }

}
