package com.njmsita.exam.manager.service.ebi;

import com.njmsita.exam.base.BaseEbi;
import com.njmsita.exam.manager.model.LogModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public interface LogEbi extends BaseEbi<LogModel>
{
}
