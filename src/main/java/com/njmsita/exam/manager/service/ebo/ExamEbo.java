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

    public void save(ExamVo examVo, String[] markTeachers,String paperId ,String[] classroomIds)
    {
        infoValid(examVo,markTeachers,paperId,classroomIds);
        examDao.save(examVo);
    }

    public void update(ExamVo examVo, String[] markTeachers,String paperId ,String[] classroomIds)
    {
        ExamVo temp=examDao.get(examVo.getId());
        if(temp==null){

        }
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

    private void infoValid(ExamVo examVo, String[] markTeachers,String paperId ,String[] classroomIds)
    {
        if(examVo.getSubjectVo()==null){

        }
        if (examVo.getSubjectVo().getId()==null||examVo.getSubjectVo().getId()==0){

        }
        if(classroomIds==null||classroomIds.length==0){

        }
        if(StringUtil.isEmpty(paperId)){

        }
        PaperVo paperVo=paperDao.get(paperId);
        SubjectVo temp= subjectDao.get(examVo.getSubjectVo().getId());
        if(paperVo==null){

        }
        if(temp==null){

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
        JSONArray jsonObject=JSONArray.fromObject(map);
        JSONArray paperJson=JSONArray.fromObject(paperVo);

        examVo.setSubjectVo(temp);
        examVo.setMarkTeachers(teacherSet);
        examVo.setClassroomIds(jsonObject.toString());
        examVo.setPaperContent(paperJson.toString());
    }
}
