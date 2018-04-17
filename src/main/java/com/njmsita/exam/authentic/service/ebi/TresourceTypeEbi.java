package com.njmsita.exam.authentic.service.ebi;

import com.njmsita.exam.authentic.model.TresourceTypeModel;
import com.njmsita.exam.base.BaseEbi;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface TresourceTypeEbi extends BaseEbi<TresourceTypeModel>
{
}
