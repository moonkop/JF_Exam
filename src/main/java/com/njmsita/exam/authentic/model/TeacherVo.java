package com.njmsita.exam.authentic.model;

import javax.persistence.*;

/**
 * 教师实体模型
 */
@Entity
@Table(name = "teacher", schema = "jf_exam")
public class TeacherVo
{
    private String id;
    private String teacherId;
    private String name;
    private String mail;
    private String idCardNo;
    private String telephone;
    private String password;
    private Long lastLoginTime;
    private String lastLoginIp;
    private Long createtime;
    private Long modifytime;
    private String teacherRes;
    private String roleId;
    //所拥有的角色  n TO  1
    private TroleVo troleVo;


    public TroleVo getTroleVo()
    {
        return troleVo;
    }

    public void setTroleVo(TroleVo troleVo)
    {
        this.troleVo = troleVo;
    }

    public String getTeacherRes()
    {
        return teacherRes;
    }

    public void setTeacherRes(String teacherRes)
    {
        this.teacherRes = teacherRes;
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
    @Column(name = "teacher_id")
    public String getTeacherId()
    {
        return teacherId;
    }

    public void setTeacherId(String teacherId)
    {
        this.teacherId = teacherId;
    }

    @Basic
    @Column(name = "name")
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Basic
    @Column(name = "mail")
    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    @Basic
    @Column(name = "id_card_no")
    public String getIdCardNo()
    {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo)
    {
        this.idCardNo = idCardNo;
    }

    @Basic
    @Column(name = "telephone")
    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "password")
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Basic
    @Column(name = "last_login_time")
    public Long getLastLoginTime()
    {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime)
    {
        this.lastLoginTime = lastLoginTime;
    }

    @Basic
    @Column(name = "last_login_ip")
    public String getLastLoginIp()
    {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp)
    {
        this.lastLoginIp = lastLoginIp;
    }

    @Basic
    @Column(name = "createtime")
    public Long getCreatetime()
    {
        return createtime;
    }

    public void setCreatetime(Long createtime)
    {
        this.createtime = createtime;
    }

    @Basic
    @Column(name = "modifytime")
    public Long getModifytime()
    {
        return modifytime;
    }

    public void setModifytime(Long modifytime)
    {
        this.modifytime = modifytime;
    }

    @Basic
    @Column(name = "role_id")
    public String getRoleId()
    {
        return roleId;
    }

    public void setRoleId(String roleId)
    {
        this.roleId = roleId;
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (teacherId != null ? teacherId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (idCardNo != null ? idCardNo.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (lastLoginTime != null ? lastLoginTime.hashCode() : 0);
        result = 31 * result + (lastLoginIp != null ? lastLoginIp.hashCode() : 0);
        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
        result = 31 * result + (modifytime != null ? modifytime.hashCode() : 0);
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeacherVo teacherVo = (TeacherVo) o;

        if (id != null ? !id.equals(teacherVo.id) : teacherVo.id != null) return false;
        if (teacherId != null ? !teacherId.equals(teacherVo.teacherId) : teacherVo.teacherId != null) return false;
        if (name != null ? !name.equals(teacherVo.name) : teacherVo.name != null) return false;
        if (mail != null ? !mail.equals(teacherVo.mail) : teacherVo.mail != null) return false;
        if (idCardNo != null ? !idCardNo.equals(teacherVo.idCardNo) : teacherVo.idCardNo != null) return false;
        if (telephone != null ? !telephone.equals(teacherVo.telephone) : teacherVo.telephone != null) return false;
        if (password != null ? !password.equals(teacherVo.password) : teacherVo.password != null) return false;
        if (lastLoginTime != null ? !lastLoginTime.equals(teacherVo.lastLoginTime) : teacherVo.lastLoginTime != null)
            return false;
        if (lastLoginIp != null ? !lastLoginIp.equals(teacherVo.lastLoginIp) : teacherVo.lastLoginIp != null)
            return false;
        if (createtime != null ? !createtime.equals(teacherVo.createtime) : teacherVo.createtime != null) return false;
        if (modifytime != null ? !modifytime.equals(teacherVo.modifytime) : teacherVo.modifytime != null) return false;
        if (roleId != null ? !roleId.equals(teacherVo.roleId) : teacherVo.roleId != null) return false;

        return true;
    }
}
