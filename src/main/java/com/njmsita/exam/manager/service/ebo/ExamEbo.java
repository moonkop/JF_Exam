package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.authentic.dao.dao.TeacherDao;
import com.njmsita.exam.authentic.model.TeacherVo;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.*;
import com.njmsita.exam.manager.model.ClassroomVo;
import com.njmsita.exam.manager.model.ExamVo;
import com.njmsita.exam.manager.model.PaperVo;
import com.njmsita.exam.manager.model.SubjectVo;
import com.njmsita.exam.manager.service.ebi.ExamEbi;
import com.njmsita.exam.utils.consts.SysConsts;
import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.format.StringUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

/**
 * 考试业务层实现类
 */
@Service
@Transactional
public class ExamEbo implements ExamEbi
{
    @Autowired
    private ExamDao examDao;
    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private ClassroomDao classroomDao;
    @Autowired
    private PaperDao paperDao;

    /**
     * 作废
     * @param examVo
     */
    public void save(ExamVo examVo)
    {
        examDao.save(examVo);
    }

    public List<ExamVo> getAll()
    {
        return examDao.getAll();
    }

    public ExamVo get(Serializable uuid)
    {
        return examDao.get(uuid);
    }

    public List<ExamVo> getAll(BaseQueryVO qm, Integer pageNum, Integer pageCount)
    {
        return examDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryVO qm)
    {
        return examDao.getCount(qm);
    }

    /**
     * 作废
     * @param examVo
     */
    public void update(ExamVo examVo)
    {
        examDao.update(examVo);
    }

    public void delete(ExamVo examVo)
    {
        examDao.delete(examVo);
    }


    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------

    public void save(ExamVo examVo, String[] markTeachers,String paperId ,String[] classroomIds) throws OperationException
    {
        infoValid(examVo,markTeachers,paperId,classroomIds);
        examDao.save(examVo);
    }

    public void update(ExamVo examVo, String[] markTeachers,String paperId ,String[] classroomIds) throws OperationException
    {
        ExamVo temp=isNull(examVo);
        infoValid(examVo,markTeachers,paperId,classroomIds);

        temp.setCloseTime(examVo.getCloseTime());
        temp.setOpenTime(examVo.getOpenTime());
        temp.setDuration(examVo.getDuration());
        temp.setClassroomIds(examVo.getClassroomIds());
        temp.setMarkTeachers(examVo.getMarkTeachers());
        temp.setSubjectVo(examVo.getSubjectVo());
        temp.setCreateTeacher(examVo.getCreateTeacher());
        temp.setPaperContent(examVo.getPaperContent());

    }

    public List<ExamVo> getByCreateTeacher(TeacherVo login)
    {
        List<ExamVo> list=examDao.getByCreateTeacher(login.getTeacherId());
        operationValid(list,login);
        return list;
    }

    public List<ExamVo> getByMarkTeacher(TeacherVo login)
    {
        List<ExamVo> list=examDao.getByMarkTeacher(login.getTeacherId());
        operationValid(list,login);
        return list;
    }

    public void setPass(ExamVo examVo) throws OperationException
    {
        ExamVo temp=isNull(examVo);
        if(temp.getExamStatus()!=SysConsts.EXAM_STATUS_NO_CHECK){
            throw new OperationException("所选该场考试不是待审核状态，请不要进行非法操作！");
        }
        temp.setExamStatus(SysConsts.EXAM_STATUS_PASS);
    }

    public void setNoPass(ExamVo examVo) throws OperationException
    {
        ExamVo temp=isNull(examVo);
        if(temp.getExamStatus()!=SysConsts.EXAM_STATUS_NO_CHECK){
            throw new OperationException("所选该场考试不是待审核状态，请不要进行非法操作！");
        }
        if(StringUtil.isEmpty(examVo.getRemark())){
            throw new OperationException("驳回考试请求时“备注”不能为空，请不要进行非法操作！");
        }
        temp.setRemark(examVo.getRemark());
        temp.setExamStatus(SysConsts.EXAM_STATUS_NO_PASS);
    }

