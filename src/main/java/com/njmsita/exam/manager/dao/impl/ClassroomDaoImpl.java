package com.njmsita.exam.manager.dao.impl;

import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryModel;
import com.njmsita.exam.manager.dao.dao.ClassroomDao;
import com.njmsita.exam.manager.model.ClassroomModel;
import com.njmsita.exam.manager.model.querymodel.ClassroomQueryModel;
import org.hibernate.criterion.DetachedCriteria;

public class ClassroomDaoImpl extends BaseImpl<ClassroomModel> implements ClassroomDao
{
    public void doQbc(DetachedCriteria dc, BaseQueryModel qm)
    {
        ClassroomQueryModel cqm = (ClassroomQueryModel) qm;
    }
}
