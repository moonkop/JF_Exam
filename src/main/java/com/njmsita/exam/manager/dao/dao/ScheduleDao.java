package com.njmsita.exam.manager.dao.dao;

import com.njmsita.exam.base.BaseDao;
import com.njmsita.exam.manager.model.ScheduleVo;
import com.njmsita.exam.manager.model.SubjectVo;

import java.util.List;

/**
 * 定时任务持久层接口
 */
public interface ScheduleDao extends BaseDao<ScheduleVo>
{
    /**
     * 根据考试id获取任务
     * @return
     * @param examId
     */
    public List<ScheduleVo> getByExam(String examId);
}
