package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.authentic.dao.dao.TeacherDao;
import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.QuestionDao;
import com.njmsita.exam.manager.dao.dao.QuestionTypeDao;
import com.njmsita.exam.manager.dao.dao.SubjectDao;
import com.njmsita.exam.manager.dao.dao.TopicDao;
import com.njmsita.exam.manager.model.QuestionTypeVo;
import com.njmsita.exam.manager.model.QuestionVo;
import com.njmsita.exam.manager.model.SubjectVo;
import com.njmsita.exam.manager.model.TopicVo;
import com.njmsita.exam.manager.model.querymodel.QuestionQueryModel;
import com.njmsita.exam.manager.service.ebi.QuestionEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.FormatException;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.format.StringUtil;
import com.njmsita.exam.utils.idutil.IdUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
        QuestionVo temp=questionDao.get(questionVo.getId());
        temp.setCode(questionVo.getCode());
        temp.setIsPrivate(questionVo.getIsPrivate());
        temp.setOutline(questionVo.getOutline());
        temp.setOption(questionVo.getOption());
        temp.setAnswer(questionVo.getAnswer());

        temp.setTopic(questionVo.getTopic());
        temp.setQuestionType(questionVo.getQuestionType());
        temp.setSubject(questionVo.getSubject());
    }

    public void delete(QuestionVo questionVo)
    {
        questionDao.delete(questionVo);
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
     * @param questionVo   前台信息
     */
    private  void infoValidate(QuestionVo questionVo) throws OperationException
    {
        if(questionVo.getSubject()==null){
            throw new OperationException("选择科目不能为空，请不要进行非法操作！");
        }
        if(questionVo.getSubject().getId()==0||questionVo.getSubject().getId()==null){
            throw new OperationException("选择科目不能为空，请不要进行非法操作！");
        }
        if(questionVo.getQuestionType()==null){
            throw new OperationException("选择题型不能为空，请不要进行非法操作！");
        }
        if(questionVo.getQuestionType().getId()==0||questionVo.getQuestionType().getId()==null){
            throw new OperationException("选择题型不能为空，请不要进行非法操作！");
        }
        if(questionVo.getTopic()==null){
            throw new OperationException("选择知识点不能为空，请不要进行非法操作！");
        }
        if(StringUtil.isEmpty(questionVo.getTopic().getId())){
            throw new OperationException("选择知识点不能为空，请不要进行非法操作！");
        }
        SubjectVo subject=subjectDao.get(questionVo.getSubject().getId());
        if (subject==null){
            throw new OperationException("选择科目不存在，请不要进行非法操作！");
        }
        TopicVo topicVo=topicDao.get(questionVo.getTopic().getId());
        if (topicVo==null){
            throw new OperationException("选择知识点不存在，请不要进行非法操作！");
        }
        QuestionTypeVo questionTypeVo =questionTypeDao.get(questionVo.getQuestionType().getId());
        if (questionTypeVo==null){
            throw new OperationException("选择题型不存在，请不要进行非法操作！");
        }
        if(questionVo.getIsPrivate()==null){
            questionVo.setIsPrivate(0);
        }else if(questionVo.getIsPrivate()!=0&&questionVo.getIsPrivate()!=1){
            throw new OperationException("不存在的可见类型，请不要进行非法操作！");
        }
        if(StringUtil.isEmpty(questionVo.getOutline())){
            throw new OperationException("题干不能为空，请核对后重试！");
        }
        questionVo.setSubject(subject);
        questionVo.setQuestionType(questionTypeVo);
        questionVo.setTopic(topicVo);

        if(!questionTypeVo.getName().equals(SysConsts.NO_ANSWER_QUESTION_TYPE_NAME)){
            String jsonOptions=questionVo.getOption();
            String[] options=jsonOptions.substring(2,jsonOptions.length()-2).split(",");
            String answer=questionVo.getAnswer().toUpperCase();
            if(StringUtil.isChar(answer)){
                if (StringUtil.isAnswer(answer,options.length)){
                    String temp1="";
                    for(int i=0;i<answer.length();i++){
                        temp1+=answer.charAt(i)-'A';
                    }
                    answer=temp1;
                }else {
                    throw new OperationException("答案格式不正确，请核对后重试！");
                }
            }else if(StringUtil.isNumber(answer)){
                if (!StringUtil.isAnswer(answer,options.length)){
                    throw new OperationException("答案格式不正确，请核对后重试！");
                }
            }else {
                throw new OperationException("答案格式不正确，请核对后重试！");
            }
            questionVo.setAnswer(StringUtil.sort(answer));
        }

    }

    public List<QuestionVo> getAllByTeacher(QuestionQueryModel questionQueryModel, Integer offset, Integer pageSize, TeacherVo login)
    {
        return questionDao.getAllByTeacher(questionQueryModel,offset,pageSize, login);
    }

    public void updateOrSaveToMe(QuestionVo questionVo, TeacherVo teacherVo) throws OperationException
    {
        QuestionVo temp=questionDao.get(questionVo.getId());
        if(temp.getTeacher().getTeacherId().equals(teacherVo.getTeacherId())){
            this.update(questionVo);
        }else {
            questionVo.setTeacher(teacherVo);
            questionVo.setId(IdUtil.getUUID());
            this.save(questionVo);
        }
    }

    public void bulkInputBySheet(HSSFSheet sheet, Integer subjectId) throws Exception
    {
        SubjectVo subjectVo=subjectDao.get(subjectId);
        if(subjectVo==null){
            throw new OperationException("不存在当前选择的科目，请勿非法操作！");
        }
        List<QuestionVo> questions=new ArrayList<QuestionVo>();
        for (Row row : sheet)
        {
            int rowNum=row.getRowNum();
            //跳过标题行
            if (rowNum==0||rowNum==1||rowNum==2)
                continue;
            try
            {
                //如果题型为空则不录入该行
                if(null!=row.getCell(2)&&null!=row.getCell(2).getStringCellValue()&&
                        !StringUtil.isEmpty(row.getCell(2).getStringCellValue())){

                    //读取表中数据并进行校验和属性设置
                    QuestionVo temp=setQuestion(row,subjectId);

                    //设置初始信息
                    temp.setSubject(subjectVo);
                    temp.setCreateTime(System.currentTimeMillis());

                    questions.add(temp);
                }else {
                    break;
                }
            }catch (IllegalStateException e){
                throw new OperationException("行号："+(rowNum+1)+"的数据格式错误，请核对后重试");
            }
        }

        questionDao.bulkInput(questions);
    }

    /**
     * 读取并设置
     * @param row
     * @param subjectId
     * @return
     * @throws FormatException
     */
    private QuestionVo setQuestion(Row row, Integer subjectId) throws FormatException
    {
        QuestionVo temp=new QuestionVo();
        int num=row.getRowNum()+1;
        String questionId=IdUtil.getUUID();
        Integer isPrivate=0;
        String code="";
        String outline="";
        List<String> options=new ArrayList<>();
        String answer="";
        TopicVo topic=null;
        TeacherVo teacher=null;
        QuestionTypeVo questionTypeVo=null;

        //读取数据
        if(row.getCell(2)!=null){
            String questionTypeName=row.getCell(2).getStringCellValue();
            questionTypeVo=questionTypeDao.getByName(questionTypeName.toUpperCase());
            if(questionTypeVo==null){
                throw new FormatException("行号："+num+"  题型:"+questionTypeName+"的题型不存在，请核对后重试！");
            }
        }
        if(row.getCell(3)!=null){
            outline=row.getCell(3).getStringCellValue();
            if(StringUtil.isEmpty(outline)){
                throw new FormatException("行号："+num+"  题干不能为空");
            }
        }
        if(row.getCell(4)!=null){
            code=row.getCell(4).getStringCellValue();
        }
        if(row.getCell(5)!=null){
            String isPrivateStr=row.getCell(5).getStringCellValue();
            if(!StringUtil.isEmpty(isPrivateStr)){
                Integer isPrivateNum=Integer.parseInt(isPrivateStr);
                if(isPrivateNum==1){
                    isPrivate=1;
                }
            }
        }
        if(row.getCell(6)!=null){
            String topicName=row.getCell(6).getStringCellValue();
            topic=topicDao.getByName(topicName,subjectId);
            if(topic==null){
                List<TopicVo> list=topicDao.getByLikeName(topicName);
                if(list.size()<=0){
                    throw new FormatException("行号："+num+"  知识点:"+topicName+"的知识点不存在，请核对后重试！");
                }else if(list.size()>1){
                    throw new FormatException("行号："+num+"  知识点:"+topicName+"的知识点不精确，请核对后重试！");
                }
                topic=list.get(0);
            }
        }
        if(row.getCell(7)!=null){
            String teacherId=row.getCell(7).getStringCellValue();
            if(teacherId==null||teacherId.trim().equals("")){
                throw new FormatException("行号："+num+"  出题人不能为空，请核对后重新导入！");
            }else if(teacherId.length()>32){
                throw new FormatException("行号："+num+"  出题人职工号格式不正确，请核对后重新导入！");
            }
            teacher=teacherDao.getByTeacherId(teacherId);
            if(teacher==null){
                throw new FormatException("行号："+num+"  职工号:"+teacherId+"的教师不存在，请核对后重试！");
            }
        }
        if(!questionTypeVo.getName().equals(SysConsts.NO_ANSWER_QUESTION_TYPE_NAME)){
            answer=readOptionAndAnswer(row,answer,options);
            //opstions转json
            StringBuilder jsonOptions=new StringBuilder();
            int i=0;
            jsonOptions.append("{[");
            for (String option : options)
            {
                jsonOptions.append("{");
                jsonOptions.append("\""+i+"\":\""+option+"\"");
                jsonOptions.append("},");
                i++;
            }
            String JsonOP=jsonOptions.substring(0,jsonOptions.length()-1);
            temp.setOption(JsonOP+"]}");
        }


        //属性设置
        temp.setId(questionId);
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
     * @param row
     * @param answer
     * @param options
     */
    private String readOptionAndAnswer(Row row, String answer, List<String> options) throws FormatException
    {
        int num=row.getRowNum()+1;

        for(int i=9;i<19;i++){
            if(row.getCell(i)!=null&&!StringUtil.isEmpty(row.getCell(i).getStringCellValue())){
                String option=row.getCell(i).getStringCellValue();
                options.add(option);
            }else {
                break;
            }
        }

        if(row.getCell(8)!=null){
            answer=row.getCell(8).getStringCellValue().toUpperCase();
            if(!StringUtil.isEmpty(answer)){
                if(StringUtil.isChar(answer)){
                    if (StringUtil.isAnswer(answer,options.size())){
                        String temp1="";
                        for(int i=0;i<answer.length();i++){
                            temp1+=answer.charAt(i)-'A';
                        }
                        answer=temp1;
                    }else {
                        throw new FormatException("行号："+num+"  答案格式不正确，请核对后重新导入！");
                    }
                }else if(StringUtil.isNumber(answer)){
                    if (!StringUtil.isAnswer(answer,options.size())){
                        throw new FormatException("行号："+num+"  答案格式不正确，请核对后重新导入！");
                    }
                }else {
                    throw new FormatException("行号："+num+"  答案格式不正确，请核对后重新导入！");
                }
            }
            answer=StringUtil.sort(answer);
        }
        return answer;
    }
}
