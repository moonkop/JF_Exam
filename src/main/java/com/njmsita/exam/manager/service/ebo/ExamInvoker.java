
package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.utils.exception.ItemNotFoundException;
import com.njmsita.exam.utils.exception.OperationException;

public interface ExamInvoker
{
    void invoke(ExamManageEbo examManageEbo) throws OperationException, ItemNotFoundException;
}
