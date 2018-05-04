package com.njmsita.exam.utils.format;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonListResponse<T> extends JsonResponse
{
    @JsonIgnore
    List<T> raw;

    @JsonIgnore
    String[] fields;
    @JsonIgnore
    List<Map<String, Object>> rows = new ArrayList<>();

    public void setFields(String fields)
    {
        this.fields = fields.split(",");
    }

    public void serialize(List<T> raw, String fields)
    {
        this.raw = raw;
        Pattern p = Pattern.compile("\\[([^\\]]+)\\]");
        try
        {
            setFields(fields);
            for (Object obj : raw)
            {
                Map<String, Object> row = new HashMap<>();
                for (String fieldPath : this.fields)
                {
                    String fieldName = fieldPath;
                    //匹配[classroom]classroom.name []中的classroom 为自定义key
                    Matcher matcher = p.matcher(fieldPath);
                    if (matcher.find())
                    {
                        fieldName = matcher.group();
                        fieldName = fieldName.substring(1, fieldName.length() - 1);
                        fieldPath = fieldPath.substring(fieldPath.lastIndexOf(']') + 1, fieldPath.length());
                    }
                    Object objtemp = obj;
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
                            break;
                        }
                        Class<?> classType = objtemp.getClass();
                        Field field = classType.getDeclaredField(props);
                        field.setAccessible(true); // 抑制Java对修饰符的检查
                        objtemp = field.get(objtemp);
                    }
                    row.put(fieldName, objtemp);
                }
                rows.add(row);
            }
            this.payload.put("rows", rows);

        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
