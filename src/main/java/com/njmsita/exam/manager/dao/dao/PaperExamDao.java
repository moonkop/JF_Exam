package com.njmsita.exam.manager.dao.dao;

import com.njmsita.exam.manager.model.PaperVo;

public interface PaperExamDao
{
    public void savePaperToMongoExamPaper(PaperVo paperVo, String ExamId);
    public PaperVo getPaperVoByExamId(String examId);
    public void deletePaperFromMongoExamPaper(String ExamId);
    public void updatePaperFromMongoExamPaper(PaperVo paperVo, String examId);
}
