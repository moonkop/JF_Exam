package com.njmsita.exam.utils.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptionUtil
{
    public static String[] toOptionList(String string)
    {
        try
        {
            Map<String,String> map = CustomJsonSerializer.getDefaultMapper().readValue(string, Map.class);
            String[] result = new String[map.size()];
            for (Map.Entry entry : map.entrySet())
            {
                result[Integer.valueOf(entry.getKey().toString())]=entry.getValue().toString();
            }
            return  result;
        } catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static String toOptionString(String[] options)
    {
        Map<String, String> map = new HashMap<>();
        int i = 0;
        for (String option : options)
        {
            map.put("" + i, option);
            i++;
        }
        return CustomJsonSerializer.toJsonString_static(map);
    }
}
