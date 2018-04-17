package com.njmsita.exam.manager.service.ebi;

import com.njmsita.exam.base.BaseEbi;
import com.njmsita.exam.manager.model.ExamModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public interface ExamEbi extends BaseEbi<ExamModel>
{
}
