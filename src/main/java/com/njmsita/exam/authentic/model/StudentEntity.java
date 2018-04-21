package com.njmsita.exam.authentic.model;

import javax.persistence.*;

@Entity
@Table(name = "student", schema = "jf_exam", catalog = "")
public class StudentEntity
{
    private String id;
    private String studentId;
    private String name;
    private String mail;
    private String idCardNo;
    private String telephone;
    private String password;
    private Long lastLoginTime;
    private String schoolId;
    private String classroomId;
    private String lastLoginIp;
    private Long createtime;
    private Long modifytime;
    private String roleId;

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
    @Column(name = "student_id")
    public String getStudentId()
    {
        return studentId;
    }

    public void setStudentId(String studentId)
    {
        this.studentId = studentId;
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
    @Column(name = "school_id")
    public String getSchoolId()
    {
        return schoolId;
    }

    public void setSchoolId(String schoolId)
    {
        this.schoolId = schoolId;
    }

    @Basic
    @Column(name = "classroom_id")
    public String getClassroomId()
    {
        return classroomId;
    }

    public void setClassroomId(String classroomId)
    {
        this.classroomId = classroomId;
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
        result = 31 * result + (studentId != null ? studentId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (idCardNo != null ? idCardNo.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (lastLoginTime != null ? lastLoginTime.hashCode() : 0);
        result = 31 * result + (schoolId != null ? schoolId.hashCode() : 0);
        result = 31 * result + (classroomId != null ? classroomId.hashCode() : 0);
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

        StudentEntity that = (StudentEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;
        if (idCardNo != null ? !idCardNo.equals(that.idCardNo) : that.idCardNo != null) return false;
        if (telephone != null ? !telephone.equals(that.telephone) : that.telephone != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (lastLoginTime != null ? !lastLoginTime.equals(that.lastLoginTime) : that.lastLoginTime != null)
            return false;
        if (schoolId != null ? !schoolId.equals(that.schoolId) : that.schoolId != null) return false;
        if (classroomId != null ? !classroomId.equals(that.classroomId) : that.classroomId != null) return false;
        if (lastLoginIp != null ? !lastLoginIp.equals(that.lastLoginIp) : that.lastLoginIp != null) return false;
        if (createtime != null ? !createtime.equals(that.createtime) : that.createtime != null) return false;
        if (modifytime != null ? !modifytime.equals(that.modifytime) : that.modifytime != null) return false;
        if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null) return false;

        return true;
    }
}
