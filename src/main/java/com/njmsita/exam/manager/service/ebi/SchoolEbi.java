package com.njmsita.exam.manager.service.ebi;

import com.njmsita.exam.manager.model.SchoolVo;
import com.njmsita.exam.base.BaseEbi;

/**
 * 学校业务层接口
 */
public interface SchoolEbi extends BaseEbi<SchoolVo>
{

    /**
     * 删除学校
     * @param school    需要删除的学校
     */
    public void delete(SchoolVo school);
}
