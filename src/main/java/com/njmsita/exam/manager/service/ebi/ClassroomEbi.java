package com.njmsita.exam.manager.service.ebi;

import com.njmsita.exam.base.BaseEbi;
import com.njmsita.exam.manager.model.ClassroomVo;
import com.njmsita.exam.manager.model.SchoolVo;
import com.njmsita.exam.utils.exception.OperationException;

/**
 * 业务层接口
 */
public interface ClassroomEbi extends BaseEbi<ClassroomVo>
{

    /**
     * 删除班级
     * @param classroomVo   需要删除的班级
     */
    public void delete(ClassroomVo classroomVo) throws OperationException;
}
