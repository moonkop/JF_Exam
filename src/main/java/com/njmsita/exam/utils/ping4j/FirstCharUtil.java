package com.njmsita.exam.utils.ping4j;

import com.njmsita.exam.utils.exception.OperationException;
import com.njmsita.exam.utils.format.StringUtil;
import com.njmsita.exam.utils.idutil.IdUtil;

public class FirstCharUtil
{
    public static void main(String[] args) throws OperationException
    {
        System.out.println(firstCharAndID("hello world"));
    }

    public static String firstCharAndID(String src) throws OperationException
    {
        return firstChar(src)+"_"+IdUtil.getUUID().substring(0,16);
    }
    public static String firstChar(String src) throws OperationException
    {
        if(StringUtil.isEmpty(src)){
            throw new OperationException("知识点名称不能为空！");
        }else {
            String[] strings =PinYin4jUtils.stringToPinyin(src);
            StringBuilder sb=new StringBuilder();
            for (String string : strings)
            {
                sb.append(string.substring(0,1));
            }
            return sb.toString();
        }
    }
}
