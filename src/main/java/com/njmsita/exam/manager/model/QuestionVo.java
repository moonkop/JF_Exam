package com.njmsita.exam.manager.model;

import javax.persistence.*;

@Entity
@Table(name = "question", schema = "jf_exam", catalog = "")
public class QuestionVo
{
    private String id;
    private Byte isPrivate;
    private Long createTime;
    private String code;
    private String outline;
    private String option;
    private Byte answer;
    private String topicId;
    private String createTeacherId;
    private byte questionTypeId;
    private byte subjectId;

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
    public Byte getIsPrivate()
    {
        return isPrivate;
    }

    public void setIsPrivate(Byte isPrivate)
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
    public Byte getAnswer()
    {
        return answer;
    }

    public void setAnswer(Byte answer)
    {
        this.answer = answer;
    }

    @Basic
    @Column(name = "topic_id")
    public String getTopicId()
    {
        return topicId;
    }

    public void setTopicId(String topicId)
    {
        this.topicId = topicId;
    }

    @Basic
    @Column(name = "create_teacher_id")
    public String getCreateTeacherId()
    {
        return createTeacherId;
    }

    public void setCreateTeacherId(String createTeacherId)
    {
        this.createTeacherId = createTeacherId;
    }

    @Basic
    @Column(name = "question_type_id")
    public byte getQuestionTypeId()
    {
        return questionTypeId;
    }

    public void setQuestionTypeId(byte questionTypeId)
    {
        this.questionTypeId = questionTypeId;
    }

    @Basic
    @Column(name = "subject_id")
    public byte getSubjectId()
    {
        return subjectId;
    }

    public void setSubjectId(byte subjectId)
    {
        this.subjectId = subjectId;
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (isPrivate != null ? isPrivate.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (outline != null ? outline.hashCode() : 0);
        result = 31 * result + (option != null ? option.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (topicId != null ? topicId.hashCode() : 0);
        result = 31 * result + (createTeacherId != null ? createTeacherId.hashCode() : 0);
        result = 31 * result + (int) questionTypeId;
        result = 31 * result + (int) subjectId;
        return result;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionVo that = (QuestionVo) o;

        if (questionTypeId != that.questionTypeId) return false;
        if (subjectId != that.subjectId) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (isPrivate != null ? !isPrivate.equals(that.isPrivate) : that.isPrivate != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (outline != null ? !outline.equals(that.outline) : that.outline != null) return false;
        if (option != null ? !option.equals(that.option) : that.option != null) return false;
        if (answer != null ? !answer.equals(that.answer) : that.answer != null) return false;
        if (topicId != null ? !topicId.equals(that.topicId) : that.topicId != null) return false;
        if (createTeacherId != null ? !createTeacherId.equals(that.createTeacherId) : that.createTeacherId != null)
            return false;

        return true;
    }
}