    private ExamVo isNull(ExamVo examVo) throws OperationException
    {
        ExamVo temp=examDao.get(examVo.getId());
        if(temp==null){
            throw new OperationException("所选该场考试不存在，请不要进行非法操作！");
        }
        return temp;
    }
    private void operationValid(List<ExamVo> list, TeacherVo login)
    {
        for (ExamVo examVo : list)
        {
            Set<String> set = new HashSet<>();
            set.add(SysConsts.EXAM_OPERATION_VIEW);
            examVo.setOperation(set);
            if(examVo.getExamStatus()==SysConsts.EXAM_STATUS_OPEN||examVo.getExamStatus()==SysConsts.EXAM_STATUS_CLOSE){
                continue;
            }
            if(examVo.getExamStatus()== SysConsts.EXAM_STATUS_NO_CHECK){
                if(login.getTroleVo().getId().equals(SysConsts.ADMIN_ROLE_ID)){
                    set.add(SysConsts.EXAM_OPERATION_NO_CHECK);
                }
                set.add(SysConsts.EXAM_OPERATION_EDIT);
                set.add(SysConsts.EXAM_OPERATION_CANCEL);
                set.add(SysConsts.EXAM_OPERATION_ADD_MARK_TEACHER);
                continue;
            }
            if(examVo.getExamStatus()==SysConsts.EXAM_STATUS_PASS){
                if(login.getTroleVo().getId().equals(SysConsts.ADMIN_ROLE_ID)){
                    set.add(SysConsts.EXAM_OPERATION_CANCEL);
                }
                set.add(SysConsts.EXAM_OPERATION_ADD_MARK_TEACHER);
                continue;
            }
            if(examVo.getExamStatus()==SysConsts.EXAM_STATUS_NO_PASS){
                if(login.getTroleVo().getId().equals(SysConsts.ADMIN_ROLE_ID)){
                    set.add(SysConsts.EXAM_OPERATION_CANCEL);
                }
                set.add(SysConsts.EXAM_OPERATION_EDIT);
                set.add(SysConsts.EXAM_OPERATION_CANCEL);
                set.add(SysConsts.EXAM_OPERATION_ADD_MARK_TEACHER);
                continue;
            }
            if(examVo.getExamStatus()==SysConsts.EXAM_STATUS_IN_MARK){
                if(!login.getTroleVo().getId().equals(SysConsts.ADMIN_ROLE_ID)){
                    set.add(SysConsts.EXAM_OPERATION_ADD_MARK_TEACHER);
                    set.add(SysConsts.EXAM_OPERATION_MARK);
                    set.add(SysConsts.EXAM_OPERATION_SUBMIT_MARK);
                }
                continue;
            }
            if(examVo.getExamStatus()==SysConsts.EXAM_STATUS_IN_CANCEL){
                if(login.getTroleVo().getId().equals(SysConsts.ADMIN_ROLE_ID)){
                    set.add(SysConsts.EXAM_OPERATION_DELETE);
                }
                continue;
            }
            if(examVo.getExamStatus()==SysConsts.EXAM_STATUS_ENDING){
                set.add(SysConsts.EXAM_OPERATION_VIEW_SCORE);
                continue;
            }
        }
    }

    private void infoValid(ExamVo examVo, String[] markTeachers,String paperId ,String[] classroomIds) throws OperationException
    {
        if(examVo.getSubjectVo()==null){
            throw new OperationException("科目不能为空,请不要进行非法操作！");
        }
        if (examVo.getSubjectVo().getId()==null||examVo.getSubjectVo().getId()==0){
            throw new OperationException("科目不能为空,请不要进行非法操作！");
        }
        if(classroomIds==null||classroomIds.length==0){
            throw new OperationException("参加考试的班级不能为空,请不要进行非法操作！");
        }
        if(StringUtil.isEmpty(paperId)){
            throw new OperationException("试卷不能为空,请不要进行非法操作！");
        }
        PaperVo paperVo=paperDao.get(paperId);
        SubjectVo temp= subjectDao.get(examVo.getSubjectVo().getId());
        if(paperVo==null){
            throw new OperationException("所选择的试卷不存在,请不要进行非法操作！");
        }
        if(temp==null){
            throw new OperationException("所选择的科目不存在,请不要进行非法操作！");
        }
        Set<TeacherVo> teacherSet=new HashSet<>();
        if(markTeachers!=null&&markTeachers.length>0){
            for (String markTeacher : markTeachers)
            {
                TeacherVo teacherVo=teacherDao.get(markTeacher);
                if(teacherVo!=null){
                    teacherSet.add(teacherVo);
                }
            }
        }
        Map<String,ClassroomVo> map=new HashMap<>();
        int i=0;
        for (String classroomId : classroomIds)
        {
            ClassroomVo classroomVo=classroomDao.get(classroomId);
            if(classroomVo!=null){
                map.put(i+"",classroomVo);
            }
        }
        if(map.entrySet().size()==0){
            throw new OperationException("所选择的班级均不存在,请不要进行非法操作！");
        }
        JSONArray jsonObject=JSONArray.fromObject(map);
        JSONArray paperJson=JSONArray.fromObject(paperVo);

        examVo.setSubjectVo(temp);
        examVo.setMarkTeachers(teacherSet);
        examVo.setClassroomIds(jsonObject.toString());
        examVo.setPaperContent(paperJson.toString());
    }
}