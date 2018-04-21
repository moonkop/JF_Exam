package com.njmsita.exam.authentic.service.ebi;

import com.njmsita.exam.authentic.model.SchoolVo;
import com.njmsita.exam.authentic.model.TroleVo;
import com.njmsita.exam.base.BaseEbi;

/**
 * 学校业务层接口
 */
public interface SchoolEbi extends BaseEbi<SchoolVo>
{
    public void save1(SchoolVo school);
}
