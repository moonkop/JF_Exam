package com.njmsita.exam.manager.service.ebi;

import com.njmsita.exam.authentic.model.UserModel;
import com.njmsita.exam.base.BaseEbi;
import com.njmsita.exam.manager.model.LogVo;
import com.njmsita.exam.manager.model.querymodel.LogQueryModel;
import com.njmsita.exam.utils.exception.OperationException;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 学校业务层接口
 */
public interface LogEbi extends BaseEbi<LogVo>
{

    /**
     * 用户登陆日志记录
     * @param loginUser
     * @param loginIp
     * @return
     */
    public void login(UserModel loginUser, String loginIp);

    /**
     * 指定时间区间查询日志
     * @param logQueryModel
     * @return
     */
    public File getAll(LogQueryModel logQueryModel, String path) throws Exception;
}
