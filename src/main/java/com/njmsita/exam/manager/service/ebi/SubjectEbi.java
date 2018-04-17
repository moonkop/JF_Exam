package com.njmsita.exam.manager.service.ebi;

import com.njmsita.exam.base.BaseEbi;
import com.njmsita.exam.manager.model.SubjectModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public interface SubjectEbi extends BaseEbi<SubjectModel>
{
}
