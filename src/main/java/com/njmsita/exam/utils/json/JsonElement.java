package com.njmsita.exam.utils.json;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonElement<T>
{
    String key;
    String fieldPath;
    Object nullValue = null;
    T object;
    CustomJsonElementFormatter<T> formater;


    public JsonElement(String field)
    {
        resolve(field);
    }

    public void resolve(String fieldRawString)
    {
        Pattern p = Pattern.compile("\\[([^\\]]+)\\]");
        String key = fieldRawString;
        String fieldPath = null;
        //匹配[classroom]classroom.name 中的[classroom] 为自定义key
        Matcher matcher = p.matcher(fieldRawString);
        if (matcher.find())
        {
            key = matcher.group();
            key = key.substring(1, key.length() - 1);
            fieldPath = fieldRawString.substring(fieldRawString.lastIndexOf(']') + 1, fieldRawString.length());
        } else
        {
            fieldPath = fieldRawString;
        }
        this.key = key;
        this.fieldPath = fieldPath;
    }

    public Object serialize(T object)
    {
        this.object = object;
        if (this.formater != null)
        {
            return formater.format(this.object);
        }
        Object objtemp = this.object;
        //以点分开，逐级深入查找
        String[] subProps = fieldPath.split("\\.");
        //若没有找到点 则成员名为本身
        if (subProps.length == 0)
        {
            subProps = new String[1];
            subProps[0] = fieldPath;
        }
        for (String props : subProps)
        {
            if (objtemp == null)
            {
                objtemp = this.nullValue;
                break;
            }
            Class<?> classType = objtemp.getClass();
            try
            {
                //若为函数 则调用函数
                if (props.indexOf("()") != -1)
                {
                   props= props.replace("()", "");
                    Method method = classType.getDeclaredMethod(props);
                    method.setAccessible(true);
                    objtemp = method.invoke(objtemp);
                } else
                {
                    Field field = classType.getDeclaredField(props);
                    field.setAccessible(true); // 抑制Java对修饰符的检查
                    objtemp = field.get(objtemp);
                }

            } catch (Exception e)
            {
                String message= e.getClass()+"  "+e.getMessage()+" in class:"+classType.getName();
                System.out.println(message);
                objtemp = null;
            }
        }
        return objtemp;
    }
}
