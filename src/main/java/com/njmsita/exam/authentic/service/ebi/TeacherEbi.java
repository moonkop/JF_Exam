package com.njmsita.exam.authentic.service.ebi;

import com.njmsita.exam.authentic.model.TeacherModel;
import com.njmsita.exam.base.BaseEbi;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface TeacherEbi extends BaseEbi<TeacherModel>
{
    public TeacherModel login(String teacher_id, String password, String loginIp);
}
