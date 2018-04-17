package com.njmsita.exam.manager.model;

import com.njmsita.exam.authentic.model.TeacherModel;
import com.njmsita.exam.utils.format.FormatUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目实体模型
 */
public class QuestionModel
{
    public static final Integer QUESTION_PRIVSTE_IS=0;
    public static final Integer QUESTION_PRIVSTE_NO=1;
    public static final String QUESTION_PRIVSTE_IS_VIEW="共享";
    public static final String QUESTION_PRIVSTE_NO_VIEW="私有";

    private Map<Integer,String> privateMap = new HashMap<>();
    {
        privateMap.put(QUESTION_PRIVSTE_IS,QUESTION_PRIVSTE_IS_VIEW);
        privateMap.put(QUESTION_PRIVSTE_NO,QUESTION_PRIVSTE_NO_VIEW);
    }
    private String id;
    private String code;
    private String outline;
    private String options;
    //TODO 有问题
    private Integer answer;

    private Integer is_private;
    private Long create_time;
    //视图值
    private String is_privateView;
    private String create_timeView;

    //所属知识点
    private TopicModel topic_id;
    //出题教师
    private TeacherModel create_teacher_id;
    //所属题目类型
    private QuestionTypeModel question_type_id;
    //所属科目
    private SubjectModel subject_id;

    public Integer getIs_private()
    {
        return is_private;
    }

    public void setIs_private(Integer is_private)
    {
        this.is_private = is_private;
        this.is_privateView=privateMap.get(is_private);
    }

    public Long getCreate_time()
    {
        return create_time;
    }

    public void setCreate_time(Long create_time)
    {
        this.create_time = create_time;
        this.create_timeView= FormatUtil.formatDateTime(create_time);
    }

    public String getIs_privateView()
    {
        return is_privateView;
    }

    public String getCreate_timeView()
    {
        return create_timeView;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getOutline()
    {
        return outline;
    }

    public void setOutline(String outline)
    {
        this.outline = outline;
    }

    public String getOptions()
    {
        return options;
    }

    public void setOptions(String options)
    {
        this.options = options;
    }

    public TopicModel getTopic_id()
    {
        return topic_id;
    }

    public void setTopic_id(TopicModel topic_id)
    {
        this.topic_id = topic_id;
    }

    public TeacherModel getCreate_teacher_id()
    {
        return create_teacher_id;
    }

    public void setCreate_teacher_id(TeacherModel create_teacher_id)
    {
        this.create_teacher_id = create_teacher_id;
    }

    public QuestionTypeModel getQuestion_type_id()
    {
        return question_type_id;
    }

    public void setQuestion_type_id(QuestionTypeModel question_type_id)
    {
        this.question_type_id = question_type_id;
    }

    public SubjectModel getSubject_id()
    {
        return subject_id;
    }

    public void setSubject_id(SubjectModel subject_id)
    {
        this.subject_id = subject_id;
    }
}
