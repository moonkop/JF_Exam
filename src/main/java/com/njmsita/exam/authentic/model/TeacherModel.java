package com.njmsita.exam.authentic.model;

import com.njmsita.exam.utils.format.FormatUtil;

/**
 * 教师实体模型
 */
public class TeacherModel
{
    private String id;
    private String teacher_id;
    private String name;
    private String mail;
    private String id_card_no;
    private String telephone;
    private String password;
    private String last_login_ip;

    private Long last_login_time;
    private Long createtime;
    private Long modifytime;

    //时间视图值，用来显示用的
    private String last_login_timeView;
    private String createtimeView;
    private String modifytimeView;

    //所拥有的角色 n TO 1
    private  TroleModel role;

    public Long getLast_login_time()
    {
        return last_login_time;
    }

    public void setLast_login_time(Long last_login_time)
    {
        this.last_login_time = last_login_time;
        this.last_login_timeView= FormatUtil.formatDateTime(last_login_time);
    }

    public Long getCreatetime()
    {
        return createtime;
    }

    public void setCreatetime(Long createtime)
    {
        this.createtime = createtime;
        this.createtimeView=FormatUtil.formatDateTime(createtime);
    }

    public Long getModifytime()
    {
        return modifytime;
    }

    public void setModifytime(Long modifytime)
    {
        this.modifytime = modifytime;
        this.modifytimeView=FormatUtil.formatDateTime(modifytime);
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getTeacher_id()
    {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id)
    {
        this.teacher_id = teacher_id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public String getId_card_no()
    {
        return id_card_no;
    }

    public void setId_card_no(String id_card_no)
    {
        this.id_card_no = id_card_no;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getLast_login_ip()
    {
        return last_login_ip;
    }

    public void setLast_login_ip(String last_login_ip)
    {
        this.last_login_ip = last_login_ip;
    }

    public TroleModel getRole()
    {
        return role;
    }

    public void setRole(TroleModel role)
    {
        this.role = role;
    }

    public String getLast_login_timeView()
    {
        return last_login_timeView;
    }

    public String getCreatetimeView()
    {
        return createtimeView;
    }

    public String getModifytimeView()
    {
        return modifytimeView;
    }
}
