package com.njmsita.exam.authentic.model;

import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.validate.annotation.IDCardNoValifatorAnnocation;
import com.njmsita.exam.utils.validate.annotation.TelephoneValidatorAnnotation;
import com.njmsita.exam.utils.validate.validategroup.AddGroup;
import com.njmsita.exam.utils.validate.validategroup.EditGroup;
import com.njmsita.exam.utils.validate.validategroup.SelfEditGroup;
import com.njmsita.exam.utils.validate.validategroup.SetPassword;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.security.spec.EncodedKeySpec;

/**
 * 教师实体模型
 */
@Entity
@Table(name = "teacher", schema = "jf_exam")
public class TeacherVo extends UserModel
{
    @NotEmpty(message = "{id.notempty}",groups = {EditGroup.class})
    private String id;

    @NotEmpty(message = "{teacher.id.notempty}",groups = {AddGroup.class, EditGroup.class})
    private String teacherId;

    @NotEmpty(message = "{student.or.teacher.name.notempty}",groups = {AddGroup.class, EditGroup.class})
    private String name;

    @Email(message = "{email.formar.error}",groups = {AddGroup.class,EditGroup.class, SelfEditGroup.class})
    private String mail;

    @IDCardNoValifatorAnnocation(groups = {AddGroup.class,EditGroup.class})
    private String idCardNo;

    @TelephoneValidatorAnnotation(groups = {AddGroup.class,EditGroup.class,SelfEditGroup.class})
    private String telephone;

    @NotEmpty(message = "{password.notempty}",groups = {SetPassword.class})
    private String password;
    private Long lastLoginTime;
    private String lastLoginIp;
    private Long createtime;
    private Long modifytime;

    @NotNull(message = "{gender.notempty}",groups = {AddGroup.class, EditGroup.class})
    private Integer gender;
    //所拥有的角色  n TO  1
    private TroleVo role;

    @Basic
    @Column(name = "role_id")
    public TroleVo getRole()
    {
        return role;
    }

    public void setRole(TroleVo troleVo)
    {
        this.role = troleVo;
        if (troleVo != null)
        {
            super.setUserRole(troleVo.getId());
        }
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
        this.setUuid(id);
    }

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
        super.setRealName(name);
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


    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (teacherId != null ? teacherId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (idCardNo != null ? idCardNo.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
        {
            if (hashCode() == o.hashCode())
            {
                return true;
            }
            else {
                return false;
            }
        }

        TeacherVo teacherVo = (TeacherVo) o;

        if (getId() != null ? !getId().equals(teacherVo.getId()) : teacherVo.getId() != null) return false;
        if (getTeacherId() != null ? !getTeacherId().equals(teacherVo.getTeacherId()) : teacherVo.getTeacherId() != null) return false;
        if (getName() != null ? !getName().equals(teacherVo.getName()) : teacherVo.getName() != null) return false;
        if (getMail() != null ? !getMail().equals(teacherVo.getMail()) : teacherVo.getMail() != null) return false;
        if (getIdCardNo() != null ? !getIdCardNo().equals(teacherVo.getIdCardNo()) : teacherVo.getIdCardNo() != null) return false;
        if (getTelephone() != null ? !getTelephone().equals(teacherVo.getTelephone()) : teacherVo.getTelephone() != null) return false;
        if (getPassword() != null ? !getPassword().equals(teacherVo.getPassword()) : teacherVo.getPassword() != null) return false;
        return true;
    }
    public boolean equalsById(TeacherVo teacherVo)
    {
        if (getId() != null ? !getId().equals(teacherVo.getId()) : teacherVo.getId() != null) return false;
        return true;
    }

    public String toString()
    {
        return "TeacherVo{" +
                "id='" + getId() + '\'' +
                ", teacherId='" + getTeacherId() + '\'' +
                ", name='" + getName() + '\'' +
                ", mail='" + getMail() + '\'' +
                ", idCardNo='" + getIdCardNo() + '\'' +
                ", telephone='" + getTelephone() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", lastLoginTime=" + getLastLoginTime() +
                ", lastLoginIp='" + getLastLoginIp() + '\'' +
                ", createtime=" + getCreatetime() +
                ", modifytime=" + getModifytime() +
                ", gender=" + getGender() +
                ", troleVo=" + getRole() +
                '}';
    }

    @Transient
    public boolean IsAdmin()
    {
        if (this.getRole() != null && this.getRole().getId().equals(SysConsts.ADMIN_ROLE_ID))
        {
            return true;
        }
        return false;
    }

}
