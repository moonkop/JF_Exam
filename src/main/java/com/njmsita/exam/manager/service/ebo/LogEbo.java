package com.njmsita.exam.manager.service.ebo;

import com.njmsita.exam.authentic.model.UserModel;
import com.njmsita.exam.base.BaseQueryVO;
import com.njmsita.exam.manager.dao.dao.LogDao;
import com.njmsita.exam.manager.model.LogVo;
import com.njmsita.exam.manager.model.querymodel.LogQueryModel;
import com.njmsita.exam.manager.service.ebi.LogEbi;
import com.njmsita.exam.utils.format.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.List;

/**
 * 日志业务层实现类
 */
@Service
@Transactional
public class LogEbo implements LogEbi
{
    @Autowired
    private LogDao logdao;

    public void save(LogVo LogVo)
    {
        logdao.save(LogVo);
    }

    public List<LogVo> getAll()
    {
        return logdao.getAll();
    }

    public LogVo get(Serializable uuid)
    {
        return logdao.get(uuid);
    }

    public List<LogVo> getAll(BaseQueryVO qm, Integer pageNum, Integer pageCount)
    {
        return logdao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryVO qm)
    {
        return logdao.getCount(qm);
    }

    public void update(LogVo LogVo)
    {
        logdao.update(LogVo);
    }

    public void delete(LogVo logVo)
    {
        logdao.delete(logVo);
    }

    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------
    //-----------------------------------以上为基本操作-------------------------------------

    public void login(UserModel loginUser, String loginIp)
    {
        LogVo logVo = new LogVo();
        logVo.setUserId(loginUser.getUuid());
        logVo.setCommite("用户登陆");
        logVo.setMethod("登陆");
        logVo.setUserRole(loginUser.getUserRole());
        logVo.setRealName(loginUser.getRealName());
        logVo.setIp(loginIp);
        logVo.setModule("用户个人");
        logVo.setTime(System.currentTimeMillis());
        logdao.save(logVo);
    }

    public File getAll(LogQueryModel logQueryModel,String path) throws Exception
    {
        Long startTime=logQueryModel.getStartTime();
        Long endTime=logQueryModel.getEndTime();
        //如果开始时间为空默认为当前时间的一周前
        if(startTime==null){
            logQueryModel.setStartTime(System.currentTimeMillis()-604800000l);
        }
        //如果结束时间为空默认为当前系统时间
        if(endTime==null){
            logQueryModel.setEndTime(System.currentTimeMillis());
        }
        List<LogVo> logList=logdao.getAll(logQueryModel);
        //文件写入
        return fileWriter(logList,path);
    }

    /**
     * 文件写入方法
     * @param logList
     * @param path
     * @throws Exception
     */
    private File fileWriter(List<LogVo> logList,String path) throws Exception{
        String fileName= FormatUtil.formatDateTime(System.currentTimeMillis())+"_log.csv";

        fileName=fileName.replace("-","");
        fileName=fileName.replace(" ","");
        fileName=fileName.replace(":","");
        File file = new File(path+"\\"+fileName);

        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write("用户ID,用户姓名,用户身份,IP地址,操作模块,操作方法,具体描述,相应时间,携带参数,操作时间\r\n");
        for (LogVo logVo : logList)
        {
            String arguments=logVo.getArgument();
            arguments=arguments.replaceAll(",",";");
            out.write(logVo.getUserId()+","+logVo.getRealName()+","+logVo.getUserRole()+","+
                    logVo.getIp()+","+logVo.getModule()+","+logVo.getMethod()+","+logVo.getCommite()+","+
                    logVo.getResponseTime()+","+arguments+","+FormatUtil.formatDateTime(logVo.getTime())+"\r\n");
        }
        out.flush();
        out.close();
        return file;
    }
}
