package com.njmsita.exam.utils.timertask;

import com.njmsita.exam.manager.dao.dao.ExamDao;
import com.njmsita.exam.manager.service.ebi.ExamEbi;

public class ScheduleJob
{
    /** 任务id */
    private String jobId;
    /** 任务名称 */
    private String jobName;
    /** 任务分组 */
    private String jobGroup;
    /** 任务状态 0禁用 1启用 2删除*/
    private String jobStatus;
    /** 任务运行时间表达式 */
    private String cronExpression;
    /** 任务描述 */
    private String desc;
    private Integer nowStatu;
    private Integer affterStatu;
    private ExamEbi examEbi;

    public ExamEbi getExamEbi()
    {
        return examEbi;
    }

    public void setExamEbi(ExamEbi examEbi)
    {
        this.examEbi = examEbi;
    }

    public Integer getNowStatu()
    {
        return nowStatu;
    }

    public void setNowStatu(Integer nowStatu)
    {
        this.nowStatu = nowStatu;
    }

    public Integer getAffterStatu()
    {
        return affterStatu;
    }

    public void setAffterStatu(Integer affterStatu)
    {
        this.affterStatu = affterStatu;
    }

    public String getJobId()
    {
        return jobId;
    }

    public void setJobId(String jobId)
    {
        this.jobId = jobId;
    }

    public String getJobName()
    {
        return jobName;
    }

    public void setJobName(String jobName)
    {
        this.jobName = jobName;
    }

    public String getJobGroup()
    {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup)
    {
        this.jobGroup = jobGroup;
    }

    public String getJobStatus()
    {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus)
    {
        this.jobStatus = jobStatus;
    }

    public String getCronExpression()
    {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression)
    {
        this.cronExpression = cronExpression;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }
}
