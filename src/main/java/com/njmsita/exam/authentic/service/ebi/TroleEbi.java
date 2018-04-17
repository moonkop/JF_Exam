package com.njmsita.exam.authentic.service.ebi;

import com.njmsita.exam.authentic.model.TroleModel;
import com.njmsita.exam.base.BaseEbi;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface TroleEbi extends BaseEbi<TroleModel>
{
}
