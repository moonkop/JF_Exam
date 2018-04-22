package com.njmsita.exam.authentic.dao.dao;

import com.njmsita.exam.authentic.model.StudentVo;
import com.njmsita.exam.base.BaseDao;

import java.util.List;

/**
 * 学生持久层接口
 */
public interface StudentDao extends BaseDao<StudentVo>
{
    /**
     * 根据学校id获取学生
     * @param id    学校id
     * @return      学生集合
     */
    public List<StudentVo> getAllBySchoolId(String id);
}
