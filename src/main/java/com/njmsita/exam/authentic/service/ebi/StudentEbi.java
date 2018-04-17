package com.njmsita.exam.authentic.service.ebi;

import com.njmsita.exam.authentic.model.StudentModel;
import com.njmsita.exam.base.BaseEbi;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface StudentEbi extends BaseEbi<StudentModel>
{
}
