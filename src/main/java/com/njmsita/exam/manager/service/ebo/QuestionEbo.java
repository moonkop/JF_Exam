package com.njmsita.exam.manager.service.ebo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.njmsita.exam.authentic.dao.dao.TeacherDao;
import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.*;
import com.njmsita.exam.manager.model.*;
import com.njmsita.exam.manager.model.querymodel.QuestionQueryModel;
import com.njmsita.exam.manager.service.ebi.QuestionEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.FormatException;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.exception.UnAuthorizedException;
import com.njmsita.exam.utils.format.StringUtil;
import com.njmsita.exam.utils.idutil.IdUtil;
import com.njmsita.exam.utils.json.CustomJsonSerializer;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 题目业务层实现类
 */
@Service
@Transactional
public class QuestionEbo implements QuestionEbi
{
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private QuestionTypeDao questionTypeDao;
    @Autowired
    private TopicDao topicDao;
    @Autowired
    private TeacherDao teacherDao;

    public void save(QuestionVo questionVo) throws OperationException
    {
        infoValidate(questionVo);
        questionVo.setCreateTime(System.currentTimeMillis());
        questionDao.save(questionVo);
    }

    public List<QuestionVo> getAll()
    {
        return questionDao.getAll();
    }

    public QuestionVo get(Serializable uuid)
    {
        return questionDao.get(uuid);
    }

