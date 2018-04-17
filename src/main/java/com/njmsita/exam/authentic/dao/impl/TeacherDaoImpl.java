package com.njmsita.exam.authentic.dao.impl;

import com.njmsita.exam.authentic.dao.dao.TeacherDao;
import com.njmsita.exam.authentic.model.TeacherModel;
import com.njmsita.exam.authentic.model.querymodel.TeacherQueryModel;
import com.njmsita.exam.base.BaseImpl;
import com.njmsita.exam.base.BaseQueryModel;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public class TeacherDaoImpl extends BaseImpl<TeacherModel> implements TeacherDao
{
    public void doQbc(DetachedCriteria dc, BaseQueryModel qm)
    {
        TeacherQueryModel tqm= (TeacherQueryModel) qm;
    }
}
