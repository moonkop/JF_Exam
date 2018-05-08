package com.njmsita.exam.manager.model;

import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.utils.validate.validategroup.AddGroup;
import com.njmsita.exam.utils.validate.validategroup.EditGroup;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "question", schema = "jf_exam", catalog = "")
/**
 * 题目实体模型
 */
public class QuestionVo
{
    @NotEmpty(message = "{id.notempty}",groups = {EditGroup.class})
    private String id;
    private Integer isPrivate;
    private Long createTime;
    private String code;
    @NotEmpty(message = "{outline.notempty}",groups = {AddGroup.class,EditGroup.class})
    private String outline;
    private String option;
    private String answer;

    //所属知识点  n TO 1
    private TopicVo topic;
    //出题人   n TO 1
    private TeacherVo teacher;
    //题型    n TO 1
    private QuestionTypeVo questionType;
    //所属科目  n TO 1
    private SubjectVo subject;

    @Basic
    @Column(name = "topic_id")
    public TopicVo getTopic()
    {
        return topic;
    }

    public void setTopic(TopicVo topic)
    {
        this.topic = topic;
    }

    @Basic
    @Column(name = "create_teacher_id")
    public TeacherVo getTeacher()
    {
        return teacher;
    }

    public void setTeacher(TeacherVo teacher)
    {
        this.teacher = teacher;
    }

    @Basic
    @Column(name = "question_type_id")
    public QuestionTypeVo getQuestionType()
    {
        return questionType;
    }

    public void setQuestionType(QuestionTypeVo questionType)
    {
        this.questionType = questionType;
    }

    @Basic
    @Column(name = "subject_id")
    public SubjectVo getSubject()
    {
        return subject;
    }

    public void setSubject(SubjectVo subject)
    {
        this.subject = subject;
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
    @Column(name = "is_private")
    public Integer getIsPrivate()
    {
        return isPrivate;
    }

    public void setIsPrivate(Integer isPrivate)
    {
        this.isPrivate = isPrivate;
    }

    @Basic
    @Column(name = "create_time")
    public Long getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Long createTime)
    {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "code")
    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    @Basic
    @Column(name = "outline")
    public String getOutline()
    {
        return outline;
    }

    public void setOutline(String outline)
    {
        this.outline = outline;
    }

    @Basic
    @Column(name = "option")
    public String getOption()
    {
        return option;
    }

    public void setOption(String option)
    {
        this.option = option;
    }

    @Basic
    @Column(name = "answer")
    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (isPrivate != null ? isPrivate.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (outline != null ? outline.hashCode() : 0);
        result = 31 * result + (option != null ? option.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        return result;
    }

    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionVo that = (QuestionVo) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (isPrivate != null ? !isPrivate.equals(that.isPrivate) : that.isPrivate != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (outline != null ? !outline.equals(that.outline) : that.outline != null) return false;
        if (option != null ? !option.equals(that.option) : that.option != null) return false;
        if (answer != null ? !answer.equals(that.answer) : that.answer != null) return false;

        return true;
    }

    public String toString()
    {
        return "QuestionVo{" +
                "id='" + id + '\'' +
                ", isPrivate=" + isPrivate +
                ", createTime=" + createTime +
                ", code='" + code + '\'' +
                ", outline='" + outline + '\'' +
                ", option='" + option + '\'' +
                ", answer=" + answer +
                ", topic=" + topic +
                ", teacher=" + teacher +
                ", questionType=" + questionType +
                ", subject=" + subject +
                '}';
    }
}