    public List<QuestionVo> getAll(BaseQueryVO qm, Integer pageNum, Integer pageCount)
    {
        return questionDao.getAllByAdmin(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryVO qm)
    {
        return questionDao.getCount(qm);
    }

    public void update(QuestionVo questionVo) throws OperationException
    {
        infoValidate(questionVo);
        QuestionVo temp = questionDao.get(questionVo.getId());
        if (temp == null)
        {
            throw new OperationException("当前选择的题目不存在，请不要进行非法操作！");
        }
        setField(temp, questionVo);
    }

    public void delete(QuestionVo questionVo)
    {
        questionDao.delete(questionVo);
    }

    private void setField(QuestionVo oldQuestion, QuestionVo newQuestion)
    {
        oldQuestion.setCode(newQuestion.getCode());
        oldQuestion.setIsPrivate(newQuestion.getIsPrivate());
        oldQuestion.setOutline(newQuestion.getOutline());
        oldQuestion.setOption(newQuestion.getOption());
        oldQuestion.setAnswer(newQuestion.getAnswer());
        oldQuestion.setTopic(newQuestion.getTopic());
        oldQuestion.setQuestionType(newQuestion.getQuestionType());
        oldQuestion.setSubject(newQuestion.getSubject());
    }

    public List<QuestionVo> getBySubject(Integer id)
    {
        return questionDao.getBySubject(id);
    }

    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------

    /**
     * 信息校验
     *
     * @param questionVo 前台信息
     */
    private void infoValidate(QuestionVo questionVo) throws OperationException
    {
        if (questionVo.getQuestionType() == null)
        {
            throw new OperationException("选择题型不能为空，请不要进行非法操作！");
        }
        if (questionVo.getQuestionType().getId() == 0 || questionVo.getQuestionType().getId() == null)
        {
            throw new OperationException("选择题型不能为空，请不要进行非法操作！");
        }
        if (questionVo.getTopic() == null)
        {
            throw new OperationException("选择知识点不能为空，请不要进行非法操作！");
        }
        if (StringUtil.isEmpty(questionVo.getTopic().getId()))
        {
            throw new OperationException("选择知识点不能为空，请不要进行非法操作！");
        }

        TopicVo topicVo = topicDao.get(questionVo.getTopic().getId());
        if (topicVo == null)
        {
            throw new OperationException("选择知识点不存在，请不要进行非法操作！");
        }
        SubjectVo subject = topicVo.getSubjectVo();
        if (subject == null)
        {
            throw new OperationException("选择科目不存在，请不要进行非法操作！");
        }
        QuestionTypeVo questionTypeVo = questionTypeDao.get(questionVo.getQuestionType().getId());
        if (questionTypeVo == null)
        {
            throw new OperationException("选择题型不存在，请不要进行非法操作！");
        }
        if (questionVo.getIsPrivate() == null)
        {
            questionVo.setIsPrivate(0);
        } else if (questionVo.getIsPrivate() != 0 && questionVo.getIsPrivate() != 1)
        {
            throw new OperationException("不存在的可见类型，请不要进行非法操作！");
        }
        if (StringUtil.isEmpty(questionVo.getOutline()))
        {
            throw new OperationException("题干不能为空，请核对后重试！");
        }
        questionVo.setSubject(subject);
        questionVo.setQuestionType(questionTypeVo);
        questionVo.setTopic(topicVo);

        if (!questionTypeVo.getName().equals(SysConsts.NO_ANSWER_QUESTION_TYPE_NAME))
        {
            String answer = questionVo.getAnswer();
            questionVo.setAnswer(formatAnswer(questionVo.getAnswer(),questionVo.getOptionList().length));
        }

    }

    public List<QuestionVo> getAllByTeacher(QuestionQueryModel questionQueryModel, Integer offset, Integer pageSize, TeacherVo login)
    {
        return questionDao.getAllByTeacher(questionQueryModel, offset, pageSize, login);
    }

    public void update(QuestionVo questionVo, TeacherVo teacherVo) throws OperationException
    {
        infoValidate(questionVo);
        QuestionVo old = questionDao.get(questionVo.getId());
        if (old == null)
        {
            throw new OperationException("当前编辑题目不存在！");
        }
        //如果这道题属于当前用户，或当前用户身份为管理员，则允许编辑
        if (SysConsts.ADMIN_ROLE_ID.equals(teacherVo.getRole().getId()) || old.getTeacher().getId().equals(teacherVo.getId()))
        {
            setField(old, questionVo);
        } else
        {
            throw new OperationException("不允许编辑操作！");
        }
    }

    public void saveAsMine(QuestionVo questionVo, TeacherVo teacherVo) throws OperationException
    {
        infoValidate(questionVo);
        questionVo.setTeacher(teacherDao.get(teacherVo.getId()));
        this.save(questionVo);
    }

    public void delete(QuestionVo questionVo, TeacherVo teacherVo) throws UnAuthorizedException
    {
        teacherVo=teacherDao.get(teacherVo.getId());
        questionVo = questionDao.get(questionVo.getId());
        if (teacherVo.getId().equals(questionVo.getTeacher().getId()) || teacherVo.getRole().getId().equals(SysConsts.ADMIN_ROLE_ID) )
        {
            questionDao.delete(questionVo);
        }else{
        throw new UnAuthorizedException("您不能删除这个题目");
        }
    }

    public QuestionVo updateOrSaveToMe(QuestionVo questionVo, TeacherVo teacherVo) throws OperationException
    {
        infoValidate(questionVo);
        QuestionVo temp = questionDao.get(questionVo.getId());
        if (temp.getTeacher().getTeacherId().equals(teacherVo.getTeacherId()))
        {
            setField(temp, questionVo);
            return temp;
        } else
        {
            questionVo.setTeacher(teacherVo);
            this.save(questionVo);
            return questionVo;
        }
    }

    public void bulkInputBySheet(HSSFSheet sheet, Integer subjectId) throws Exception
    {
        SubjectVo subjectVo = subjectDao.get(subjectId);
        if (subjectVo == null)
        {
            throw new OperationException("不存在当前选择的科目，请勿非法操作！");
        }
        List<QuestionVo> questions = new ArrayList<QuestionVo>();
        for (Row row : sheet)
        {
            int rowNum = row.getRowNum();
            //跳过标题行
            if (rowNum == 0 || rowNum == 1 || rowNum == 2)
                continue;
            try
            {
                //如果题型为空则不录入该行
                if (null != row.getCell(2) && null != row.getCell(2).getStringCellValue() &&
                        !StringUtil.isEmpty(row.getCell(2).getStringCellValue()))
                {

                    //读取表中数据并进行校验和属性设置
                    QuestionVo temp = setQuestion(row, subjectId);

                    //设置初始信息
                    temp.setSubject(subjectVo);
                    temp.setCreateTime(System.currentTimeMillis());

                    questions.add(temp);
                } else
                {
                    break;
                }
            } catch (IllegalStateException e)
            {
                throw new OperationException("行号：" + (rowNum + 1) + "的数据格式错误，请核对后重试");
            }
        }

        questionDao.bulkInput(questions);
    }

    public List<QuestionVo> autoSelectByTopicIdsAndType(String[] topicIds, Integer questionTypeId,
                                                        Integer questionNum, TeacherVo teacherVo) throws Exception
    {
        //数据校验
        QuestionTypeVo questionTypeVo = questionTypeDao.get(questionTypeId);
        if (questionTypeVo == null)
        {
            throw new OperationException("所选择的题型不存在，请不要进行非法操作");
        }
        if (questionNum == null || questionNum == 0)
        {
            throw new OperationException("所抽取的题目数量不能为0，请不要进行非法操作");
        }
        String[] topicIds2 = null;
        if (topicIds != null)
        {
            List<String> correctTopicIds = new ArrayList<>();
            for (String topicId : topicIds)
            {
                if (StringUtil.isEmpty(topicId))
                {
                    continue;
                }
                TopicVo topicVo = topicDao.get(topicId);
                if (topicVo == null)
                {
                    continue;
                }
                correctTopicIds.add(topicId);
            }
            if (correctTopicIds.size() > 0)
            {
                topicIds2 = new String[correctTopicIds.size()];
                correctTopicIds.toArray(topicIds2);
            }
        }

        //得到所有符合条件的题目
        List<QuestionVo> questionList = questionDao.getByTopicIdsAndTypeId(topicIds2, questionTypeId, teacherVo);
        if (questionList.size() <= questionNum)
        {
            return questionList;
        }

        Map<Integer, Set<QuestionVo>> map = new HashMap<>();
        Set<QuestionVo> paper = new HashSet<>();

        //将得到的题目根据使用次数保存在map中   key-->使用次数    value-->当前使用次数的所有题目
        for (QuestionVo questionVo : questionList)
        {
            if (map.containsKey(questionVo.getUseTime()))
            {
                map.get(questionVo.getUseTime()).add(questionVo);
            } else
            {
                Set<QuestionVo> set = new HashSet<QuestionVo>();
                set.add(questionVo);
                map.put(questionVo.getUseTime(), set);
            }
        }

        while (paper.size() < questionNum)
        {
            QuestionVo question = selectQuestion(map);
            if (question != null)
            {
                paper.add(question);
            }
        }

        List<QuestionVo> paperQuestions = new ArrayList<>();
        paperQuestions.addAll(paper);

        return paperQuestions;
    }

    /**
     * 选择题目
     *
     * @param map
     *
     * @return
     */
    private QuestionVo selectQuestion(Map<Integer, Set<QuestionVo>> map)
    {
        //sum为各个使用次数与其所用于题目数量乘积的总和
        int sum = 0;
        //p为各个使用次数的总概率
        int p = 0;
        //使用次数的种类
        List<Integer> useTimes = new ArrayList<>();
        //各个使用次数对应的线性概率
        List<Integer> pn = new ArrayList<>();
        //得到sum和useTimes
        for (Map.Entry<Integer, Set<QuestionVo>> entry : map.entrySet())
        {
            sum += (entry.getKey() + 1) * entry.getValue().size();
            useTimes.add(entry.getKey());
        }
        //得到p和pn
        for (Integer integer : useTimes)
        {
            p += sum / (integer + sum * 0.5);
            pn.add(p);
        }
        Random random = new Random();
        int num = random.nextInt(p) + 1;
        //根据随机数出现的位置找到对应的使用次数，并从其所拥有题目的集合中随机抽取一个
        for (Integer integer : pn)
        {
            if (num <= integer)
            {
                Integer useTime = useTimes.get(pn.indexOf(integer));
                Set<QuestionVo> set = map.get(useTime);
                if (set.size() == 0)
                    continue;
                else
                {
                    List<QuestionVo> list = new ArrayList<>();
                    list.addAll(set);
                    QuestionVo question = list.get(new Random().nextInt(list.size()));
                    set.remove(question);
                    map.put(useTime, set);
                    question = questionDao.get(question.getId());
                    question.setUseTime(question.getUseTime() + 1);
                    return question;
                }
            }
        }
        return null;
    }

    /**
     * 读取并设置
     *
     * @param row
     * @param subjectId
     *
     * @return
     *
     * @throws FormatException
     */
    private QuestionVo setQuestion(Row row, Integer subjectId) throws FormatException, JsonProcessingException
    {
        QuestionVo temp = new QuestionVo();
        int num = row.getRowNum() + 1;
        Integer isPrivate = 0;
        String code = "";
        String outline = "";
        List<String> options = new ArrayList<>();
        String answer = "";
        TopicVo topic = null;
        TeacherVo teacher = null;
        QuestionTypeVo questionTypeVo = null;

        //读取数据
        if (row.getCell(2) != null)
        {
            String questionTypeName = row.getCell(2).getStringCellValue();
            questionTypeVo = questionTypeDao.getByName(questionTypeName.toUpperCase());
            if (questionTypeVo == null)
            {
                throw new FormatException("行号：" + num + "  题型:" + questionTypeName + "的题型不存在，请核对后重试！");
            }
        }
        if (row.getCell(3) != null)
        {
            outline = row.getCell(3).getStringCellValue();
            if (StringUtil.isEmpty(outline))
            {
                throw new FormatException("行号：" + num + "  题干不能为空");
            }
        }
        if (row.getCell(4) != null)
        {
            code = row.getCell(4).getStringCellValue();
        }
        if (row.getCell(5) != null)
        {
            String isPrivateStr = row.getCell(5).getStringCellValue();
            if (!StringUtil.isEmpty(isPrivateStr))
            {
                Integer isPrivateNum = Integer.parseInt(isPrivateStr);
                if (isPrivateNum == 1)
                {
                    isPrivate = 1;
                }
            }
        }
        if (row.getCell(6) != null)
        {
            String topicName = row.getCell(6).getStringCellValue();
            topic = topicDao.getByName(topicName, subjectId);
            if (topic == null)
            {
                List<TopicVo> list = topicDao.getByLikeName(topicName);
                if (list.size() <= 0)
                {
                    throw new FormatException("行号：" + num + "  知识点:" + topicName + "的知识点不存在，请核对后重试！");
                } else if (list.size() > 1)
                {
                    throw new FormatException("行号：" + num + "  知识点:" + topicName + "的知识点不精确，请核对后重试！");
                }
                topic = list.get(0);
            }
        }
        if (row.getCell(7) != null)
        {
            String teacherId = row.getCell(7).getStringCellValue();
            if (teacherId == null || teacherId.trim().equals(""))
            {
                throw new FormatException("行号：" + num + "  出题人不能为空，请核对后重新导入！");
            } else if (teacherId.length() > 32)
            {
                throw new FormatException("行号：" + num + "  出题人职工号格式不正确，请核对后重新导入！");
            }
            teacher = teacherDao.getByTeacherId(teacherId);
            if (teacher == null)
            {
                throw new FormatException("行号：" + num + "  职工号:" + teacherId + "的教师不存在，请核对后重试！");
            }
        }
        if (!questionTypeVo.getName().equals(SysConsts.NO_ANSWER_QUESTION_TYPE_NAME))
        {

            try
            {
                answer = readOptionAndAnswer(row, options);
            } catch (Exception e)
            {
                throw new FormatException("行号"+row.getRowNum()+": "+e.getMessage());
            }
            //options转json
            StringBuilder jsonOptions = new StringBuilder();
            List<String> optionList = new ArrayList<>();
            int i = 0;
            for (String option : options)
            {
                optionList.add(option);
                i++;
            }
            ObjectMapper mapper = CustomJsonSerializer.getDefaultMapper();
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            temp.setOption(mapper.writeValueAsString(optionList));
        }


        //属性设置
        temp.setIsPrivate(isPrivate);
        temp.setCode(code);
        temp.setOutline(outline);
        temp.setAnswer(answer);
        temp.setTopic(topic);
        temp.setTeacher(teacher);
        temp.setQuestionType(questionTypeVo);

        return temp;
    }

    /**
     * 读取选项和答案
     *
     * @param row
     * @param options
     */
    private String readOptionAndAnswer(Row row, List<String> options) throws Exception
    {
        int num = row.getRowNum() + 1;

        for (int i = 9; i < 100; i++)
        {
            if (row.getCell(i) != null && !StringUtil.isEmpty(row.getCell(i).getStringCellValue()))
            {
                String option = row.getCell(i).getStringCellValue();
                options.add(option);
            } else
            {
                break;
            }
        }

        String cellContent = "";
        if (row.getCell(8) != null)
        {
            cellContent = row.getCell(8).getStringCellValue();
        }
        if (StringUtil.isEmpty(cellContent)&&options.size() != 0)
        {
            throw new FormatException("行号：" + num + " 缺少答案");
        }
        if (!StringUtil.isEmpty(cellContent))
        {
            return formatAnswer(cellContent, options.size());
        }
        return "";
    }

    private String formatAnswer(String rawAnswerString, int optionCount) throws OperationException
    {
        List<Integer> answersInt = new ArrayList<>();
        String[] answers = rawAnswerString.split(",");
        Pattern numpattern = Pattern.compile("^[0-9]+$");
        for (String a : answers)
        {
            if (!numpattern.matcher(a).matches())
            {
                throw new OperationException("答案格式不正确，请核对后重新导入！");
            }
            int aint=Integer.valueOf(a);
            if (aint>optionCount)
            {
                throw new OperationException("答案格式不正确，请核对后重新导入！");
            }
            answersInt.add(aint);
        }
        Collections.sort(answersInt);
        String answerString = ""+(answersInt.get(0));
        for (int i = 1; i < answersInt.size(); i++)
        {
            answerString += "," + answersInt.get(i);
        }
        return answerString;
    }
}
