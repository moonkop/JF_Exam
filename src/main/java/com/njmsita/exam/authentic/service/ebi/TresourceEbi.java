package com.njmsita.exam.authentic.service.ebi;

import com.njmsita.exam.authentic.model.TresourceModel;
import com.njmsita.exam.base.BaseEbi;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TresourceEbi extends BaseEbi<TresourceModel>
{
    public List<TresourceModel> getAllByLoginTea(String id);
}
