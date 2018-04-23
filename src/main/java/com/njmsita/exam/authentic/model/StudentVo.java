package com.njmsita.exam.authentic.model;

import com.njmsita.exam.manager.model.ClassroomVo;
import com.njmsita.exam.manager.model.SchoolVo;

import javax.persistence.*;

/**
 * 学生实体模型
 */
@Entity
@Table(name = "student", schema = "jf_exam", catalog = "")
public class StudentVo
{
    private String id;
    private String studentId;
    private String name;
    private String mail;
    private String idCardNo;
    private String telephone;
    private String password;
    private Long lastLoginTime;
    private String lastLoginIp;
    private Long createtime;
    private Long modifytime;
    private String studentRes;
    private Integer gender;

    //所属学校
    private SchoolVo school;
    //所拥有的角色
    private TroleVo role;
    //所属班级
    private ClassroomVo classroom;

    @Basic
    @Column(name = "gender")
    public Integer getGender()
    {
        return gender;
    }

    public void setGender(Integer gender)
    {
        this.gender = gender;
    }

    @Basic
    @Column(name="classroom_id")
    public ClassroomVo getClassroom()
    {
        return classroom;
    }

    public void setClassroom(ClassroomVo classroom)
    {
        this.classroom = classroom;
    }

    public String getStudentRes()
    {
        return studentRes;
    }

    public void setStudentRes(String studentRes)
    {
        this.studentRes = studentRes;
    }

    @Basic
    @Column(name = "school_id")
    public SchoolVo getSchool()
    {
        return school;
    }

    public void setSchool(SchoolVo school)
    {
        this.school = school;
    }

    @Basic
    @Column(name = "role_id")
    public TroleVo getRole()
    {
        return role;
    }

    public void setRole(TroleVo role)
    {
        this.role = role;
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
        result = 31 * result + (lastLoginIp != null ? lastLoginIp.hashCode() : 0);
        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
        result = 31 * result + (modifytime != null ? modifytime.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentVo that = (StudentVo) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;
        if (idCardNo != null ? !idCardNo.equals(that.idCardNo) : that.idCardNo != null) return false;
        if (telephone != null ? !telephone.equals(that.telephone) : that.telephone != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (lastLoginTime != null ? !lastLoginTime.equals(that.lastLoginTime) : that.lastLoginTime != null)
            return false;
        if (lastLoginIp != null ? !lastLoginIp.equals(that.lastLoginIp) : that.lastLoginIp != null) return false;
        if (createtime != null ? !createtime.equals(that.createtime) : that.createtime != null) return false;
        if (modifytime != null ? !modifytime.equals(that.modifytime) : that.modifytime != null) return false;

        return true;
    }
}
