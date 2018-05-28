package com.njmsita.exam.manager.model;

import com.njmsita.exam.base.BaseDao;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "schedule", schema = "jf_exam", catalog = "")
public class ScheduleVo
{
    private String id;
    private String jobName;
    private String jobGroup;
    private Integer jobStatus;
    private String cronexpression;
    private String describe;
    private Integer jobType;

    private String targetVoId;

    private Integer nowStatu;
    private Integer affterStatu;
    private BaseDao dao;

    @Basic
    @Column(name = "targetVo_id")
    public String getTargetVoId()
    {
        return targetVoId;
    }

    public void setTargetVoId(String examVo)
    {
        this.targetVoId = examVo;
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

    public BaseDao getDao()
    {
        return dao;
    }

    public void setDao(BaseDao examDao)
    {
        this.dao = examDao;
    }

    @Id
    @Column(name = "id")
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Basic
    @Column(name = "jobName")
    public String getJobName()
    {
        return jobName;
    }

    public void setJobName(String jobName)
    {
        this.jobName = jobName;
    }

    @Basic
    @Column(name = "jobGroup")
    public String getJobGroup()
    {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup)
    {
        this.jobGroup = jobGroup;
    }

    @Basic
    @Column(name = "jobStatus")
    public Integer getJobStatus()
    {
        return jobStatus;
    }

    public void setJobStatus(Integer jobStatus)
    {
        this.jobStatus = jobStatus;
    }

    @Basic
    @Column(name = "cronexpression")
    public String getCronexpression()
    {
        return cronexpression;
    }

    public void setCronexpression(String cronexpression)
    {
        this.cronexpression = cronexpression;
    }

    @Basic
    @Column(name = "describes")
    public String getDescribe()
    {
        return describe;
    }

    public void setDescribe(String describe)
    {
        this.describe = describe;
    }


    @Basic
    @Column(name = "jobType")
    public Integer getJobType()
    {
        return jobType;
    }

    public void setJobType(Integer jobType)
    {
        this.jobType = jobType;
    }

    @Override
    public int hashCode()
    {

        return Objects.hash(id, jobName, jobGroup, jobStatus, cronexpression, describe, jobType);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleVo that = (ScheduleVo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(jobName, that.jobName) &&
                Objects.equals(jobGroup, that.jobGroup) &&
                Objects.equals(jobStatus, that.jobStatus) &&
                Objects.equals(cronexpression, that.cronexpression) &&
                Objects.equals(describe, that.describe) &&
                Objects.equals(jobType, that.jobType);
    }

    @Override
    public String toString()
    {
        return "ScheduleVo{" +
                "id='" + id + '\'' +
                ", jobName='" + jobName + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                ", jobStatus=" + jobStatus +
                ", cronexpression='" + cronexpression + '\'' +
                ", describe='" + describe + '\'' +
                ", jobType=" + jobType +
                ", examVo=" + targetVoId +
                ", nowStatu=" + nowStatu +
                ", affterStatu=" + affterStatu +
                ", examDao=" + dao +
                '}';
    }
}
